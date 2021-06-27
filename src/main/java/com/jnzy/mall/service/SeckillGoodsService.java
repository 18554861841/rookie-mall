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
  SeckillGoods selectById(Long id);

  //添加普通商品
  Integer insertSeckillGoods(SeckillGoods seckillGoods);

  //修改
  Integer updateSeckillGoods(SeckillGoods seckillGoods);

  //根据id删除
  Integer deleteById(Long id);

  Long selectMaxId();

  Integer updatePic(SeckillGoods seckillGoods);

  //库存减一
  int deductSeckillGoodsStock(Long id);



  /**
   * 通过id查询秒杀商品
   * @param id
   * @return
   */
  SeckillGoods selectSeckillGoodsById(Long id);

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
  void deleteSeckillGood(Long id);

}
