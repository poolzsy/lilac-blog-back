package com.lilac.handler.exception;

import com.lilac.pojo.result.ResponseResult;
import com.lilac.enums.AppHttpCodeEnum;
import com.lilac.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        // 打印异常信息
        log.error("出现了异常！ {}",e);
        // 从异常对象中获取提示信息，返回给前端
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        // 打印异常信息
        log.error("出现了异常！ {}",e);
        // 从异常对象中获取提示信息，返回给前端
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
