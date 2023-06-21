package com.dlnu.service.impl;

import com.dlnu.entity.user.InStore;
import com.dlnu.mapper.InStoreMapper;
import com.dlnu.service.InStoreService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InStoreServiceImpl implements InStoreService {
    @Resource
    InStoreMapper mapper;

    /**
     * 增加入库信息
     * @param intime 入库时间
     * @param carid 车牌号
     * @param inton 入库吨数
     * @param inpieces 入库件数
     * @return int
     */
    @Override
    public int createInStore(String intime, String carid, double inton, int inpieces) {
        return mapper.createInStore(intime, carid, inton, inpieces);
    }

    /**
     * 删除入库信息
     * @param carid 车牌号
     * @return int
     */
    @Override
    public int deleteInStore(String carid) {
        return mapper.deleteInStore(carid);
    }

    /**
     * 更新入库信息
     * @param intime
     * @param carid
     * @param inton
     * @param inpieces
     * @return
     */
    @Override
    public int updateInStore(String intime, String carid, double inton, int inpieces) {
        return mapper.updateInStore(intime, carid, inton, inpieces);
    }

    /**
     * 查询入库信息
     * @param carid
     * @return
     */
    @Override
    public InStore findInStore(String carid) {
        return mapper.findInStore(carid);
    }

    @Override
    public List<InStore> findAllInStore() {
        return mapper.findAllInStore();
    }
}
