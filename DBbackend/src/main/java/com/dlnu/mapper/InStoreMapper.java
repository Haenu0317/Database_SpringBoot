package com.dlnu.mapper;

import com.dlnu.entity.user.InStore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InStoreMapper {

    //增加入库记录
    @Insert("insert into instore(in_id, in_time, in_carid, in_ton, in_casenum, in_storeid) value (#{in_id}, #{in_time}, #{in_carid}, #{in_ton}, #{in_casenum}, #{in_storeid})")
    int createInStore(String in_id, String in_time, String in_carid, double in_ton, int in_casenum, String in_storeid);


    //删除入库记录
    @Delete("delete from instore where in_id = #{in_id}")
    int deleteInStore(String in_id);

    //更新入库记录
    @Update("update instore set in_time = #{in_time}, in_carid = #{in_carid}, in_ton = #{in_ton}, in_casenum = #{in_casenum}, in_storeid = #{in_storeid} where in_id = #{in_id}")
    int updateInStore(String in_id, String in_time, String in_carid, double in_ton, int in_casenum, String in_storeid);

    //查询入库记录
    @Select("select * from instore where in_id = #{in_id}")
    @Results({

            @Result(column = "in_id", property = "inid"),
            @Result(column = "in_time", property = "intime"),
            @Result(column = "in_carid", property = "incarid"),
            @Result(column = "in_ton", property = "inton"),
            @Result(column = "in_casenum", property = "incasenum"),
            @Result(column = "in_storeid", property = "instoreid")
    }
    )
    InStore findInStore(String in_id);

    //查询所有入库记录
    @Select("select * from instore")
    @Results({

            @Result(column = "in_id", property = "inid"),
            @Result(column = "in_time", property = "intime"),
            @Result(column = "in_carid", property = "incarid"),
            @Result(column = "in_ton", property = "inton"),
            @Result(column = "in_casenum", property = "incasenum"),
            @Result(column = "in_storeid", property = "instoreid")
    }
    )
    List<InStore> findAllInStore();

}
