package com.jnzy.mall.mapper;

import com.jnzy.mall.pojo.SeckillOrder;
import com.jnzy.mall.pojo.SeckillOrderExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SeckillOrderMapper {
    int countByExample(SeckillOrderExample example);

    int deleteByExample(SeckillOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SeckillOrder record);

    int insertSelective(SeckillOrder record);

    List<SeckillOrder> selectByExample(SeckillOrderExample example);

    SeckillOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SeckillOrder record, @Param("example") SeckillOrderExample example);

    int updateByExample(@Param("record") SeckillOrder record, @Param("example") SeckillOrderExample example);

    int updateByPrimaryKeySelective(SeckillOrder record);

    int updateByPrimaryKey(SeckillOrder record);

    List<SeckillOrder> selectSeckillOrder();

    List<SeckillOrder> selectByUserName(String username);

    List<SeckillOrder> selectByDiscount();

    Integer totalSeckillOrder(String productName);

    SeckillOrder selectSeckillOrderByGoodsIdUserId(Long goodsId, Long userId);
}