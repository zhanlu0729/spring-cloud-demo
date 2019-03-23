package com.spring.cloud.msc.entity;

import lombok.Data;

@Data
public class ZuulRouteEntity {

    private Long id;
    private String path;
    private String serviceId;
    private String url;
    private Boolean stripPrefix;
    private Boolean retryable;
    private Boolean enabled;
    private String description;

}

