package com.yx.watchmall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yx.watchmall.enums.ResponseEnum;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    int status;
    String msg;
    T data;

    public ResponseVo() {
    }

    public ResponseVo(int status) {
        this.status = status;
    }

    public ResponseVo(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static <T> ResponseVo<T> success(T data) {
        ResponseVo<T> responseVo = new ResponseVo<>();
        responseVo.setStatus(0);
        responseVo.setData(data);
        return responseVo;
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(0);
    }

    public static <T> ResponseVo<T> error(int code, String msg) {
        ResponseVo<T> responseVo = new ResponseVo<T>();
        responseVo.setStatus(code);
        responseVo.setMsg(msg);
        return responseVo;
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum) {
        return new ResponseVo<>(responseEnum.getCode(),responseEnum.getMsg());
    }
}
