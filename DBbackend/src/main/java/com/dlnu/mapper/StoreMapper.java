package com.dlnu.mapper;

import com.dlnu.entity.user.Store;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoreMapper {

    /**
     * 查找所有
     */
    @Select("select * from store")
    @Results({
            @Result(column = "store_id", property = "storeid"),
            @Result(column = "store_ton", property = "storeton"),
            @Result(column = "store_casenum", property = "storecasenum"),
            @Result(column = "store_belongport", property = "storebelongport"),
            @Result(column = "store_belongcompany", property = "storebelongcompany")
    })
    List<Store> findAll();

    /**
     * 新增
     * @param store_id
     * @param store_ton
     * @param store_casenum
     * @param store_belongport
     * @param store_belongcompany
     * @return
     */
    @Insert("insert into store(store_id, store_ton, store_casenum, store_belongport, store_belongcompany) value " +
            "(#{store_id}, #{store_ton}, #{store_casenum}, #{store_belongport}, #{store_belongcompany})")
    int createStore(String store_id, double store_ton, int store_casenum, String store_belongport, String store_belongcompany);


    /**
     * 更新
     * @param store_id
     * @param store_ton
     * @param store_casenum
     * @param store_belongport
     * @param store_belongcompany
     * @return
     */
    @Update("update store set store_ton=#{store_ton}, store_casenum=#{store_casenum}, store_belongport=#{store_belongport}, store_belongcompany=#{store_belongcompany} where store_id=#{store_id}")
    int updateStore(String store_id, double store_ton, int store_casenum, String store_belongport, String store_belongcompany);


    @Select("select store_belongcompany from store where store_id=#{store_id}")
    String findbelongcompany(String store_id);

    /**
     * 删除
     * @param store_id
     * @return
     */
    @Delete("delete from store where store_id=#{store_id}")
    int deleteStore(String store_id);

    @Select("select * from store where store_id=#{store_id}")
    @Results({
            @Result(column = "store_id", property = "storeid"),
            @Result(column = "store_ton", property = "storeton"),
            @Result(column = "store_casenum", property = "storecasenum"),
            @Result(column = "store_belongport", property = "storebelongport"),
            @Result(column = "store_belongcompany", property = "storebelongcompany")
    })
    Store findStoreById(String store_id);
}
