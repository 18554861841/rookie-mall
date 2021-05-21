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
    return seckillOrderMapper.selectAll();
  }

  @Override
  public List<SeckillOrder> selectByUsername(String username) {
    return seckillOrderMapper.selectByUsername(username);
  }

  @Override
  public List<SeckillOrder> selectByDiscount() {
    return seckillOrderMapper.selectByDiscount();
  }

  @Override
  public SeckillOrder selectById(Integer id) {
    return seckillOrderMapper.selectById(id);
  }

  @Override
  public Integer deleteById(Integer id) {
    return seckillOrderMapper.deleteById(id);
  }

  @Override
  public Integer updateSeckillOrder(SeckillOrder seckillOrder) {
    return seckillOrderMapper.updateSeckillOrder(seckillOrder);
  }



  @Override
  public Integer insertSeckillOrder(SeckillOrder seckillOrder) {
    return seckillOrderMapper.insertSelective(seckillOrder);
  }

  @Override
  public Integer totalSeckillOrder(String productName) {
    return seckillOrderMapper.totalSeckillOrder( productName);
  }


  @Override
  public List<SeckillOrder> selectSeckillOrder() {
    return null;
  }




}
