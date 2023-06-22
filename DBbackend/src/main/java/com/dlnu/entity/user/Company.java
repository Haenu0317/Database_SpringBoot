package com.dlnu.entity.user;

import lombok.Data;

@Data
public class Company {
    //公司编号
    private String companyid;
    //公司名称
    private String companyname;
    //所属港口
    private String companybelong;
    //公司负责人
    private String companyprincipal;
    //公司联系方式
    private String companyphone;
    //限时免费天数
    private int companyfreedays;
    //费率
    private double companyrate;

}
