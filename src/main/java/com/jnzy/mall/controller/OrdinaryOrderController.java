package com.jnzy.mall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnzy.mall.common.Msg;
import com.jnzy.mall.pojo.OrdinaryOrder;
import com.jnzy.mall.pojo.SeckillOrder;
import com.jnzy.mall.pojo.User;
import com.jnzy.mall.service.OrdinaryGoodsService;
import com.jnzy.mall.service.OrdinaryOrderService;
import com.jnzy.mall.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-04-26
 */
@Controller
public class OrdinaryOrderController {

  @Autowired
  OrdinaryGoodsService ordinaryGoodsService;
  @Autowired
  OrdinaryOrderService ordinaryOrderService;
  @Autowired
  SeckillOrderService seckillOrderService;

  /**
   * 添加订单并库存减一（admin）
   */
  @PostMapping("/admin/buyOrdinaryGoodsProfile/{id}")
  @ResponseBody
  public Msg addUser(OrdinaryOrder ordinaryOrder,HttpSession session,@PathVariable("id") Long id) {

    User loginUser = (User) session.getAttribute("loginUser");
    Double productPrices = (Double) session.getAttribute("ProductPrices");
    ordinaryOrder.setUsername(loginUser.getUsername());
    ordinaryOrder.setTotalPrice(productPrices);
    ordinaryOrder.setId(null);

    Integer result = ordinaryOrderService.insertOrdinaryOrder(ordinaryOrder);
    if (result == 1) {
      int i = ordinaryGoodsService.deductOrdinaryGoodsStock(id);
      if (i == 1) {
        return Msg.success();
      }else {
        return Msg.fail();
      }
    }
    return Msg.fail();
  }

  /**
   * 删除订单（管理员）
   */
  @DeleteMapping("/root/deleteOrdinaryOrderById/{id}")
  @ResponseBody
  public Msg deleteUserById(@PathVariable("id") Integer id, HttpSession session) {
    Integer result = ordinaryOrderService.deleteById(id);
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }

  /**
   * 更新订单信息时回显用户信息（管理员）
   */
  @GetMapping("/root/getOrdinaryOrderById/{id}")
  @ResponseBody
  public Msg getUserInfoById(@PathVariable("id") Integer id) {
    OrdinaryOrder ordinaryOrder = ordinaryOrderService.selectById(id);
    return Msg.success().add("ordinaryOrder", ordinaryOrder);
  }




  /**
   * 跳转到用户信息界面（用户）
   */
  @RequestMapping("/admin/toOrder.html")
  public String toOrder( Model model, HttpSession session) {
    User loginUser = (User) session.getAttribute("loginUser");
    String username = loginUser.getUsername();

    List<OrdinaryOrder> ordinaryOrder = ordinaryOrderService.selectByUsername(username);
    List<SeckillOrder> seckillOrders = seckillOrderService.selectByUsername(username);

    System.out.println(ordinaryOrder);
    model.addAttribute("ordinaryOrderList", ordinaryOrder);
    model.addAttribute("seckillOrderList", seckillOrders);
    return "admin/order";
  }




  /**
   * 跳转到用户信息界面（管理员）
   */
  @RequestMapping("/root/toOrdinaryOrderInfo.html")
  public String toOrdinaryOrderInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                    Model model, HttpSession session) {
    // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
    PageHelper.startPage(pageNum, pageSize);
    List<OrdinaryOrder> list = ordinaryOrderService.selectAll();

    System.out.println(list);
    // 使用PageInfo包装查询后的结果，并交给页面处理
    // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
    PageInfo<OrdinaryOrder> pageInfo = new PageInfo<OrdinaryOrder>(list, 5);
    model.addAttribute("ordinaryOrderPageInfo", pageInfo);
    model.addAttribute("ordinaryOrderList", list);
    model.addAttribute("pageTopBarInfo", "普通商品订单管理界面");

    model.addAttribute("activeUrl1", "order");
    model.addAttribute("activeUrl2", "ordinaryOrder");

    return "root/ordinaryOrderInfo";
  }

  /**
   * 根据id更新订单()
   */
  @PutMapping("/root/updateOrdinaryOrderProfile/{Id}")
  @ResponseBody
  public Msg updateOrdinaryOrderProfile(@PathVariable("Id") Integer id, OrdinaryOrder ordinaryOrder) {
    ordinaryOrder.setId(id);
    Integer result = ordinaryOrderService.updateOrdinaryOrder(ordinaryOrder);
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }
}
