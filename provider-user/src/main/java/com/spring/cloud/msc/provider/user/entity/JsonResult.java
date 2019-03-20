package com.spring.cloud.msc.provider.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JsonResult<T> {

    private int status;
    private Boolean success;
    private String msg;
    private T data;

}
