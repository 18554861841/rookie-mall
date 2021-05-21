package com.jnzy.mall.service.impl;

import com.jnzy.mall.mapper.OrdinaryGoodsMapper;
import com.jnzy.mall.pojo.OrdinaryGoods;
import com.jnzy.mall.service.OrdinaryGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class OrdinaryGoodsServiceImpl implements OrdinaryGoodsService {

  @Autowired(required = false)
  OrdinaryGoodsMapper ordinaryGoodsMapper;

  @Override
  public List<OrdinaryGoods> selectAll() {
    return ordinaryGoodsMapper.selectAll();
  }

  @Override
  public OrdinaryGoods selectById(Integer id) {
    return ordinaryGoodsMapper.selectById(id);
  }

  @Override
  public Integer insertOrdinaryGoods(OrdinaryGoods ordinaryGoods) {
    return ordinaryGoodsMapper.insertOrdinaryGoods(ordinaryGoods);
  }

  @Override
  public Integer updateOrdinaryGoods(OrdinaryGoods ordinaryGoods) {
    return ordinaryGoodsMapper.updateOrdinaryGoods(ordinaryGoods);
  }

  @Override
  public Integer deleteById(Integer id) {
    return ordinaryGoodsMapper.deleteById(id);
  }

  @Override
  public int selectMaxId() {
    return ordinaryGoodsMapper.selectMaxId();
  }

  @Override
  public Integer updatePic(OrdinaryGoods ordinaryGoods) {
    return ordinaryGoodsMapper.updatePic(ordinaryGoods);
  }

  @Override
  public int deductOrdinaryGoodsStock(Integer id) {
    return ordinaryGoodsMapper.deductOrdinaryGoodsStock(id);
  }


}
