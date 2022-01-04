package com.wyh.controller;

import com.wyh.mapper.UserMapper;
import com.wyh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UserMapper mapper;
    @RequestMapping("/login")
    public String login(@RequestParam("userNo") String userNo, @RequestParam("password") String password, Model model, HttpSession session){
        User user = mapper.getUserById(Integer.parseInt(userNo));
        if (user!=null && user.getPwd().equals(password)){
            session.setAttribute("userNo",userNo);
            session.setAttribute("level",user.getLevel());
            return "redirect:/book";
        }else{
            model.addAttribute("msg","用户名或密码错误");
            return "/index";
        }
    }
    @RequestMapping("/")
    public String toLogin(){
        return "index";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/index";
    }
}
