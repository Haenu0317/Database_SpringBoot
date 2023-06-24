package com.dlnu.controller;

import com.dlnu.common.R;
import com.dlnu.entity.user.Store;
import com.dlnu.service.StoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth/store")
public class StoreController {
    @Resource
    StoreService service;

    @GetMapping("findall")
    public R<List<Store>> findAll(){
        List<Store> storeList = service.findAll();
        System.out.println(storeList);
        return R.success(storeList);
    }

    @PostMapping("add")
    public R<String> createStore(@RequestBody Store store){
        int i = service.createStore(store.getStoreid(), store.getStoreton(), store.getStorecasenum(),
                store.getStorebelongport(), store.getStorebelongcompany());
        if (i == 1){
            return R.success("添加成功");
        }else {
            return R.error(201,"添加失败");
        }
    }

    @PostMapping("update")
    public R<String> updateStore(@RequestBody Store store){
        int i = service.updateStore(store.getStoreid(), store.getStoreton(), store.getStorecasenum(),
                store.getStorebelongport(), store.getStorebelongcompany());
        if (i == 1){
            return R.success("修改成功");
        }else {
            return R.error(201,"修改失败");
        }
    }

    @DeleteMapping("del")
    public R<String> deleteStore(@RequestBody Store store){
        int i = service.deleteStore(store.getStoreid());
        if (i == 1){
            return R.success("删除成功");
        }else {
            return R.error(201,"删除失败");
        }
    }

    @PostMapping("findone")
    public R<List<Store>> findStore(@RequestBody Store store){
        List<Store> storeList = new ArrayList<>(5);
        storeList.add(service.findStore(store.getStoreid()));
        if (storeList.size()!=0){
            return R.success(storeList);
        }else {
            return R.error(201,null);
        }
    }
}
