package com.dlnu.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InStoreMapper {

    //增加入库记录
    @Insert("insert into instore(intime,carid,inton,inpieces) values(#{intime},#{carid},#{inton},#{inpieces})")
    int createInStore(String intime, String carid, double inton, int inpieces);

    //删除入库记录
    @Delete("delete from instore where carid=#{carid}")
    int deleteInStore(String carid);

}
