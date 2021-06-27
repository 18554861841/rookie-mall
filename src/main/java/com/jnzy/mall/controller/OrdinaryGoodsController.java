package com.jnzy.mall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnzy.mall.common.Msg;
import com.jnzy.mall.pojo.OrdinaryGoods;
import com.jnzy.mall.service.OrdinaryGoodsService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 */
@Controller
public class OrdinaryGoodsController {

  @Autowired
  OrdinaryGoodsService ordinaryGoodsService;
  @Value("${web.upload-path}")
  private String path;
  private ResourceLoader resourceLoader;

  public OrdinaryGoodsController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @Autowired
  public void PhotoController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  /**
   * 添加用户（管理员）
   */
  @PostMapping("/root/addOrdinaryGoods")
  @ResponseBody
  public Msg addOrdinaryGoods(OrdinaryGoods ordinaryGoods) {
    Integer result = ordinaryGoodsService.insertOrdinaryGoods(ordinaryGoods);
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }

  /**
   * 删除商品（管理员）
   */
  @DeleteMapping("/root/deleteOrdinaryGoodsById/{id}")
  @ResponseBody
  public Msg deleteOrdinaryGoodsById(@PathVariable("id") Long id, HttpSession session) {
    Integer result = ordinaryGoodsService.deleteById(id);
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }

  /**
   * 更新用户信息时回显用户信息（管理员）
   */
  @GetMapping("/root/getOrdinaryGoodsById/{id}")
  @ResponseBody
  public Msg getOrdinaryGoodsInfoById(@PathVariable("id") Long id) {
    OrdinaryGoods ordinaryGoods = ordinaryGoodsService.selectById(id);
    return Msg.success().add("ordinaryGoods", ordinaryGoods);
  }

  /**
   * 上传图片文件
   *
   * @param file
   * @return
   */
  @PostMapping("/imgUpload")
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
      Long maxId = ordinaryGoodsService.selectMaxId();
      OrdinaryGoods ordinaryGoods = new OrdinaryGoods();
      ordinaryGoods.setId(maxId);
      ordinaryGoods.setProductPic(url);
      ordinaryGoodsService.updatePic(ordinaryGoods);
      map1.put("file", "成功");
      map.put("files", map1);

    } catch (Exception e) {
      e.printStackTrace();

    }
    System.out.println(map);
    return map;
  }

  /**
   * 跳转到普通商品管理界面（管理员）
   */
  @RequestMapping("/root/toOrdinaryGoodsInfo.html")
  public String toOrdinaryGoodsInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                    Model model, HttpSession session) {
    // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
    PageHelper.startPage(pageNum, pageSize);
    List<OrdinaryGoods> list = ordinaryGoodsService.selectAll();

    System.out.println(list);
    // 使用PageInfo包装查询后的结果，并交给页面处理
    // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
    PageInfo<OrdinaryGoods> pageInfo = new PageInfo<OrdinaryGoods>(list, 5);
    model.addAttribute("ordinaryGoodsPageInfo", pageInfo);
    model.addAttribute("ordinaryGoodsList", list);
    model.addAttribute("pageTopBarInfo", "普通商品管理界面");

    model.addAttribute("activeUrl1", "goods");
    model.addAttribute("activeUrl2", "ordinaryGoods");

    return "root/ordinaryGoodsInfo";
  }

  /**
   * 更新用户信息()
   */
  @PutMapping("/root/updateOrdinaryGoodsProfile/{Id}")
  @ResponseBody
  public Msg updateOrdinaryGoodsProfile(@PathVariable("Id") Long id, OrdinaryGoods ordinaryGoods, HttpSession session) {
    ordinaryGoods.setId(id);
    Integer result = ordinaryGoodsService.updateOrdinaryGoods(ordinaryGoods);
    if (result == 1) {
      return Msg.success();
    }
    return Msg.fail();
  }

  /**
   * 跳转到普通商品管理界面（普通用户）
   */
  @RequestMapping("/admin/toOrdinaryGoods.html")
  public String toOrdinaryGoods(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                    Model model, HttpSession session) {
    // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
    PageHelper.startPage(pageNum, pageSize);
    List<OrdinaryGoods> list = ordinaryGoodsService.selectAll();

    System.out.println(list);
    // 使用PageInfo包装查询后的结果，并交给页面处理
    // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
    PageInfo<OrdinaryGoods> pageInfo = new PageInfo<OrdinaryGoods>(list, 5);
    model.addAttribute("ordinaryGoodsPageInfo", pageInfo);
    model.addAttribute("ordinaryGoodsList", list);
    return "admin/ordinaryGoods";
  }

//  根据id查询
  @GetMapping("/admin/getOrdinaryGoodsById/{id}")
  @ResponseBody
  public Msg getOrdinaryGoodsById(@PathVariable("id") Long id, HttpSession session, Model model) {
    OrdinaryGoods ordinaryGoods = ordinaryGoodsService.selectById(id);
    System.out.println(ordinaryGoods);
    session.setAttribute("ProductPrices",ordinaryGoods.getProductPrices());
    return Msg.success().add("ordinaryGoods", ordinaryGoods);
  }

}
