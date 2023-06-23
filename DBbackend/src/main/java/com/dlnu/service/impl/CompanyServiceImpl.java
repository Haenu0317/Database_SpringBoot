package com.dlnu.service.impl;

import com.dlnu.entity.user.Company;
import com.dlnu.mapper.CompanyMapper;
import com.dlnu.service.CompanyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {
    @Resource
    CompanyMapper mapper;


    @Override
    public int createCompany(String company_id, String company_name, String company_belong, String company_principal, String company_phone) {
        return mapper.createCompany(company_id, company_name, company_belong, company_principal, company_phone);
    }

    @Override
    public int deleteCompany(String company_id) {
        return mapper.deleteCompany(company_id);
    }

    @Override
    public int updateCompany(String company_id, String company_name, String company_belong, String company_principal, String company_phone) {
        return mapper.updateCompany(company_id, company_name, company_belong, company_principal, company_phone);
    }

    @Override
    public Company findCompany(String company_id) {
        return mapper.findCompany(company_id);
    }

    @Override
    public List<Company> findAllCompany() {
        return mapper.findAllCompany();
    }
}
