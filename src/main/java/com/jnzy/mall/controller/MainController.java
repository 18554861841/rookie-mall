package com.jnzy.mall.controller;

import com.jnzy.mall.pojo.User;
import com.jnzy.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    /**
     * 404页面
     */
    @GetMapping(value = "/error/404")
    public String error_404() {
        return "error/404";
    }

    /**
     * 500页面
     */
    @GetMapping(value = "/error/500")
    public String error_500() {
        return "error/500";
    }


    /**
     * 错误界面返回
     * @param session
     * @return
     */
    @GetMapping("/toindex.html")
    public String toIndex(HttpSession session){

        return "redirect:/main.html";
    }

    /**
     * 管理员首页
     * @param model
     * @return
     */
    @GetMapping("/root/index.html")
    public String toAdminIndex( Model model){
        model.addAttribute("pageTopBarInfo","系统首页");
        model.addAttribute("activeUrl","indexActive");
        return "root/main";
    }

    /**
     * 用户首页
     * @param model
     * @return
     */
    @GetMapping("/admin/index.html")
    public String toUserIndex(Model model){

        model.addAttribute("pageTopBarInfo","系统首页");

        model.addAttribute("activeUrl","indexActive");
        return "admin/main";
    }


    /**
     * 注销（只有正常退出的用户可以注销）
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(@RequestParam("logout")String logout, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        User user = userService.selectById(loginUser.getId());
        session.removeAttribute("loginUser");

        System.out.println("logout==>" + user.getUsername() + "已退出系统");
        return "login";

    }
}
