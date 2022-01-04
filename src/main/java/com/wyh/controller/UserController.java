package com.wyh.controller;

import com.wyh.mapper.UserMapper;
import com.wyh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class UserController {
    @Autowired
    private UserMapper mapper;

    @RequestMapping("/user")
    public String toUser(Model model){
        List<User> userList = mapper.getUserList();
        model.addAttribute("users",userList);
        return "user/list";
    }
    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "user/add";
    }
    @RequestMapping("/addUser")
    public String addUser(User user){
        mapper.addUser(user);
        return "redirect:user";
    }
    @RequestMapping("/searchUser")
    public String searchUser(String userName, Model model){
        List<User> userList = mapper.getUserByName(userName);
        model.addAttribute("users",userList);
        return "user/list";
    }
    @RequestMapping("/toUpdateUser/{id}")
    public String toUpdate(@PathVariable("id") int id, Model model){
        User user = mapper.getUserById(id);
        model.addAttribute("user",user);
        return "user/update";
    }
    @RequestMapping("/updateUser")
    public String update(User user){
        mapper.updateUser(user);
        return "redirect:user";
    }
}
