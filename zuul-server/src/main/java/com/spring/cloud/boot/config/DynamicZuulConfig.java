package com.spring.cloud.boot.config;

import com.spring.cloud.msc.dao.DynamicZuulRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ComponentScan(basePackages = {"com.spring.cloud.msc"})
@Configuration
public class DynamicZuulConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private ServerProperties serverProperties;

    @Bean
    public DynamicZuulRouteLocator routeLocator() {
        DynamicZuulRouteLocator locator = new DynamicZuulRouteLocator(serverProperties.getServlet().getPath(),
            zuulProperties);
        return locator;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers("/login", "/client/**").permitAll().anyRequest().authenticated().and()
            .csrf().disable();
    }

}
