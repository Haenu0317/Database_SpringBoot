package com.dlnu.controller;

import com.dlnu.common.R;
import com.dlnu.entity.user.InStore;
import com.dlnu.entity.user.OutStore;
import com.dlnu.service.OutStoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth/outstore")
public class OutStoreController {
    @Resource
    OutStoreService service;

    @PostMapping("findone")
    public R<List<OutStore>> findone(@RequestBody OutStore outStore){
        List<OutStore> list =new ArrayList<>(5);
        list.add(service.findOutStore(outStore.getOutid()));
        if (list.get(0)==null){
            return R.error(201,null);
        }
        return R.success(list);
    }


    @GetMapping("/findAll")
    public R<List<OutStore>> findAllOutStore(){
        List<OutStore> allOutStore = service.findAllOutStore();
        return R.success(allOutStore);
    }

    @PostMapping("add")
    public R<String> CreateOutStore(@RequestBody OutStore outStore){
        try {
            outStore.setOutcost(100.0);
            int i = service.CreateOutStore(outStore.getOutid(), outStore.getOutdate(), outStore.getOutcasenum(),
                    outStore.getOutton(), outStore.getOutstoreid(), outStore.getOutcost());
            if(i==1){
                return R.success("添加成功");
            }else{
                return R.error(201,"添加失败");
            }
        } catch (Exception e) {
            return R.error(201, "出库失败,请检查取货仓库是否存在");
        }
    }

    @PostMapping("del")
    public R<String> deleteOutStore(@RequestBody OutStore outStore){
        int i = service.deleteOutStore(outStore.getOutid());
        if(i==1){
            return R.success("删除成功");
        }else{
            return R.error(201,"删除失败");
        }
    }
}
