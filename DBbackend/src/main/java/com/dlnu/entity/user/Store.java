package com.dlnu.entity.user;

import lombok.Data;

@Data
public class Store {
    //仓库id
    private String storeid;
    //仓库吨数
    private double storeton;
    //仓库件数
    private int storecasenum;
    //仓库所属港口
    private String storebelongport;
    //仓库所属公司
    private String storebelongcompany;
}
