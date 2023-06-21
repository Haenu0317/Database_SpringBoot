package com.dlnu.entity.user;

import lombok.Data;

@Data
public class OutStore {
    // 出库时间
    private String outtime;
    // 订单号
    private String ordernum;
    // 吨数
    private double outton;
    // 件数
    private int outpieces;
    // 仓储费
    private double storagefee;
}
