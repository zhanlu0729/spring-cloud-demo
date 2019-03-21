package com.spring.cloud.msc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class FirstPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String a = request.getParameter("a");
        if (StringUtils.isEmpty(a)) {
            //禁止路由及访问下游服务
            context.setSendZuulResponse(false);
            context.setResponseBody("{\"status\":500, \"message\":\"a参数为空\"}");
            //作为同类型下游Filter的开关
            context.set("logic-is-success", false);
            return null;
        }
        System.err.println("这是第一个自定义Filter：" + context.getRequestQueryParams());
        context.set("logic-is-success", true);
        return null;
    }

}
