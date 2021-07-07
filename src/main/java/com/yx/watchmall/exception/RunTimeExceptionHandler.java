package com.yx.watchmall.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yx.watchmall.enums.ResponseEnum;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class RunTimeExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody
    ResponseVo notValidArgumentExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseVo.error(ResponseEnum.PARAM_ERROR);
    }

    @ExceptionHandler(PaymentException.class)
    public @ResponseBody ResponseVo paymentExceptionHandler(PaymentException e) {
        return ResponseVo.error(ResponseEnum.PAYMENT_ERROR.getCode(),e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    public @ResponseBody ResponseVo userLoginExceptionHandler() {
        return ResponseVo.error(ResponseEnum.NO_CURRENTUSER);
    }

    @ExceptionHandler(InsufficientPermissionException.class)
    public @ResponseBody ResponseVo insufficientPermissionExceptionHandler() {
        return ResponseVo.error(ResponseEnum.INSUFFICIENT_PERMISSION);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public @ResponseBody ResponseVo resourceNotFound() {
        return ResponseVo.error(ResponseEnum.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public @ResponseBody ResponseVo jsonProcessingException(JsonProcessingException e) {
        return ResponseVo.error(-1,e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ResponseVo runtimeExceptionHandler(RuntimeException e) {
        return ResponseVo.error(-1, e.getMessage());
    }
}
