package com.dlnu.service.impl;

import com.dlnu.entity.user.OutStore;
import com.dlnu.mapper.OutStoreMapper;
import com.dlnu.service.OutStoreService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutStoreServiceImpl implements OutStoreService {

    @Resource
    OutStoreMapper outStoreMapper;

    @Override
    public OutStore findOutStore(String out_id) {
        return outStoreMapper.findOutStore(out_id);
    }

    @Override
    public List<OutStore> findAllOutStore() {
        return outStoreMapper.findAll();
    }

    @Override
    public int CreateOutStore(String outid, String outdate, int outcasenum, double outton, String outstoreid, double outcost) {
        return outStoreMapper.CreateOutStore(outid, outdate, outcasenum, outton, outstoreid, outcost);
    }

    @Override
    public int deleteOutStore(String outid) {
        return outStoreMapper.deleteOutStore(outid);
    }
}

