package com.jnzy.mall.service;

import com.jnzy.mall.pojo.OrdinaryGoods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface OrdinaryGoodsService {

  //查全部
  List<OrdinaryGoods> selectAll();

  //根据id查
  OrdinaryGoods selectById(Long id);

  //添加普通商品
  Integer insertOrdinaryGoods(OrdinaryGoods ordinaryGoods);

  //修改
  Integer updateOrdinaryGoods(OrdinaryGoods ordinaryGoods);

  //根据id删除
  int deleteById(Long id);

  Long selectMaxId();

  Integer updatePic(OrdinaryGoods ordinaryGoods);
  //库存减一
  int deductOrdinaryGoodsStock(Long id);

}
