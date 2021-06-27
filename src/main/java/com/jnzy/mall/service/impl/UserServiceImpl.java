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

  @Autowired
  UserMapper userMapper;

  @Override
  public User selectById(Long id) {
    return userMapper.selectByPrimaryKey(id);
  }

  @Override
  public User selectByUserName(String username) {
    return userMapper.selectByUserName(username);
  }

  @Override
  public User selectByUsernamePassword(String username, String password) {
    return userMapper.selectByUserNamePassWord(username,password);
  }

  @Override
  public List<User> selectAllUser() {
    return userMapper.selectAllUser();
  }

  @Override
  public int insertUser(User user) {
    return userMapper.insert(user);
  }

  @Override
  public Integer updateUser(User user) {
    return userMapper.updateByPrimaryKey(user);
  }

  @Override
  public Integer deleteById(Long id) {
    return userMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(User record) {
    return userMapper.updateByPrimaryKeySelective(record);
  }
}
