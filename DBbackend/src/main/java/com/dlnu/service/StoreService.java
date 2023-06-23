package com.dlnu.service;

import com.dlnu.entity.user.Store;

import java.util.List;

public interface StoreService {
    List<Store> findAll();

    int createStore(String store_id, double store_ton, int store_casenum, String store_belongport, String store_belongcompany);

    int updateStore(String store_id, double store_ton, int store_casenum, String store_belongport, String store_belongcompany);

    int deleteStore(String store_id);

    Store findStore(String store_id);
}
