package com.jnzy.mall.mapper;

import com.jnzy.mall.pojo.SeckillGoods;
import com.jnzy.mall.pojo.SeckillGoodsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SeckillGoodsMapper  {
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

  //查询最大的id

  int selectMaxId();

  //更新图片

  Integer updatePic(SeckillGoods seckillGoods);

  //库存减一
  int deductSeckillGoodsStock(Integer id);


  int countByExample(SeckillGoodsExample example);

  int deleteByExample(SeckillGoodsExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SeckillGoods record);

  int insertSelective(SeckillGoods record);

  List<SeckillGoods> selectByExample(SeckillGoodsExample example);

  SeckillGoods selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SeckillGoods record, @Param("example") SeckillGoodsExample example);

  int updateByExample(@Param("record") SeckillGoods record, @Param("example") SeckillGoodsExample example);

  int updateByPrimaryKeySelective(SeckillGoods record);

  int updateByPrimaryKey(SeckillGoods record);

  List<SeckillGoods> selectSeckillGoods();
}
