package com.dlnu.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Store {
    //仓库id
    @JsonProperty("storeId")
    private String storeid;
    //仓库吨数
    @JsonProperty("storeTunnage")
    private double storeton;
    //仓库件数
    @JsonProperty("storeCasenum")
    private int storecasenum;
    //仓库所属港口
    @JsonProperty("storeBelongport")
    private String storebelongport;
    //仓库所属公司
    @JsonProperty("storeBelongcompany")
    private String storebelongcompany;
}
