package com.spring.cloud.msc.dao;

import com.spring.cloud.msc.entity.ZuulRouteEntity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ZuulRouteDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL = "SELECT * FROM tb_zuul_route WHERE enabled=1";

    public Map<String, ZuulRoute> getRoutesFromTable() {
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteEntity> list = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ZuulRouteEntity.class));
        list.forEach(entity -> {
            if (StringUtils.isEmpty(entity.getPath())) {
                return;
            }
            ZuulRoute zuulRoute = new ZuulRoute();
            BeanUtils.copyProperties(entity, zuulRoute);
            routes.put(zuulRoute.getPath(), zuulRoute);
        });
        return routes;
    }

}
