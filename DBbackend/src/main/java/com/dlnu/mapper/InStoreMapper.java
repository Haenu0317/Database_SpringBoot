package com.dlnu.mapper;

import com.dlnu.entity.user.InStore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InStoreMapper {

    //增加入库记录
    @Insert("insert into instore(intime,carid,inton,inpieces) values(#{intime},#{carid},#{inton},#{inpieces})")
    int createInStore(String intime, String carid, double inton, int inpieces);

    //删除入库记录
    @Delete("delete from instore where carid=#{carid}")
    int deleteInStore(String carid);

    @Update("update instore set intime=#{intime},carid=#{carid},inton=#{inton},inpieces=#{inpieces} where carid=#{carid}")
    int updateInStore(String intime, String carid, double inton, int inpieces);

    @Select("select * from instore where carid=#{carid}")
    InStore findInStore(String carid);

    @Select("select * from instore")
    List<InStore> findAllInStore();

}
