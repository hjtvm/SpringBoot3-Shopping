package com.huang.springboot3shopping.mapper;

import com.huang.springboot3shopping.bean.temporary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lenovo
 */
public interface temporaryMapper {
    public int addTemporary(@Param("pName") String pName, @Param("pPrice") String pPrice);
    public int deleteTemporary(@Param("id") int id);
    public int deleteAllTemporary();
    public List<temporary> selectAllTemporary();
}
