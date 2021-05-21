package com.jnzy.mall.mapper;


import com.jnzy.mall.pojo.OrdinaryOrder;
import com.jnzy.mall.pojo.OrdinaryOrderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdinaryOrderMapper {
  //查全部
  List<OrdinaryOrder> selectAll();

  List<OrdinaryOrder> selectByUsername(String username);

  OrdinaryOrder selectById(Integer id);


  //删除用户
  Integer deleteById(Integer id);


  //修改用户
  Integer updateOrdinaryOrder(OrdinaryOrder ordinaryOrder);

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
}
