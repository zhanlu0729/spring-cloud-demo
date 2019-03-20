package com.spring.cloud.boot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author liuqi
 */
@Setter
@Getter
@Builder
public class JsonResult<T> implements Serializable {

    private int status;
    private Boolean success;
    private String msg;
    private T data;

}
