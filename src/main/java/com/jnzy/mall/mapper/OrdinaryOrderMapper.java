package com.jnzy.mall.mapper;

import com.jnzy.mall.pojo.OrdinaryOrder;
import com.jnzy.mall.pojo.OrdinaryOrderExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrdinaryOrderMapper {
    int countByExample(OrdinaryOrderExample example);

    int deleteByExample(OrdinaryOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrdinaryOrder record);

    int insertSelective(OrdinaryOrder record);

    List<OrdinaryOrder> selectByExample(OrdinaryOrderExample example);

    OrdinaryOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrdinaryOrder record, @Param("example") OrdinaryOrderExample example);

    int updateByExample(@Param("record") OrdinaryOrder record, @Param("example") OrdinaryOrderExample example);

    int updateByPrimaryKeySelective(OrdinaryOrder record);

    int updateByPrimaryKey(OrdinaryOrder record);

    List<OrdinaryOrder> selectAll();

    List<OrdinaryOrder> selectByUserName(String username);
}