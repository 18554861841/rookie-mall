package com.jnzy.mall.mapper;

import com.jnzy.mall.pojo.SeckillOrder;
import com.jnzy.mall.pojo.SeckillOrderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SeckillOrderMapper {

  //查全部
  List<SeckillOrder> selectAll();

  List<SeckillOrder> selectByUsername(String username);

  List<SeckillOrder> selectByDiscount();

  SeckillOrder selectById(Integer id);

  //删除用户
  Integer deleteById(Integer id);

  //修改用户
  Integer updateSeckillOrder(SeckillOrder seckillOrder);

  //订单的数量
  Integer totalSeckillOrder(String productName);


  int countByExample(SeckillOrderExample example);

  int deleteByExample(SeckillOrderExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SeckillOrder record);

  int insertSelective(SeckillOrder record);

  List<SeckillOrder> selectByExample(SeckillOrderExample example);

  SeckillOrder selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SeckillOrder record, @Param("example") SeckillOrderExample example);

  int updateByExample(@Param("record") SeckillOrder record, @Param("example") SeckillOrderExample example);

  int updateByPrimaryKeySelective(SeckillOrder record);

  int updateByPrimaryKey(SeckillOrder record);

}
