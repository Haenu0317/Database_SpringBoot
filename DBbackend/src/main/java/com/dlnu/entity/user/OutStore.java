package com.dlnu.entity.user;

import lombok.Data;

@Data
public class OutStore {
    //出库id
    private String outid;
    // 出库时间
    private String outdate;
    // 件数
    private int outcasenum;
    // 吨数
    private double outton;
    // 取货仓库
    private String outstoreid;
    // 仓储费
    private double outcost;
}
