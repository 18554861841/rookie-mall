package com.jnzy.mall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnzy.mall.common.Msg;
import com.jnzy.mall.pojo.SeckillGoods;
import com.jnzy.mall.service.SeckillGoodsService;
import com.jnzy.mall.service.SeckillOrderService;
import com.jnzy.mall.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@Controller
public class SeckillGoodsController {

  @Autowired
  SeckillGoodsService seckillGoodsService;
  @Autowired
  SeckillOrderService seckillOrderService;
  @Value("${web.upload-path}")
  private String path;
  private ResourceLoader resourceLoader;

  @Autowired
  public void PhotoController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  /**
   * 添加商品（管理员）
   */
  @PostMapping("/root/addSeckillGoods")
  @ResponseBody
  public Msg addSeckillGoods(SeckillGoods seckillGoods) {
    Integer result = seckillGoodsService.insertSeckillGoods(seckillGoods);
    //TODO
    seckillGoodsService.selectMaxId();
    seckillGoods.getStock();
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }

  /**
   * 删除秒杀商品（管理员）
   */
  @DeleteMapping("/root/deleteSeckillGoodsById/{id}")
  @ResponseBody
  public Msg deleteSeckillGoodsById(@PathVariable("id") Integer id, HttpSession session) {
    Integer result = seckillGoodsService.deleteById(id);
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }

  //  根据id查询
  @GetMapping("/admin/getSeckillGoodsById/{id}")
  @ResponseBody
  public Msg getSeckillGoodsById(@PathVariable("id") Integer id, HttpSession session, Model model) {

    SeckillGoods seckillGoods = seckillGoodsService.selectById(id);

    //商品名称
    String productName = seckillGoods.getProductName();

    //商品的价格
    Double ProductPrices = seckillGoods.getProductPrices();
    //折后价格
    Double discount;

    Integer total = seckillOrderService.totalSeckillOrder(productName);
    if (total < 1) {
      discount = ProductPrices * 0.1;
    } else if (total < 2) {
      discount = ProductPrices * 0.5;
    } else if (total < 3) {
      discount = ProductPrices * 0.8;
    } else {
      discount = ProductPrices;
    }
    //格式化Double数据
    BigDecimal df = new BigDecimal(discount);
    seckillGoods.setProductPrices(df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    System.out.println(seckillGoods);

    session.setAttribute("total",total);
    session.setAttribute("seckillGoods", seckillGoods);

    return Msg.success().add("seckillGoods", seckillGoods);
  }

  /**
   * 更新用户信息时回显用户信息（管理员）
   */
  @GetMapping("/root/getSeckillGoodsById/{id}")
  @ResponseBody
  public Msg getSeckillGoodsInfoById(@PathVariable("id") Integer id) {
    SeckillGoods seckillGoods = seckillGoodsService.selectById(id);
    return Msg.success().add("seckillGoods", seckillGoods);
  }

  /**
   * 上传图片文件
   *
   * @param file
   * @return
   */
  @PostMapping("/imgUpload2")
  @ResponseBody
  public Map<String, Map<String, String>> imgUpload(MultipartFile file) {
    int uid = 1;
    Map<String, String> map1 = new HashMap<>();
    Map<String, Map<String, String>> map = new HashMap<>();
    try {
      //1.定义上传的文件
      String localPath = path;
      //2.获得文件名字
      String fileName = file.getOriginalFilename();
      //3.上传

      //3.1 生成新的文件名
      String photoName = FileNameUtils.getFileName(fileName);
      String realPath = localPath + "/" + photoName;
      String url = "/image/" + photoName;
      //3.2 保存文件
      File dest = new File(realPath);
      //判断文件目目录是否存在,不存在则新建
      if (!dest.getParentFile().exists()) {
        dest.getParentFile().mkdir();
      }
      file.transferTo(dest);
      System.out.println(fileName);
      Thread.sleep(500);
      //保存路径到数据库
      int maxId = seckillGoodsService.selectMaxId();
      SeckillGoods seckillGoods = new SeckillGoods();
      seckillGoods.setId(maxId);
      seckillGoods.setProductPic(url);
      seckillGoodsService.updatePic(seckillGoods);
      map1.put("file", "成功");
      map.put("files", map1);

    } catch (Exception e) {
      e.printStackTrace();

    }
    System.out.println(map);
    return map;
  }

  /**
   * 跳转到普通商品管理界面（普通用户）
   */
  @RequestMapping("/admin/toSeckillGoods.html")
  public String toSeckillGoods(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                               Model model, HttpSession session) {
    // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
    PageHelper.startPage(pageNum, pageSize);
    List<SeckillGoods> list = seckillGoodsService.selectAll();
    System.out.println(list);
    // 使用PageInfo包装查询后的结果，并交给页面处理
    // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
    PageInfo<SeckillGoods> pageInfo = new PageInfo<SeckillGoods>(list, 5);
    model.addAttribute("seckillGoodsPageInfo", pageInfo);
    model.addAttribute("seckillGoodsList", list);
    return "/admin/seckillGoods";
  }

  /**
   * 跳转到用户信息界面（管理员）
   */
  @RequestMapping("/root/toSeckillGoodsInfo.html")
  public String toSeckillGoodsInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   Model model, HttpSession session) {
    // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
    PageHelper.startPage(pageNum, pageSize);
    List<SeckillGoods> list = seckillGoodsService.selectAll();

    System.out.println(list);
    // 使用PageInfo包装查询后的结果，并交给页面处理
    // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
    PageInfo<SeckillGoods> pageInfo = new PageInfo<SeckillGoods>(list, 5);
    model.addAttribute("seckillGoodsPageInfo", pageInfo);
    model.addAttribute("seckillGoodsList", list);
    model.addAttribute("pageTopBarInfo", "秒杀商品管理界面");

    model.addAttribute("activeUrl1", "goods");
    model.addAttribute("activeUrl2", "seckillGoods");

    return "/root/seckillGoodsInfo";
  }

  /**
   * 更新用户信息()
   */
  @PutMapping("/root/updateSeckillGoodsProfile/{Id}")
  @ResponseBody
  public Msg updateSeckillGoodsProfile(@PathVariable("Id") Integer id, SeckillGoods seckillGoods, HttpSession session) {
    seckillGoods.setId(id);
    Integer result = seckillGoodsService.updateSeckillGoods(seckillGoods);
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }
}
