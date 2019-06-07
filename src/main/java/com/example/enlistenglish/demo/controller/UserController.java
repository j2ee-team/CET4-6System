package com.example.enlistenglish.demo.controller;

import com.example.enlistenglish.demo.Repo.UserRepo;
import com.example.enlistenglish.demo.entity.Exam;
import com.example.enlistenglish.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;
    //来到考点信息新增界面
    @GetMapping("/admin/adduser")
    public String Toaddexam(){
        return "/admin/useradd";
    }

    //来到修改页面
    @RequestMapping(value = "/admin/adduser/{id}")
    public String toEditexam(@PathVariable Long id, Model model) {
        User user = userRepo.findByUserId(id);
        model.addAttribute("user", user);
        return "/admin/useradd";
    }


    //用户密码修改
    @PutMapping(value = "/admin/adduser")
    public String updateUser(User user,Model model) throws NoSuchAlgorithmException {
        String resultStr = "用户密码修改成功！";
        String hrefStr = "/admin/userinfo";
        //对密码进行加密
        MessageDigest md = MessageDigest.getInstance("MD5");
        String password = user.getUserPassword();
        md.update(password.getBytes());
        password = new BigInteger(1,md.digest()).toString(16);

        user.setUserPassword(password);
        User userinfo = userRepo.save(user);
        if (userinfo == null)
            resultStr = "用户密码修改出错";
        model.addAttribute("result", resultStr);
        model.addAttribute("hre", hrefStr);
        return "/success";
    }

    //考点信息删除
    @DeleteMapping(value = "/admin/user/{id}")
    public String updteExam(@PathVariable("id") Long id,Model model){
        String resultStr = "用户信息删除成功！";
        String hrefStr = "/admin/userinfo";
        try {
            userRepo.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            resultStr = "用户信息删除失败！";
            model.addAttribute("result", resultStr);
            model.addAttribute("hre", hrefStr);
            return "/success";
        }
        model.addAttribute("result", resultStr);
        model.addAttribute("hre", hrefStr);
        return "/success";
    }
}
