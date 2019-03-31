package com.spring.cloud.boot.config;

import com.spring.cloud.msc.dao.DynamicZuulRouteLocator;
import com.spring.cloud.msc.filter.FirstPreFilter;
import com.spring.cloud.msc.filter.SecondPreFilter;
import com.spring.cloud.msc.filter.ThirdPreFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(1)
@EnableOAuth2Sso
@EnableDiscoveryClient
@EnableZuulProxy
@EnableConfigurationProperties({ZuulProperties.class, ServerProperties.class})
@Configuration
public class ZuulServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private ServerProperties serverProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/client/**").permitAll()
            .anyRequest().authenticated().and().csrf().disable();
    }

    @Bean
    public DynamicZuulRouteLocator routeLocator() {
        DynamicZuulRouteLocator locator = new DynamicZuulRouteLocator(serverProperties.getServlet().getPath(),
            zuulProperties);
        return locator;
    }

    @Bean
    public FirstPreFilter firstPreFilter() {
        return new FirstPreFilter();
    }

    @Bean
    public SecondPreFilter secondPreFilter() {
        return new SecondPreFilter();
    }

    @Bean
    public ThirdPreFilter thirdPreFilter() {
        return new ThirdPreFilter();
    }

}
