package com.jnzy.mall.service.impl;

import com.jnzy.mall.mapper.UserMapper;
import com.jnzy.mall.pojo.User;
import com.jnzy.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class UserServiceImpl implements UserService  {

  @Autowired(required = false)
  UserMapper userMapper;

  @Override
  public User selectById(Integer id) {
    return userMapper.selectById(id);
  }

  @Override
  public User selectByUsername(String username) {
    return userMapper.selectByUsername(username);
  }

  @Override
  public User selectByUsernamePassword(String username, String password) {
    return userMapper.selectByUsernamePassword(username,password);
  }

  @Override
  public List<User> selectAllUser() {
    return userMapper.selectAllUser();
  }

  @Override
  public int insertUser(User user) {
    return userMapper.insertUser(user);
  }

  @Override
  public Integer updateUser(User user) {
    return userMapper.updateUser(user);
  }

  @Override
  public Integer deleteById(Integer id) {
    return userMapper.deleteById(id);
  }

  @Override
  public int updateByPrimaryKeySelective(User record) {
    return userMapper.updateByPrimaryKeySelective(record);
  }
}
