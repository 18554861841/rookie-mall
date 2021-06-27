package com.jnzy.mall.service.impl;

import com.jnzy.mall.mapper.OrdinaryOrderMapper;
import com.jnzy.mall.pojo.OrdinaryOrder;
import com.jnzy.mall.service.OrdinaryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class OrdinaryOrderServiceImpl  implements OrdinaryOrderService {

  @Autowired(required = false)
  OrdinaryOrderMapper ordinaryOrderMapper;

  @Override
  public List<OrdinaryOrder> selectAll() {
    return ordinaryOrderMapper.selectAll();
  }

  @Override
  public List<OrdinaryOrder> selectByUsername(String username) {
    return ordinaryOrderMapper.selectByUserName(username);
  }

  @Override
  public OrdinaryOrder selectById(Integer id) {
    return ordinaryOrderMapper.selectByPrimaryKey(id);
  }

  @Override
  public Integer deleteById(Integer id) {
    return ordinaryOrderMapper.deleteByPrimaryKey(id);
  }

  @Override
  public Integer updateOrdinaryOrder(OrdinaryOrder ordinaryOrder) {
    return ordinaryOrderMapper.updateByPrimaryKeySelective(ordinaryOrder);
  }

  @Override
  public Integer insertOrdinaryOrder(OrdinaryOrder ordinaryOrder) {
    return ordinaryOrderMapper.insertSelective(ordinaryOrder);
  }
}
