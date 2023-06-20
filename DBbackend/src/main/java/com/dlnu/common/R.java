package com.dlnu.common;

import lombok.Data;


@Data
public class R<T> {
    private Integer status;

    private boolean success; //信息

    private T message; //数据


    public R(Integer status, boolean success, T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public static <T> R<T> success(T object) {
        return new R<>(200, true, object);
    }

    public static <T> R<T> error(int status, T data) {
        return new R<>(status, false, data);

    }


}
