package com.dlnu.service;

import com.dlnu.entity.user.OutStore;

import java.util.List;

public interface OutStoreService {

    OutStore findOutStore(String out_id);
    List<OutStore> findAllOutStore();

    public int CreateOutStore(String outid,  String outdate,int outcasenum, double outton, String outstoreid,double outcost);

    public int deleteOutStore(String outid);
}
