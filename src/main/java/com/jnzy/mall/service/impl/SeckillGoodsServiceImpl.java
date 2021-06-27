package com.jnzy.mall.service.impl;

import com.jnzy.mall.mapper.SeckillGoodsMapper;
import com.jnzy.mall.pojo.SeckillGoods;
import com.jnzy.mall.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class SeckillGoodsServiceImpl  implements SeckillGoodsService {

  @Autowired(required = false)
  SeckillGoodsMapper seckillGoodsMapper;


  @Override
  public List<SeckillGoods> selectAll() {
    return seckillGoodsMapper.selectSeckillGoods();
  }

  @Override
  public SeckillGoods selectById(Long id) {
    return seckillGoodsMapper.selectByPrimaryKey(id);
  }

  @Override
  public Integer insertSeckillGoods(SeckillGoods seckillGoods) {
    return seckillGoodsMapper.insertSelective(seckillGoods);
  }

  @Override
  public Integer updateSeckillGoods(SeckillGoods seckillGoods) {
    return seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
  }

  @Override
  public Integer deleteById(Long id) {
    return seckillGoodsMapper.deleteByPrimaryKey(id);
  }

  @Override
  public Long selectMaxId() {
    return seckillGoodsMapper.selectMaxId();
  }

  @Override
  public Integer updatePic(SeckillGoods seckillGoods) {
    return seckillGoodsMapper.updatePic(seckillGoods);
  }


  //减少库存
  @Override
  public int deductSeckillGoodsStock(Long id) {
    return seckillGoodsMapper.deductSeckillGoodsStock(id);
  }


  //通过id查询秒杀商品
  @Override
  public SeckillGoods selectSeckillGoodsById(Long id) {
//    return  seckillGoodsMapper.selectByPrimaryKey(id);
  return  seckillGoodsMapper.selectByPrimaryKey(id);
  }

  /**
   * 添加秒杀商品
   *
   * @param seckillGoods
   */
  @Override
  public void saveSeckillGood(SeckillGoods seckillGoods) {
    seckillGoodsMapper.insert(seckillGoods);
  }

  @Override
  public void updateSeckillGood(SeckillGoods seckillGoods) {

  }

//  /**
//   * 修改秒杀商品
//   *
//   * @param seckillGoods
//   */
//  @Override
//  public void updateSeckillGood(SeckillGoods seckillGoods) {
//    seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
//  }

//  /**
//   * 减少库存
//   *
//   * @param id
//   */
//  @Override
//  public int deductSeckillGoodStock(Integer id) {
//    return seckillGoodsMapper.deductSeckillGoodStock(id);
//  }

  /**
   * 删除秒杀商品
   *
   * @param id
   */
  @Override
  public void deleteSeckillGood(Long id) {
    seckillGoodsMapper.deleteByPrimaryKey(id);
  }


}
