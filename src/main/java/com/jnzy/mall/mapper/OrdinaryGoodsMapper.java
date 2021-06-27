package com.jnzy.mall.mapper;

import com.jnzy.mall.pojo.OrdinaryGoods;
import com.jnzy.mall.pojo.OrdinaryGoodsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrdinaryGoodsMapper {
    int countByExample(OrdinaryGoodsExample example);

    int deleteByExample(OrdinaryGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrdinaryGoods record);

    int insertSelective(OrdinaryGoods record);

    List<OrdinaryGoods> selectByExample(OrdinaryGoodsExample example);

    OrdinaryGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrdinaryGoods record, @Param("example") OrdinaryGoodsExample example);

    int updateByExample(@Param("record") OrdinaryGoods record, @Param("example") OrdinaryGoodsExample example);

    int updateByPrimaryKeySelective(OrdinaryGoods record);

    int updateByPrimaryKey(OrdinaryGoods record);

    List<OrdinaryGoods> selectAll();

    int deductOrdinaryGoodsStock(Long id);

    Long selectMaxId();

    Integer updatePic(OrdinaryGoods ordinaryGoods);
}