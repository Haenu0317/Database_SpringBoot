package com.dlnu.entity.user;

import lombok.Data;

@Data
public class InStore {
    //入库时间
    private String intime;
    //车牌号
    private String carid;
    //入库吨数
    private double inton;
    //入库件数
    private int inpieces;
}
