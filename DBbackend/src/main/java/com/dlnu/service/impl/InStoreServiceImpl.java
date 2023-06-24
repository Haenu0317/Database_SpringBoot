package com.dlnu.service.impl;

import com.dlnu.entity.user.InStore;
import com.dlnu.mapper.InStoreMapper;
import com.dlnu.service.InStoreService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InStoreServiceImpl implements InStoreService {

    @Resource
    InStoreMapper mapper;

    @Override
    public int CreateInStore(String inid, String intime, String incarid, double inton, int incasenum, String instoreid) {
        return mapper.createInStore(inid, intime, incarid, inton, incasenum, instoreid);
    }

    @Override
    public int deleteInStore(String inid) {
        return mapper.deleteInStore(inid);
    }

    @Override
    public int updateInStore(String inid, String intime, String incarid, double inton, int incasenum, String instoreid) {
        return mapper.updateInStore(inid, intime, incarid, inton, incasenum, instoreid);
    }

    @Override
    public InStore findInStore(String inid) {
        return mapper.findInStore(inid);
    }

    @Override
    public List<InStore> findAllInStore() {
        return mapper.findAllInStore();
    }
}

