package com.banksteel.bone.cloud.oauth.web;

import com.alibaba.fastjson.JSONObject;
import com.banksteel.bone.security.jwt.TokenUtils;
import com.banksteel.bone.security.model.Account;
import com.bone.cloud.boot.constants.JsonResultConstants;
import com.bone.cloud.boot.enums.JsonResultEnum;
import com.bone.cloud.boot.json.JsonResult;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/oauth")
public class AuthController {

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Value("${auth.jwt.secret}")
    private String secret;

    @Value("${auth.jwt.token.expireTime}")
    private long tokenExpireTime;

    @Value("${auth.jwt.token.invalidKeyFormat}")
    private String tokenInvalidKeyFormat;

    @Value("${auth.jwt.refreshToken.expireTime}")
    private long refreshTokenExpireTime;

    @Value("${auth.jwt.refreshToken.keyFormat}")
    private String refreshTokenKeyFormat;

    @Value("${auth.jwt.issuer}")
    private String issuer;

    @Value("${auth.jwt.claimName}")
    private String claimName;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录授权，生成JWT
     *
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestParam String userName, @RequestParam String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return JsonResult.fail(JsonResultConstants.REQUEST_ERROR, "Username or password is empty");
        }
        Account account = loadAccountByPassport(userName, password);
        if (account == null) {
            return JsonResult.fail(JsonResultEnum.OAUTH_PASSPORT_INCORRECT_ERROR);
        }
        if (!account.isValid()) {
            return JsonResult.fail(JsonResultEnum.OAUTH_ACCOUNT_STATUS_INVALID_ERROR);
        }
        //生成Token
        Date createTime = new Date();
        String token = createToken(createTime, account.getUserId());
        //生成RefreshToken
        String refreshToken = createRefreshToken();
        //保存RefreshToken
        saveRefreshToken(createTime, token, account.getUserId(), refreshToken);

        return JsonResult.success(createData(createTime, token, refreshToken));
    }

    /**
     * 刷新JWT
     *
     * @param refreshToken
     * @return
     */
    @GetMapping("/refreshToken")
    public JsonResult refreshToken(@RequestParam String refreshToken) {
        if (StringUtils.isEmpty(refreshToken)) {
            return JsonResult.fail(JsonResultConstants.REQUEST_ERROR, "Refresh token is empty");
        }
        String refreshTokenKey = String.format(refreshTokenKeyFormat, refreshToken);
        String userId = (String) stringRedisTemplate.opsForHash().get(refreshTokenKey, "user");
        if (StringUtils.isEmpty(userId)) {
            return JsonResult.fail(JsonResultEnum.OAUTH_REFRESH_TOKEN_INVALID_ERROR);
        }
        //生成Token
        Date createTime = new Date();
        String newToken = createToken(createTime, userId);
        //生成refreshToken
        String newRefreshToken = createRefreshToken();
        //保存RefreshToken
        saveRefreshToken(createTime, newToken, userId, newRefreshToken);
        //将OldToken加入黑名单，保证OldToken下次请求失效
        invalidOldToken(refreshToken);

        return JsonResult.success(createData(createTime, newToken, refreshToken));
    }

    /**
     * 根据UserId获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/userInfo")
    public JsonResult userInfo(@RequestParam String userId) {
        if (StringUtils.isEmpty(userId)) {
            return JsonResult.fail(JsonResultConstants.REQUEST_ERROR, "User id is empty");
        }
        Account account = loadAccountById(userId);
        return JsonResult.success(account);
    }

    private Account loadAccountByPassport(String userName, String password) {
        if (userName.equals("admin") && password.equals("123456")) {
            Account account = new Account();
            account.setUserId("1");
            account.setUserName(userName);
            account.setPassword(password);
            return account;
        }
        return null;
    }

    private Account loadAccountById(String userId) {
        if (userId.equals("1")) {
            Account account = new Account();
            account.setUserId(userId);
            account.setUserName("admin");
            account.setPassword("123456");
            return account;
        }
        return null;
    }

    private String createToken(Date createAt, String user) {
        return TokenUtils.buildJwt(secret, issuer, claimName, user, createAt, tokenExpireTime);
    }

    private String createRefreshToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private JSONObject createData(Date createTime, String token, String refreshToken) {
        JSONObject json = new JSONObject();
        json.put("token", token);
        json.put("tokenExpireAt", new Date(createTime.getTime() + tokenExpireTime).getTime() / 1000);
        json.put("refreshToken", refreshToken);
        json.put("refreshTokenExpireAt", new Date(createTime.getTime() + refreshTokenExpireTime).getTime() / 1000);
        return json;
    }


    /**
     * 保存RefreshToken
     *
     * @param createTime
     * @param token
     * @param user
     * @param refreshToken
     */
    private void saveRefreshToken(final Date createTime, final String token, final String user,
        final String refreshToken) {
        final String refreshTokenKey = String.format(refreshTokenKeyFormat, refreshToken);
        stringRedisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //保存refreshToken至redis，使用hash结构保存使用中的token，用户标识和创建时间
                operations.multi();
                operations.opsForHash().put(refreshTokenKey, "token", token);
                operations.opsForHash().put(refreshTokenKey, "user", user);
                operations.opsForHash().put(refreshTokenKey, "time", String.valueOf(createTime.getTime()));
                //refreshToken设置过期时间
                stringRedisTemplate.expire(refreshTokenKey, refreshTokenExpireTime, TimeUnit.MILLISECONDS);
                return operations.exec();
            }
        });
    }

    /**
     * 使Token在刷新后无效
     *
     * @param oldRefreshToken
     */
    private void invalidOldToken(final String oldRefreshToken) {
        final String oldRefreshTokenKey = String.format(refreshTokenKeyFormat, oldRefreshToken);
        Date oldTokenCreateTime = new Date(
            Long.valueOf((String) stringRedisTemplate.opsForHash().get(oldRefreshTokenKey, "time")));
        String oldToken = (String) stringRedisTemplate.opsForHash().get(oldRefreshTokenKey, "token");
        String oldTokenKey = String.format(tokenInvalidKeyFormat, oldToken);
        long oldTokenTimeout = oldTokenCreateTime.getTime() + tokenExpireTime - System.currentTimeMillis();
        if (oldTokenTimeout > 0) {
            stringRedisTemplate.opsForValue().set(oldTokenKey, "", oldTokenTimeout, TimeUnit.MILLISECONDS);
        }
        stringRedisTemplate.delete(oldRefreshToken);
    }
}