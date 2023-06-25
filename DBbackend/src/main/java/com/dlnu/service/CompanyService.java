package com.dlnu.service;

import com.dlnu.entity.user.Company;

import java.util.List;

public interface CompanyService {
    int createCompany(String company_id, String company_name, String company_belong, String company_principal, String company_phone, int company_freedays, double company_rate);

    int deleteCompany(String company_id);

    int updateCompany(String company_id, String company_name, String company_belong, String company_principal, String company_phone);

    Company findCompany(String company_id);

    List<Company> findAllCompany();
}
