package com.jnzy.mall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnzy.mall.common.Msg;
import com.jnzy.mall.pojo.SeckillGoods;
import com.jnzy.mall.pojo.SeckillOrder;
import com.jnzy.mall.pojo.User;
import com.jnzy.mall.service.SeckillGoodsService;
import com.jnzy.mall.service.SeckillOrderService;
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
 *
 * @author jobob
 * @since 2021-04-26
 */
@Controller
public class SeckillOrderController {


  @Autowired
  SeckillOrderService seckillOrderService;
  @Autowired
  SeckillGoodsService seckillGoodsService;


  /**
   * 添加订单并库存减一（admin）
   */
  @PostMapping("/admin/buySeckillGoodsProfile/{id}")
  @ResponseBody
  public Msg addUser(SeckillOrder seckillOrder, HttpSession session, @PathVariable("id") Integer id) {
   //提取session中的loginUser，seckillGoods,total
    User loginUser = (User) session.getAttribute("loginUser");
    SeckillGoods seckillGoods = (SeckillGoods) session.getAttribute("seckillGoods");
    Integer total = (Integer) session.getAttribute("total");

    seckillOrder.setUsername(loginUser.getUsername());
    seckillOrder.setProductName(seckillGoods.getProductName());
    seckillOrder.setTotalPrice(seckillGoods.getProductPrices());
    seckillOrder.setId(null);

    String discount = null;
    System.out.println(total);
    if (total < 1){
      discount = "1折";
    } else if (total < 2){
      discount = "5折";
    } else if (total < 3){
      discount = "8折";
    }else {
      discount = "原价";
    }
    System.out.println(discount);
    seckillOrder.setDiscount(discount);
    System.out.println(seckillOrder);
    Integer result = seckillOrderService.insertSeckillOrder(seckillOrder);
    if (result == 1) {
      Integer i = seckillGoodsService.deductSeckillGoodsStock(id);
      if (i == 1) {
        return Msg.success();
      }else {
        return Msg.fail();
      }
    }
    return Msg.fail();
  }


  /**
   * 跳转到秒杀订单界面（用户 秒杀订单）
   */
  @RequestMapping("/admin/toSortOrder.html")
  public String toSortSeckillOrder(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                   Model model, HttpSession session) {
    // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
    PageHelper.startPage(pageNum, pageSize);
    List<SeckillOrder> list = seckillOrderService.selectByDiscount();

    System.out.println(list);
    // 使用PageInfo包装查询后的结果，并交给页面处理
    // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
    PageInfo<SeckillOrder> pageInfo = new PageInfo<SeckillOrder>(list, 5);
    model.addAttribute("seckillOrderPageInfo",pageInfo);
    model.addAttribute("seckillOrderList",list);
    return "/admin/sortSeckillOrder";
  }

  /**
   * 跳转到秒杀订单信息界面（管理员）
   */
  @RequestMapping("/root/toSeckillOrderInfo.html")
  public String toSeckillOrderInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                    Model model, HttpSession session) {
    // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
    PageHelper.startPage(pageNum, pageSize);
    List<SeckillOrder> list = seckillOrderService.selectAll();

    System.out.println(list);
    // 使用PageInfo包装查询后的结果，并交给页面处理
    // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
    PageInfo<SeckillOrder> pageInfo = new PageInfo<SeckillOrder>(list, 5);
    model.addAttribute("seckillOrderPageInfo",pageInfo);
    model.addAttribute("seckillOrderList",list);
    model.addAttribute("pageTopBarInfo", "秒杀商品订单管理界面");

    model.addAttribute("activeUrl1","order");
    model.addAttribute("activeUrl2","seckillOrder");

    return "/root/seckillOrderInfo";
  }

  /**
   * 更新订单信息时回显用户信息（管理员）
   */
  @GetMapping("/root/getSeckillOrderById/{id}")
  @ResponseBody
  public Msg getSeckillOrderById(@PathVariable("id")Integer id){
    SeckillOrder seckillOrder = seckillOrderService.selectById(id);
    return Msg.success().add("seckillOrder",seckillOrder);
  }


  /**
   * 更新用户信息(管理员)
   */
  @PutMapping("/root/updateSeckillOrder/{Id}")
  @ResponseBody
  public Msg updateSeckillOrderProfile(@PathVariable("Id") Integer id,SeckillOrder seckillOrder) {
    seckillOrder.setId(id);
    Integer result = seckillOrderService.updateSeckillOrder(seckillOrder);
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }


  /**
   * 删除订单（管理员）
   */
  @DeleteMapping("/root/deleteSeckillOrderById/{id}")
  @ResponseBody
  public Msg deleteUserById(@PathVariable("id")Integer id,HttpSession session){
    Integer result = seckillOrderService.deleteById(id);
    if (result==1){
      return Msg.success();
    }
    return Msg.fail();
  }

}
