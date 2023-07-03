package com.dlnu.mapper;

import com.dlnu.entity.user.OutStore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OutStoreMapper {


    @Select("select * from outstore where out_id = #{out_id}")
    @Results({
            @Result(column = "out_id", property = "outid"),
            @Result(column = "out_date", property = "outdate"),
            @Result(column = "out_casenum", property = "outcasenum"),
            @Result(column = "out_ton", property = "outton"),
            @Result(column = "out_storeid", property = "outstoreid"),
            @Result(column = "out_cost", property = "outcost")
    }
    )
    OutStore findOutStore(String out_id);

    @Select("select * from outstore order by (out_id+0)")
    @Results({
            @Result(column = "out_id",property = "outid"),
            @Result(column = "out_date",property = "outdate"),
            @Result(column = "out_casenum",property = "outcasenum"),
            @Result(column = "out_ton",property = "outton"),
            @Result(column = "out_storeid",property = "outstoreid"),
            @Result(column = "out_cost",property = "outcost")
    })
    public List<OutStore> findAll();

    @Insert("insert into outstore(out_id,out_date,out_casenum,out_ton,out_storeid,out_cost) values(#{outid},#{outdate},#{outcasenum},#{outton},#{outstoreid},#{outcost})")
    public int CreateOutStore(String outid,  String outdate,int outcasenum, double outton, String outstoreid,double outcost);

    @Delete("delete from outstore where out_id=#{outid}")
    public int deleteOutStore(String outid);
}
