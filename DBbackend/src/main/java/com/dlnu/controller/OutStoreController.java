package com.dlnu.controller;

import com.dlnu.common.R;
import com.dlnu.entity.user.InStore;
import com.dlnu.entity.user.OutStore;
import com.dlnu.entity.user.Store;
import com.dlnu.mapper.CompanyMapper;
import com.dlnu.mapper.InStoreMapper;
import com.dlnu.mapper.OutStoreMapper;
import com.dlnu.mapper.StoreMapper;
import com.dlnu.service.OutStoreService;
import com.dlnu.util.Util;
import io.lettuce.core.ScriptOutputType;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth/outstore")
public class OutStoreController {
    @Resource
    OutStoreService service;
    @Resource
    InStoreMapper mapper;

    @Resource
    StoreMapper storeMapper;
    @Resource
    CompanyMapper companyMapper;



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
        List<Store> all = storeMapper.findAll();
        for (Store store : all) {
            if (outStore.getOutstoreid().equals(store.getStoreid())){
                if (store.getStoreton()<outStore.getOutton()){
                    return R.error(201,"仓库剩余吨数不足");
                } else if (store.getStorecasenum()<outStore.getOutcasenum()) {
                    return R.error(201,"仓库剩余件数不足");
                }
            }
        }
        try {
            outStore.setOutcost(cal_cost(outStore));
            int i = service.CreateOutStore(outStore.getOutid(), outStore.getOutdate(), outStore.getOutcasenum(),
                    outStore.getOutton(), outStore.getOutstoreid(), outStore.getOutcost());
            if(i==1){
                return R.success("添加成功");
            }else{
                return R.error(201,"添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(201, "出库失败,请检查取货仓库是否存在或是否仓库余量不足");
        }
    }

    @PostMapping("del")
    public R<String> deleteOutStore(@RequestBody OutStore outStore){
        OutStore outStore1 = service.findOutStore(outStore.getOutid());
        String outstoreid = outStore1.getOutstoreid();
        String findbelongcompany = storeMapper.findbelongcompany(outstoreid);
        int i1 = companyMapper.updateCompanyCost(findbelongcompany, outStore1.getOutcost());
        System.out.println(i1);
        int i = service.deleteOutStore(outStore.getOutid());

        if(i==1){
            return R.success("删除成功");
        }else{
            return R.error(201,"删除失败");
        }
    }

    public double cal_cost(OutStore outStore) throws ParseException {
        List<InStore> inStoreList = mapper.findAllInStoreByStoreid(outStore.getOutstoreid());
        double cost = 0;
        double outton = outStore.getOutton();
        for (InStore inStore : inStoreList) {
            InStore newinStore = mapper.findInStore(inStore.getInid());
            double realTon = mapper.findRealTon(newinStore.getInid());
            if (outton == 0) break;
            if (realTon==0) continue;
            double inton = realTon;
            if (inton<outton){
                outton-=inton;
                double temp=inton;
                inton = 0;
                mapper.updateRealTon(newinStore.getInid(),inton);
                int days = Util.getIntervalDays(newinStore.getIntime(), outStore.getOutdate());
                if (days>=15){
                    cost += temp*0.5*(days-14);
                }
            }else {
                double temp = outton;
                inton -= temp;
                outton = 0;
                mapper.updateRealTon(newinStore.getInid(),inton);
                int days = Util.getIntervalDays(newinStore.getIntime(), outStore.getOutdate());
                if (days>=15){
                    cost += temp*0.5*(days-14);
                }
            }
        }
        return cost;
    }
}
