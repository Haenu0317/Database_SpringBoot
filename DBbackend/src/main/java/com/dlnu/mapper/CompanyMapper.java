package com.dlnu.mapper;

import com.dlnu.entity.user.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {

    //增加公司
    @Insert("insert into company(company_id, company_name, company_belong, company_principal, company_phone) value (#{company_id}, #{company_name}, #{company_belong}, #{company_principal}, #{company_phone})")
    int createCompany(String company_id, String company_name, String company_belong, String company_principal, String company_phone);

    //删除公司
    @Insert("delete from company where company_id = #{company_id}")
    int deleteCompany(String company_id);

    //修改公司
    @Update("update company set company_name = #{company_name}, company_belong = #{company_belong}, company_principal = #{company_principal}, company_phone = #{company_phone} where company_id = #{company_id}")
    int updateCompany(String company_id, String company_name, String company_belong, String company_principal, String company_phone);

    //查询公司
    @Select("select * from company where company_id = #{company_id}")
    Company findCompany(String company_id);

    //查询所有公司
    @Select("select * from company")
    @Results({
            @Result(column = "company_id", property = "companyid"),
            @Result(column = "company_name", property = "companyname"),
            @Result(column = "company_belong", property = "companybelong"),
            @Result(column = "company_principal", property = "companyprincipal"),
            @Result(column = "company_phone", property = "companyphone"),
            @Result(column = "company_freedays", property = "companyfreedays"),
            @Result(column = "company_rate", property = "companyrate")
    }
    )
    List<Company> findAllCompany();
}
