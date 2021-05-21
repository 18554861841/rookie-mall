package com.jnzy.mall.service;


import com.jnzy.mall.pojo.OrdinaryOrder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-04-26
 */
public interface OrdinaryOrderService {
  //查全部
  List<OrdinaryOrder> selectAll();

  List<OrdinaryOrder> selectByUsername(String username);

  OrdinaryOrder selectById(Integer id);

  //删除用户
  Integer deleteById(Integer id);

  //修改用户
  Integer updateOrdinaryOrder(OrdinaryOrder ordinaryOrder);

  //添加
  Integer insertOrdinaryOrder(OrdinaryOrder ordinaryOrder);
}
