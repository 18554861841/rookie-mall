package com.jnzy.mall.service.impl;

import com.jnzy.mall.mapper.SeckillOrderMapper;
import com.jnzy.mall.pojo.SeckillOrder;
import com.jnzy.mall.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

  @Autowired(required = false)
  SeckillOrderMapper seckillOrderMapper;

  @Override
  public List<SeckillOrder> selectAll() {
    return seckillOrderMapper.selectSeckillOrder();
  }

  @Override
  public List<SeckillOrder> selectByUsername(String username) {
    return seckillOrderMapper.selectByUserName(username);
  }

  @Override
  public List<SeckillOrder> selectByDiscount() {
    return seckillOrderMapper.selectByDiscount();
  }

  @Override
  public SeckillOrder selectById(Long id) {
    return seckillOrderMapper.selectByPrimaryKey(id);
  }

  @Override
  public Integer deleteById(Long id) {
    return seckillOrderMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateSeckillOrder(SeckillOrder seckillOrder) {
    return seckillOrderMapper.updateByPrimaryKeySelective(seckillOrder);
  }



  @Override
  public int insertSeckillOrder(SeckillOrder seckillOrder) {
    return seckillOrderMapper.insertSelective(seckillOrder);
  }

  @Override
  public Integer totalSeckillOrder(String productName) {
    return seckillOrderMapper.totalSeckillOrder(productName);
  }


  @Override
  public List<SeckillOrder> selectSeckillOrder() {
    return null;
  }

  @Override
  public SeckillOrder selectSeckillOrderByGoodsIdUserId(Long goodsId, Long userId) {
    return seckillOrderMapper.selectSeckillOrderByGoodsIdUserId(goodsId, userId);
  }


}
