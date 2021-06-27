package com.jnzy.mall.controller;

import com.jnzy.mall.common.Msg;
import com.jnzy.mall.pojo.User;
import com.jnzy.mall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

  @Autowired
  UserService userService;


  @RequestMapping("/toLogin")
  public String tioLogin(){
    return "login";
  }


  @RequestMapping("/login/{username}")
  @ResponseBody
  public Msg toLogin(@PathVariable("username") String username){
    User user = userService.selectByUserName(username);
    if (user != null) {
      return Msg.success().add("user",user);
    }
    return Msg.fail();
  }

  @GetMapping("/login/verifyLogin")
  @ResponseBody
  public Msg verifyLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                         HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
    User user = userService.selectByUsernamePassword(username,password);
    if (user != null) {
      session.setAttribute("loginUser", user);
      User loginUser = (User) session.getAttribute("loginUser");
      System.out.println(loginUser);
      System.out.println(loginUser.getUsername() + "登录");
      if (user.getIdentity() == 1){
        try {
          return Msg.success().add("url", "/root/index.html");
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
          return Msg.fail();
        }
      } else if(user.getIdentity() == 2) {
        try {
          return Msg.success().add("url", "/admin/index.html");
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
          return Msg.fail();
        }
      }
    }
    return Msg.fail();
  }

  @PostMapping("/register")
  @ResponseBody
  public Msg register(@RequestParam("username") String username, @RequestParam("password") String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setIdentity(2);
    System.out.println(user);
    userService.insertUser(user);
    System.out.println(user);
    return Msg.success().add("url", "/");
  }


}
