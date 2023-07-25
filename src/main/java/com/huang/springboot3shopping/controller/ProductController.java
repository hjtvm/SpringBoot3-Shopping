package com.huang.springboot3shopping.controller;

import com.huang.springboot3shopping.bean.Product;
import com.huang.springboot3shopping.bean.temporary;
import com.huang.springboot3shopping.mapper.ProductMapper;
import com.huang.springboot3shopping.mapper.UserMapper;
import com.huang.springboot3shopping.mapper.temporaryMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: SpringBoot3-Shopping
 * @BelongsPackage: com.huang.springboot3shopping.controller
 * @Author: HuangJinTao
 * @CreateTime: 2023-07-05  12:44
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class ProductController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    temporaryMapper TemporaryMapper;

    /**
     * 跳转到   添加商品   页面
     **/
    @GetMapping("addProduct")
    public String addProduct(){
        return "addProduct";
    }

    /**
     *  跳转到  修改商品  页面
     **/
    @GetMapping("/revise")
    public String revoke(@Param("id") Long id, Model model) {
        Product product = productMapper.getProductById(id);
        model.addAttribute("product", product);
        return "revise";
    }

    /**
     * 对进入商品页面的请求
     **/
    @GetMapping("/product")
    public String getProduct(Model model) {
        List<Product> products = productMapper.getProductByAll();
        model.addAttribute("roleId",1);
        model.addAttribute("products",products);
        return "Product";
    }

    @GetMapping("/product2")
    public String getProduct2(Model model) {
        List<Product> products = productMapper.getProductByAll();
        model.addAttribute("roleId",2);
        model.addAttribute("products",products);
        return "Product";
    }

    /**
     *  进行 删除商品   操作
     **/
    @GetMapping("/delete")
    public String delete(@Param("id") Long id, Model model) {
        model.addAttribute("roleId",1);
        int i = 0;
        try {
            i = productMapper.deleteProduct(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i>0){
            List<Product> products = productMapper.getProductByAll();
            model.addAttribute("products",products);
            return "Product";
        }else {
            return "";
        }
    }

    /**
     *  进行   修改商品   操作
     *  成功进入  商品页面
     *  失败留在   修改商品页面
     **/
    @RequestMapping(value = "/ProductAlteration",method = RequestMethod.POST)
    public String ProductAlteration(@RequestParam("id") Long id,
                                    @RequestParam("pId") int pId,
                                    @RequestParam("pName") String pName,
                                    @RequestParam("pPrice") String pPrice,
                                    @RequestParam("pCategory") String pCategory,
                                    @RequestParam("pStock") String pStock,
                                    Model model) {
        model.addAttribute("roleId",1);
        Product product = new Product(id,pId, pName, pPrice, pStock, pCategory);
        int i = 0;
        try {
            i = productMapper.updateProduct(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i>0){
            List<Product> products = productMapper.getProductByAll();
            model.addAttribute("products",products);
            return "Product";
        }else {
            Product product2 = productMapper.getProductById(id);
            model.addAttribute("product", product2);
            model.addAttribute("msg","商品账号已存在！请重新修改商品信息");
            return "revise";
        }
    }

    /**
     * 进行   添加商品   操作
     * 成功进入  商品页面
     * 失败留在   添加商品页面
     **/
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@RequestParam("pId") int pId,
                      @RequestParam("pName") String pName,
                      @RequestParam("pPrice") String pPrice,
                      @RequestParam("pCategory") String pCategory,
                      @RequestParam("pStock") String pStock,
                      Model model) {
        model.addAttribute("roleId", 1);
        int i = 0;
        List<Integer> id = productMapper.getProductByPId();
        if(id.contains(pId)){
            model.addAttribute("msg","商品账号已存在！请重新添加商品信息");
            return "addProduct";
        }
        try {
            i = productMapper.addProduct(pId, pName, pPrice, pCategory, pStock);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i>0){
            List<Product> products = productMapper.getProductByAll();
            model.addAttribute("products",products);
            return "Product";
        }else {
            model.addAttribute("msg","添加商品失败！请重新添加商品信息");
            return "addProduct";
        }
    }

    /**
     * 进行   查询商品   操作
     * 都进入  商品页面
     **/
    @RequestMapping(value = "/selectProduct",method = RequestMethod.POST)
    public String selectProduct(@RequestParam("roleId") int roleId,
                                @RequestParam("pId") String pId,
                                @RequestParam("pName") String pName,
                                @RequestParam("pPrice") String pPrice,
                                @RequestParam("pCategory") String pCategory,
                                @RequestParam("pStock") String pStock,
                                Model model){
        List<Product> products = null;
        try {
            products = productMapper.selectProduct(pId, pName, pPrice, pCategory, pStock);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("products",products);
        model.addAttribute("roleId",roleId);
        return "Product";
    }

    /**
     * 进行跳转  购物车  页面
     **/
    @GetMapping("/shopping")
    public String getShopping(Model model){
        List<temporary> temporaries = TemporaryMapper.selectAllTemporary();
        model.addAttribute("temporaries",temporaries);
        return "shoppingCarts";
    }

    /**
     * 移除购物车操作
     **/
    @GetMapping(value = "/deleteTemporary")
    public String deleteTemporary(@RequestParam("id") int id,Model model){
        int i = 0;
        try{
            i = TemporaryMapper.deleteTemporary(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<temporary> temporaries = TemporaryMapper.selectAllTemporary();
        model.addAttribute("temporaries",temporaries);
        return "shoppingCarts";
    }


}
