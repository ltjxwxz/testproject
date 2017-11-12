package com.wxx.thymeleaftest.controller;

import com.wxx.thymeleaftest.dao.UserDao;
import com.wxx.thymeleaftest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wxx on 2017/11/12.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    /*
     * 查询所有用户
     */
    @RequestMapping("/listUsers")
    public ModelAndView listUsers(Model model) {
        model.addAttribute("userList", userDao.listUsers());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    /*
    * 根据id查询用户
    */
    @GetMapping("/getUserById/{id}")
    public ModelAndView getUserById(@PathVariable("id")Long id, Model model) {
        User user = userDao.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /*
     * 获取创建表单页面
     */
    @GetMapping("/createForm")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    /*
     * 保存或者修改用户
     */
    @PostMapping("/saveOrUpdateUser")
    public ModelAndView saveOrUpdateUser(User user, Model model) {
        userDao.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/users/listUsers");
    }

    /*
     * 删除用户
     */
    @GetMapping("/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable("id")Long id, Model model) {
        userDao.deleteUser(id);
        return new ModelAndView("redirect:/users/listUsers");
    }

    /*
     * 修改用户
     */
    @GetMapping("/modifyUser/{id}")
    public ModelAndView modifyUser(@PathVariable("id")Long id, Model model) {
        User user = userDao.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/form", "userModel", model);
    }







}
