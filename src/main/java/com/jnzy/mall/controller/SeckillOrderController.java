package com.jnzy.mall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnzy.mall.common.CodeMsg;
import com.jnzy.mall.common.Msg;
import com.jnzy.mall.common.Result;
import com.jnzy.mall.pojo.SeckillGoods;
import com.jnzy.mall.pojo.SeckillOrder;
import com.jnzy.mall.pojo.User;
import com.jnzy.mall.queue.MQSender;
import com.jnzy.mall.queue.domain.SeckillMessage;
import com.jnzy.mall.redis.RedisService;
import com.jnzy.mall.redis.domain.impl.GoodsKey;
import com.jnzy.mall.service.SeckillGoodsService;
import com.jnzy.mall.service.SeckillOrderService;
import com.jnzy.mall.service.impl.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-04-26
 */
@Controller
public class SeckillOrderController implements InitializingBean{

    @Autowired
    RedisService redisService;
    @Autowired
    MQSender mqSender;
    @Autowired
    SeckillOrderService seckillOrderService;
    @Autowired
    SeckillGoodsService seckillGoodsService;
    @Autowired
    SeckillService seckillService;

    /**
     *减少Redis网络开销，使用map
     */
    private Map<Long, Boolean> localOverMap = new ConcurrentHashMap<>();

    /**
     * 在项目于初始化的时候将mysql中秒杀商品数量存入缓存
     */
    @Override
    public void afterPropertiesSet() {
        List<SeckillGoods> seckillGoodsList = seckillGoodsService.selectAll();
        if (seckillGoodsList == null) {
            return;
        }
        for (SeckillGoods goods : seckillGoodsList) {
//            System.out.println("goods:" + goods);
            redisService.set(GoodsKey.getSeckillGoodsStock, "" + goods.getId(), goods.getStock());
            localOverMap.put(goods.getId(), false);
        }
    }

    /**
     * 添加订单并库存减一（用户）
     */
    @PostMapping("/admin/buySeckillGoodsProfile/{id}")
    @ResponseBody
    public Result addUser(SeckillOrder seckillOrder, HttpSession session, @PathVariable("id") Long goodsId) {
        //提取session中的loginUser，seckillGoods,total
        User loginUser = (User) session.getAttribute("loginUser");
        SeckillGoods seckillGoods = (SeckillGoods) session.getAttribute("seckillGoods");
        Integer total = (Integer) session.getAttribute("total");
        Long userId = loginUser.getId();
        session.setAttribute("userId", loginUser);

        if (userId == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        //从本地判断map是否秒杀结束（防止重复秒杀）
//        Boolean is_over = localOverMap.get(goodsId);
//        if (!is_over) {
//            return Result.error(CodeMsg.REPEATE_SECKILL);
//        }

        //判断库存：从redis中去取出商品数量
        Long stock = redisService.decr(GoodsKey.getSeckillGoodsStock, "" + goodsId);
        if (stock < 0) {
            localOverMap.put(goodsId, true);
            return Result.error(CodeMsg.SECKILL_OVER);
        }

        //商品存入队列
        SeckillMessage message = new SeckillMessage();
        message.setUserId(userId);
        message.setGoodsId(goodsId);
        message.setTotal(total);
        mqSender.sendSeckillMessage(message);

        //返回0表示订单排队中
        return Result.success(0);
    }

    /**
     * orderId: 成功
     * -1: 秒杀失败
     * 0: 排队中
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> seckillResult(Model model, @RequestParam("userId") Long userId, @RequestParam("goodsId")Long goodsId) {
        model.addAttribute("userId", userId);
        if (userId == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result = seckillService.getSeckillResult(userId, goodsId);
        return Result.success(result);
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
        model.addAttribute("seckillOrderPageInfo", pageInfo);
        model.addAttribute("seckillOrderList", list);
        return "admin/sortSeckillOrder";
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
        model.addAttribute("seckillOrderPageInfo", pageInfo);
        model.addAttribute("seckillOrderList", list);
        model.addAttribute("pageTopBarInfo", "秒杀商品订单管理界面");

        model.addAttribute("activeUrl1", "order");
        model.addAttribute("activeUrl2", "seckillOrder");

        return "root/seckillOrderInfo";
    }

    /**
     * 更新订单信息时回显用户信息（管理员）
     */
    @GetMapping("/root/getSeckillOrderById/{id}")
    @ResponseBody
    public Msg getSeckillOrderById(@PathVariable("id") Long id) {
        SeckillOrder seckillOrder = seckillOrderService.selectById(id);
        return Msg.success().add("seckillOrder", seckillOrder);
    }


    /**
     * 更新用户信息(管理员)
     */
    @PutMapping("/root/updateSeckillOrder/{Id}")
    @ResponseBody
    public Msg updateSeckillOrderProfile(@PathVariable("Id") Long id, SeckillOrder seckillOrder) {
        seckillOrder.setId(id);
        int result = seckillOrderService.updateSeckillOrder(seckillOrder);
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
    public Msg deleteUserById(@PathVariable("id") Long id, HttpSession session) {
        Integer result = seckillOrderService.deleteById(id);
        if (result == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

}
