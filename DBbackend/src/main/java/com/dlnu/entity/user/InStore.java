package com.dlnu.entity.user;

import lombok.Data;

@Data
public class InStore {
    //入库公司id
    private String inid;
    //入库时间
    private String intime;
    //车牌号
    private String incarid;
    //入库吨数
    private double inton;
    //入库件数
    private int incasenum;
    //入库仓库
    private String instoreid;
}
