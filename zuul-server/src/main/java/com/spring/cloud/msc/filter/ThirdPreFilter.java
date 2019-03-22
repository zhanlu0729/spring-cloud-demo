package com.spring.cloud.msc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ThirdPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return (boolean)RequestContext.getCurrentContext().get("logic-is-success");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        System.err.println("这是第三个自定义Filter：" + request.getQueryString());
        String b = request.getParameter("b");
        if (StringUtils.isEmpty(b)) {
            log.warn("b参数为空");
            //禁止路由及访问下游服务
            context.setSendZuulResponse(false);
            context.setResponseBody("{\"status\":500, \"message\":\"b参数为空\"}");
            //作为同类型下游Filter的开关
            context.set("logic-is-success", false);
            return null;
        }
        return null;
    }

}
