package com.dlnu.controller;

import com.dlnu.common.R;
import com.dlnu.entity.user.Company;
import com.dlnu.service.CompanyService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/company")
public class CompanyController {
    @Resource
    CompanyService companyService;

    @GetMapping("find")
    public R<List<Company>> findAllCompany() {
        List<Company> allCompany = companyService.findAllCompany();
        System.out.println(allCompany);
        if (allCompany.size() != 0) {
            return R.success(allCompany);
        } else {
            return R.error(201, null);
        }
    }

    @PostMapping("add")
    public R<Integer> createCompany(@RequestBody Company company) {
        System.out.println(company);
        int i = companyService.createCompany(company.getCompanyid(), company.getCompanyname(), company.getCompanybelong(), company.getCompanyprincipal(), company.getCompanyphone());
        if (i == 1) {
            return R.success(i);
        } else {
            return R.error(201, null);
        }
    }


}
