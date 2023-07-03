package com.dlnu.controller;

import com.dlnu.aop.SignatureValidation;
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

    /**
     * 查找所有公司
     *
     * @return
     */
    @GetMapping("find")
    public R<List<Company>> findAllCompany() {
        List<Company> allCompany = companyService.findAllCompany();
        if (allCompany.size() != 0) {
            return R.success(allCompany);
        } else {
            return R.error(201, null);
        }
    }

    /**
     * 查单个公司
     *
     * @param company
     * @return
     */
    @SignatureValidation
    @PostMapping("getcompany")
    public R<Company> getCompany(@RequestBody Company company) {
        System.out.println(company.getCompanyid());
        Company company1 = companyService.findCompany(company.getCompanyid());
        if (company1 != null) {
            return R.success(company1);
        } else {
            return R.error(201, null);
        }

    }


    /**
     * 增加公司
     *
     * @param company
     * @return
     */
    @PostMapping("add")
    public R<String> createCompany(@RequestBody Company company) {
        int i = 0;
        System.out.println(company);
        Company company1 = companyService.findCompany(company.getCompanyid());
        if (company1 == null) {
            try {
                i = companyService.createCompany(company.getCompanyid(), company.getCompanyname(), company.getCompanybelong(),
                        company.getCompanyprincipal(), company.getCompanyphone(), company.getCompanyfreedays(), company.getCompanyrate());
            } catch (Exception e) {
                return R.error(201, "请检查是否公司已经存在或者港口不存在");
            }
            if (i == 1) {
                return R.success("添加成功");
            }
        }
        return R.error(201, "添加失败");
    }


    /**
     * 删除公司
     *
     * @param company
     * @return
     */
    @PostMapping("del")
    public R<Integer> deleteCompany(@RequestBody Company company) {
        int i = companyService.deleteCompany(company.getCompanyid());
        if (i == 1) {
            return R.success(i);
        } else {
            return R.error(201, null);
        }
    }

    /**
     * 更新公司信息
     *
     * @param company
     * @return
     */
    @PostMapping("update")
    public R<Integer> updateCompany(@RequestBody Company company) {
        int i = companyService.updateCompany(company.getCompanyid(), company.getCompanyname(), company.getCompanybelong(), company.getCompanyprincipal(), company.getCompanyphone());
        if (i == 1) {
            return R.success(i);
        } else {
            return R.error(201, null);
        }
    }

}
