package com.dlnu.controller;

import com.dlnu.aop.SignatureValidation;
import com.dlnu.common.R;
import com.dlnu.entity.user.InStore;
import com.dlnu.entity.user.Store;
import com.dlnu.service.InStoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth/instore")
public class InStoreController {
    @Resource
    InStoreService service;

    @SignatureValidation
    @GetMapping("findall")
    public R<List<InStore>> findall() {
        List<InStore> allInStore = service.findAllInStore();
        return R.success(allInStore);
    }

    @PostMapping("findone")
    public R<List<InStore>> findone(@RequestBody InStore inStore) {
        List<InStore> allInStore = new ArrayList<>(5);
        System.out.println(inStore);
        InStore oneInStore = service.findInStore(inStore.getInid());
        System.out.println(oneInStore);
        allInStore.add(oneInStore);
        return R.success(allInStore);
    }

    @PostMapping("add")
    public R<String> createInStore(@RequestBody InStore inStore) {
        System.out.println(inStore);
        try {
            int i = service.CreateInStore(inStore.getInid(), inStore.getIntime(), inStore.getIncarid(), inStore.getInton(),
                    inStore.getIncasenum(), inStore.getInstoreid());
            if (i == 1) {
                return R.success("添加成功");
            } else {
                return R.error(201, "添加失败");
            }
        } catch (Exception e) {
            return R.error(201, "添加失败,请检查仓库是否存在");
        }
    }

    @PostMapping("del")
    public R<String> deleteInStore(@RequestBody InStore inStore) {
        System.out.println(inStore);
        int i = service.deleteInStore(inStore.getInid());
        if (i == 1) {
            return R.success("删除成功");
        } else {
            return R.error(201, "删除失败");
        }
    }



}
