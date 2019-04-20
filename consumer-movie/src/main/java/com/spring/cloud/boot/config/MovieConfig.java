package com.spring.cloud.boot.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@EnableResourceServer
@Configuration
public class MovieConfig {

    /*@Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests().antMatchers("/**").authenticated()
            .antMatchers(HttpMethod.GET, "/test").hasAnyAuthority("WEIGHT_READ")
            .antMatchers(HttpMethod.GET, "/user/*").hasAnyAuthority("WEIGHT_READ");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("WEIGHT").tokenStore(jwtTokenStore()).stateless(true);
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }*/

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
