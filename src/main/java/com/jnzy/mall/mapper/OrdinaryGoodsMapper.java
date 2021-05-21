package com.jnzy.mall.mapper;

import com.jnzy.mall.pojo.OrdinaryGoods;
import com.jnzy.mall.pojo.OrdinaryGoodsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdinaryGoodsMapper  {

  //查全部
  List<OrdinaryGoods> selectAll();
  //根据id查
  OrdinaryGoods selectById(Integer id);
  //添加普通商品
  Integer insertOrdinaryGoods(OrdinaryGoods ordinaryGoods);
  //修改
  Integer updateOrdinaryGoods(OrdinaryGoods ordinaryGoods);
  //根据id删除
  Integer deleteById(Integer id);
  //查询最大的id
  int selectMaxId();
  //更新图片
  Integer updatePic(OrdinaryGoods ordinaryGoods);

  //库存减一
  int deductOrdinaryGoodsStock(Integer id);

  int countByExample(OrdinaryGoodsExample example);

  int deleteByExample(OrdinaryGoodsExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(OrdinaryGoods record);

  int insertSelective(OrdinaryGoods record);

  List<OrdinaryGoods> selectByExample(OrdinaryGoodsExample example);

  OrdinaryGoods selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") OrdinaryGoods record, @Param("example") OrdinaryGoodsExample example);

  int updateByExample(@Param("record") OrdinaryGoods record, @Param("example") OrdinaryGoodsExample example);

  int updateByPrimaryKeySelective(OrdinaryGoods record);

  int updateByPrimaryKey(OrdinaryGoods record);

}
