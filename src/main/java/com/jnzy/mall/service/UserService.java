package com.jnzy.mall.service;

import com.jnzy.mall.pojo.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface UserService {
  User selectById(Integer id);
  User selectByUsername(String username);
  User selectByUsernamePassword(String username,String password);
  List<User> selectAllUser();
  int insertUser(User user);
  Integer updateUser(User user);
  Integer deleteById(Integer id);
  int updateByPrimaryKeySelective(User record);
}
