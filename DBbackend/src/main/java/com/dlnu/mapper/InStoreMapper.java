package com.dlnu.mapper;

import com.dlnu.entity.user.InStore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InStoreMapper {

    //增加入库记录
    @Insert("insert into instore(in_id, in_time, in_carid, in_ton, in_casenum, in_storeid) value (#{in_id}, #{in_time}, #{in_carid}, #{in_ton}, #{in_casenum}, #{in_storeid})")
    int createInStore(@Param("in_id") String in_id, @Param("in_time") String in_time, @Param("in_carid") String in_carid, @Param("in_ton") double in_ton, @Param("in_casenum") int in_casenum, @Param("in_storeid") String in_storeid);


    //删除入库记录
    @Delete("delete from instore where in_carid = #{in_carid}")
    int deleteInStore(@Param("in_carid") String in_carid);

    //更新入库记录
    @Update("update instore set in_time = #{in_time}, in_carid = #{in_carid}, in_ton = #{in_ton}, in_casenum = #{in_casenum}, in_storeid = #{in_storeid} where in_carid = #{in_carid}")
    int updateInStore(@Param("in_time") String in_time, @Param("in_carid") String in_carid, @Param("in_ton") double in_ton, @Param("in_casenum") int in_casenum, @Param("in_storeid") String in_storeid);

    //查询入库记录
    @Select("select * from instore where in_carid = #{in_carid}")
    InStore findInStore(@Param("in_carid") String in_carid);

    @Select("select * from instore")
    List<InStore> findAllInStore();

}
