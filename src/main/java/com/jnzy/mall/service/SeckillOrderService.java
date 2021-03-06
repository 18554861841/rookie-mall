package com.jnzy.mall.service;

import com.jnzy.mall.pojo.SeckillOrder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-04-26
 */
public interface SeckillOrderService {

  //查全部
  List<SeckillOrder> selectAll();

  List<SeckillOrder> selectByUsername(String username);

  List<SeckillOrder> selectByDiscount();

  SeckillOrder selectById(Long id);

  //删除用户
  Integer deleteById(Long id);

  //修改用户
  int updateSeckillOrder(SeckillOrder seckillOrder);


  //添加订单
  int insertSeckillOrder(SeckillOrder seckillOrder);


  //订单的数量
  Integer totalSeckillOrder(String productName);

  /**
   * 查询订单列表
   * @return
   */
  List<SeckillOrder> selectSeckillOrder();

  SeckillOrder selectSeckillOrderByGoodsIdUserId(Long goodsId, Long userId);


}
