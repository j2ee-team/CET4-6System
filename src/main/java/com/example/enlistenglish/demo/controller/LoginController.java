package com.example.enlistenglish.demo.controller;

import com.example.enlistenglish.demo.Repo.UserMesRepo;
import com.example.enlistenglish.demo.Repo.UserRepo;
import com.example.enlistenglish.demo.config.WebSecurityConfig;
import com.example.enlistenglish.demo.entity.User;
import com.example.enlistenglish.demo.entity.User_mes;
import com.example.enlistenglish.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMesRepo userMesRepo;
    //如果已经登录了，则回到主页
    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account, Model model,HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        return "index";
    }

    //跳转到登录页面
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    public String toMD5(String password) throws NoSuchAlgorithmException {
        //MD5加密
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        password = new BigInteger(1,md.digest()).toString(16);
        return password;
    }

    //登录核实
    @PostMapping("/loginVerify")
    public String loginVerify(String username, String password, HttpSession session,Model model) throws NoSuchAlgorithmException {
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(toMD5(password));

        //判断是否登录
        boolean verify = loginService.verifyLogin(user);
        if (verify) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            model.addAttribute("username",username);
            //登录成功返回首页
            return "index";
        }else {
            //如果登录失败则返回登录界面
            return "redirect:/login";
        }
    }

    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

    //注册
    @PostMapping("/register")
    public String registerUser(Model model,String username, String password,String userMesName,Integer userMesGender,String userMesTel,String userMesEmail) throws NoSuchAlgorithmException {
        String resultStr = "用户注册成功！";
        String hrefStr = "/login";
        List<User> userList = userRepo.findByUserName(username);
        //如果用户名未被注册
        if (userList.isEmpty())
        {
            User user = new User();
            user.setUserName(username);

            //MD5加密
            user.setUserPassword(toMD5(password));

            User_mes user_mes =new User_mes();
            user_mes.setUserName(username);
            user_mes.setUserMesEmail(userMesEmail);
            user_mes.setUserMesTel(userMesTel);
            user_mes.setUserMesName(userMesName);
            user_mes.setUserMesGender(userMesGender);
            User userinfo = userRepo.save(user);
            User_mes userMesinfo = userMesRepo.save(user_mes);
            if (userinfo==null||userMesinfo==null){
                resultStr = "用户注册成功！";
            }
            model.addAttribute("result",resultStr);
            model.addAttribute("hre",hrefStr);
        }
        //如果用户名已经存在
        else {
            resultStr = "该用户名已被注册！";
            model.addAttribute("result",resultStr);
            model.addAttribute("hre",hrefStr);
        }
        return "success";
    }
}
