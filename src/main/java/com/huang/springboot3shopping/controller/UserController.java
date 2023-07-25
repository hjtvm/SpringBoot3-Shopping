package com.huang.springboot3shopping.controller;

import com.huang.springboot3shopping.mapper.temporaryMapper;
import org.springframework.ui.Model;
import com.huang.springboot3shopping.bean.Product;
import com.huang.springboot3shopping.bean.User;
import com.huang.springboot3shopping.mapper.ProductMapper;
import com.huang.springboot3shopping.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: SpringBoot3-Shopping
 * @BelongsPackage: com.huang.springboot3shopping.controller
 * @Author: HuangJinTao
 * @CreateTime: 2023-07-03  18:49
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    temporaryMapper TemporaryMapper;
    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 用户退出登录
     **/
    @GetMapping("/logout")
    public String logout() {
        try{
            TemporaryMapper.deleteAllTemporary();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "login";
    }

    /**
     * 进行对   登录    判断
     * 成功进入  商品页面（权限不同，进入不同的页面）
     * 失败留在  登录页面
     **/
    @RequestMapping(value = "/authentication",method = RequestMethod.POST)
    public String loginAuthentication(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      Model model) {
        User user = userMapper.getUserByName(username);
        if(user!=null&&user.getPassword().equals(password)){
            Integer id = user.getRoleId();
            List<Product> productByAll = productMapper.getProductByAll();
            model.addAttribute("products",productByAll);
            model.addAttribute("roleId",id);
            return "/Product";
        }else {
            model.addAttribute("msg","账号或者密码错误!");
            return "/login";
        }
    }

    /**
     * 进行对   注册用户
     * 成功进入  商品页面（权限不同，进入不同的页面）
     * 失败留在  注册页面
     **/
    @RequestMapping(value = "/authen",method = RequestMethod.POST)
    public String loginAuthentication1(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("roleId") int roleId,
                                       Model model) {
        model.addAttribute("roleId",1);
        List<Product> products = productMapper.getProductByAll();
        model.addAttribute("products",products);
        User user = userMapper.getUserByName(username);
        if (user==null){
            int i = userMapper.setUser(username,password,roleId);
            if (i>0){
                List<User> users = userMapper.getUsers();
                model.addAttribute("roleId",1);
                model.addAttribute("Users",users);
                return "/usersList";
            }else {
                model.addAttribute("msg","注册失败! 请重新注册用户信息");
                return "/register";
            }
        }else {
            model.addAttribute("msg","账号已存在! 请重新注册用户信息");
            return "/register";
        }
    }

    /**
     * 跳转到用户列表
     **/
    @GetMapping("/users")
    public String Users(Model model) {
        List<User> users = userMapper.getUsers();
        model.addAttribute("roleId",1);
        model.addAttribute("Users",users);
        return "usersList";
    }

    /**
     * 跳转到修改用户页面
     **/
    @GetMapping("/reviseUser")
    public String reviseUser(@RequestParam("id") int id,Model model){
        User user = userMapper.selectUserById(id);
        model.addAttribute("user",user);
        return "reviseuser";
    }

    /**
     * 进行   修改用户   操作
     * 成功进入  用户列表页面
     * 失败留在  修改用户页面
     **/
    @RequestMapping(value = "/getbyuser",method = RequestMethod.POST)
    public String getByUser(@RequestParam("id") int id,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("roleId") int roleId,
                            Model model){
        model.addAttribute("id", id);
        User user = new User(id, username, password, roleId);
        int i = 0;
        try{
            i = userMapper.updateUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i>0){
            List<User> users = userMapper.getUsers();
            model.addAttribute("Users",users);
            return "usersList";
        }else {
            User user1 = userMapper.selectUserById(id);
            model.addAttribute("user",user1);
            model.addAttribute("msg","修改用户信息失败! 请重新修改用户信息");
            return "reviseuser";
        }
    }

    /**
     *  进行 删除用户  操作
     **/
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id,Model model){
        model.addAttribute("roleId",1);
        int i = 0;
        try{
            i = userMapper.deleteUser(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i>0){
            List<User> users = userMapper.getUsers();
            model.addAttribute("Users",users);
            return "usersList";
        }else{
            return "";
        }
    }

    /**
     * 查询用户信息
     **/
    @RequestMapping(value = "/selectUser",method = RequestMethod.POST)
    public String selectProduct(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("roleId") String roleId,
                                Model model){
        List<User> users = null;
        try {
            users = userMapper.selectUser(username, password, roleId);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("roleId",1);
        model.addAttribute("Users",users);
        return "usersList";
    }
}
