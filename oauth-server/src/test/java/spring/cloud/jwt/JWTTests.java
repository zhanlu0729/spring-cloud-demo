package spring.cloud.jwt;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class JWTTests {

    @Test
    public void testJwt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

        System.err.println("------------------" + encoder.matches("myPassword", "myPassword"));
    }
}
