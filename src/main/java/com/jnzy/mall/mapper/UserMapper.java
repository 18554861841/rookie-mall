package com.jnzy.mall.mapper;

import com.jnzy.mall.pojo.User;
import com.jnzy.mall.pojo.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface UserMapper {

  //删除用户
  Integer deleteById(Integer id);

  //注册，添加用户
  int insertUser(User user);

  //  查全部
  List<User> selectAllUser();

  User selectById(Integer id);

  User selectByUsername(String username);

  User selectByUsernamePassword(String username, String password);

  //修改用户
  Integer updateUser(User user);



  int countByExample(UserExample example);

  int deleteByExample(UserExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(User record);

  int insertSelective(User record);

  List<User> selectByExample(UserExample example);

  User selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

  int updateByExample(@Param("record") User record, @Param("example") UserExample example);

  int updateByPrimaryKeySelective(User record);

  int updateByPrimaryKey(User record);
}
