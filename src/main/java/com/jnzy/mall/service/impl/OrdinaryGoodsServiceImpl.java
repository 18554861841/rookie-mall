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
  public OrdinaryGoods selectById(Long id) {
    return ordinaryGoodsMapper.selectByPrimaryKey(id);
  }

  @Override
  public Integer insertOrdinaryGoods(OrdinaryGoods ordinaryGoods) {
    return ordinaryGoodsMapper.insertSelective(ordinaryGoods);
  }

  @Override
  public Integer updateOrdinaryGoods(OrdinaryGoods ordinaryGoods) {
    return ordinaryGoodsMapper.updateByPrimaryKey(ordinaryGoods);
  }

  @Override
  public int deleteById(Long id) {
    return ordinaryGoodsMapper.deleteByPrimaryKey(id);
  }

  @Override
  public Long selectMaxId() {
    return ordinaryGoodsMapper.selectMaxId();
  }

  @Override
  public Integer updatePic(OrdinaryGoods ordinaryGoods) {
    return ordinaryGoodsMapper.updatePic(ordinaryGoods);
  }

  @Override
  public int deductOrdinaryGoodsStock(Long id) {
    return ordinaryGoodsMapper.deductOrdinaryGoodsStock(id);
  }


}
