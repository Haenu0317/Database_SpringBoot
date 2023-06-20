package com.dlnu.common;

import lombok.Data;


@Data
public class R<T> {
    private Integer status;

    private boolean success; //信息

    private T data; //数据


    public R(Integer status, boolean success, T data) {
        this.status = status;
        this.success = success;
        this.data = data;
    }

    public static <T> R<T> success(T object) {
        return new R<>(200, true, null);
    }

    public static <T> R<T> error(int status, String data) {
        R r = new R(status, false, data);
        return r;

    }


}
