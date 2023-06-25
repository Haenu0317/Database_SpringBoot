package com.dlnu.service.impl;

import com.dlnu.entity.user.Store;
import com.dlnu.mapper.StoreMapper;
import com.dlnu.service.StoreService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Resource
    StoreMapper mapper;


    @Override
    public List<Store> findAll() {
        return mapper.findAll();
    }

    @Override
    public int createStore(String store_id, double store_ton, int store_casenum, String store_belongport, String store_belongcompany) {
        return mapper.createStore(store_id, store_ton, store_casenum, store_belongport, store_belongcompany);
    }

    @Override
    public int updateStore(String store_id, double store_ton, int store_casenum, String store_belongport, String store_belongcompany) {
        return mapper.updateStore(store_id, store_ton, store_casenum, store_belongport, store_belongcompany);
    }

    @Override
    public int deleteStore(String store_id) {
        return mapper.deleteStore(store_id);
    }

    @Override
    public String findbelongcompany(String store_id) {
        return mapper.findbelongcompany(store_id);
    }

    @Override
    public Store findStore(String store_id) {
        return mapper.findStoreById(store_id);
    }

}
