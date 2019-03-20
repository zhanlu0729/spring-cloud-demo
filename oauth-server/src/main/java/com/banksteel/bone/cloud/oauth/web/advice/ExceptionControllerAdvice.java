package com.banksteel.bone.cloud.oauth.web.advice;

import com.bone.cloud.boot.constants.JsonResultConstants;
import com.bone.cloud.boot.enums.JsonResultEnum;
import com.bone.cloud.boot.json.JsonResult;
import java.net.BindException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(
        ExceptionControllerAdvice.class);

    @ExceptionHandler({
        BindException.class,
        HttpMessageNotReadableException.class,
        HttpMessageNotWritableException.class,
        MethodArgumentNotValidException.class,
        TypeMismatchException.class,

        ServletRequestBindingException.class,
        MissingPathVariableException.class,
        MissingServletRequestParameterException.class,
        MissingServletRequestPartException.class,
        UnsatisfiedServletRequestParameterException.class,

        HttpMediaTypeException.class,
        HttpMediaTypeNotSupportedException.class,
        HttpMediaTypeNotAcceptableException.class,

        HttpRequestMethodNotSupportedException.class
    })
    public JsonResult handleRequestException(Exception e) {
        logger.error("Service request error", e);
        return JsonResult.fail(JsonResultConstants.REQUEST_ERROR, "Service request is error");
    }

    @ExceptionHandler({
        ResourceAccessException.class,
        NoHandlerFoundException.class
    })
    public JsonResult handleResourceNotFoundException(Exception e) {
        logger.error("Service not found error", e);
        return JsonResult.fail(JsonResultEnum.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public JsonResult handleServerException(Exception e) {
        logger.error("Internal server error", e);
        return JsonResult.fail(JsonResultEnum.INTERNAL_SERVER_ERROR);

    }
}