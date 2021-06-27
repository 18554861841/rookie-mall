package com.jnzy.mall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnzy.mall.common.Msg;
import com.jnzy.mall.pojo.User;
import com.jnzy.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@Controller
public class UserController {

  @Autowired
  UserService userService;

  /**
   * 跳转到用户信息界面（管理员）
   */
  @GetMapping("/root/toUserInfo.html")
  public String toUserInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                           Model model, HttpSession session) {
    // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
    PageHelper.startPage(pageNum, pageSize);
    List<User> list = userService.selectAllUser();

    System.out.println(list);
    // 使用PageInfo包装查询后的结果，并交给页面处理
    // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
    PageInfo<User> pageInfo = new PageInfo<User>(list, 5);
    model.addAttribute("userPageInfo",pageInfo);
    model.addAttribute("userList",list);
    model.addAttribute("pageTopBarInfo", "用户信息界面");
    return "root/userinfo";
  }

  /**
   * 添加用户（管理员）
   */
  @PostMapping("/root/addUser")
  @ResponseBody
  public Msg addUser(User user){
    Integer result = userService.insertUser(user);
    if (result==1){
      return Msg.success();
    }
    return Msg.fail();
  }

  /**
   * 更新用户信息时回显用户信息（管理员）
   */
  @GetMapping("/root/getUserById/{id}")
  @ResponseBody
  public Msg getUserInfoById(@PathVariable("id")Long id){
    User user = userService.selectById(id);
    return Msg.success().add("user",user);
  }

  /**
   * 更新用户信息(管理员)
   */
  @PutMapping("/root/updateUserProfile/{userId}")
  @ResponseBody
  public Msg updateUserProfile(@PathVariable("userId") Long id, User user, HttpSession session) {
    user.setId(id);
    Integer result = userService.updateByPrimaryKeySelective(user);
    if (result == 1) {
      // 当前登录用户信息改变时session里面存储的用户信息也应该同时改变
      User loginUser = (User) session.getAttribute("loginUser");
      if (loginUser!=null){
        if (id.equals(loginUser.getId())) {
          session.setAttribute("loginUser", userService.selectById(id));
        }
      }
      return Msg.success();
    }
    return Msg.fail();
  }

  /**
   * 删除用户（管理员）
   */
  @DeleteMapping("/root/deleteUserById/{id}")
  @ResponseBody
  public Msg deleteUserById(@PathVariable("id")Long id,HttpSession session){
    Integer result = userService.deleteById(id);
    if (result==1){
      User loginUser = (User) session.getAttribute("loginUser");
      if (loginUser!=null){
        if (id == (loginUser.getId())) {
          session.removeAttribute("loginUser");
        }
      }
      return Msg.success();
    }
    return Msg.fail();
  }



  /**
   * 更新用户信息时回显用户信息（普通用户）
   */
  @GetMapping("/admin/getUserByUsername")
  @ResponseBody
  public Msg getUserInfoByUsername(HttpSession session){
    User loginUser = (User) session.getAttribute("loginUser");
    String username = loginUser.getUsername();
    User user = userService.selectByUserName(username);
    return Msg.success().add("user",user);
  }

  /**
   * 更新用户信息(普通用户)
   */
  @PutMapping("/admin/updateUserProfile")
  @ResponseBody
  public Msg updateUserProfile1(User user, HttpSession session) {
    System.out.println("user:" + user );

    // 当前登录用户信息改变时session里面存储的用户信息也应该同时改变
    User loginUser = (User) session.getAttribute("loginUser");
    System.out.println("loginUser:" + loginUser );
    user.setId(loginUser.getId());
    Integer result = userService.updateUser(user);
    if (result == 1) {
      session.setAttribute("loginUser", userService.selectById(loginUser.getId()));
      return Msg.success();
    }
    return Msg.fail();
  }

}
