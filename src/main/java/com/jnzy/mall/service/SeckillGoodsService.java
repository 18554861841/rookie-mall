package com.jnzy.mall.service;

import com.jnzy.mall.pojo.SeckillGoods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface SeckillGoodsService {

  //查全部
  List<SeckillGoods> selectAll();

  //根据id查
  SeckillGoods selectById(Integer id);

  //添加普通商品
  Integer insertSeckillGoods(SeckillGoods seckillGoods);

  //修改
  Integer updateSeckillGoods(SeckillGoods seckillGoods);

  //根据id删除
  Integer deleteById(Integer id);

  int selectMaxId();

  Integer updatePic(SeckillGoods seckillGoods);

  //库存减一
  int deductSeckillGoodsStock(Integer id);



  /**
   * 通过id查询秒杀商品
   * @param id
   * @return
   */
  SeckillGoods selectSeckillGoodsById(Integer id);

  /**
   * 添加秒杀商品
   * @param seckillGoods
   */
  void saveSeckillGood(SeckillGoods seckillGoods);

  /**
   * 修改秒杀商品
   * @param seckillGoods
   */
  void updateSeckillGood(SeckillGoods seckillGoods);

//  /**
//   *  减少库存
//   * @param id
//   * @return
//   */
//  int deductSeckillGoodStock(Integer id);

  /**
   * 删除秒杀商品
   * @param id
   */
  void deleteSeckillGood(Integer id);

}
