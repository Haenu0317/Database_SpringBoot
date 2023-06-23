package com.dlnu.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
public class Company {
    //公司编号
    @JsonProperty("companyId")
    private String companyid;
    //公司名称
    @JsonProperty("companyName")
    private String companyname;
    //所属港口
    @JsonProperty("companyBelongPort")
    private String companybelong;
    //公司负责人
    @JsonProperty("companyPrincipal")
    private String companyprincipal;
    //公司联系方式
    @JsonProperty("companyPhnumber")
    private String companyphone;
    //限时免费天数
    @JsonProperty("companyFreedays")
    private int companyfreedays;
    //费率
    @JsonProperty("companyRate")
    private double companyrate;

}
