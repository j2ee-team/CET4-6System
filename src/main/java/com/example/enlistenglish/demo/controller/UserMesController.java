package com.example.enlistenglish.demo.controller;


import com.example.enlistenglish.demo.Repo.UserMesRepo;
import com.example.enlistenglish.demo.Repo.UserRepo;
import com.example.enlistenglish.demo.entity.User_mes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class UserMesController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMesRepo userMesRepo;
    //跳转到个人信息界面

    @RequestMapping(value = "/updateInfo/{username}")
    public String sessionTest(HttpSession session, HttpServletRequest request, @PathVariable String username, Model model) {
        User_mes mes = userMesRepo.findByUserName(username);
//        System.out.println("注册:"+request.getSession().getAttribute("username"));
        model.addAttribute("username",username);
        model.addAttribute("mes",mes);
        return "Userinfo";
    }

    @PutMapping("/updateInfo/updateMes")
    public String updateMes(User_mes user_mes, Model model, MultipartFile picUp){
        String resultStr = "个人信息修改成功！";
        String hrefStr = "/";
        //对个人图片进行上传
        if (!picUp.isEmpty()) {
            //获取文件名
            String fileName = picUp.getOriginalFilename();
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID()+suffixName;
            //指定本地文件夹存储图片
            String filePath = "E:/EnlishProject/src/main/resources/static/img/";
            try {
                //将图片保存到static文件夹里
                picUp.transferTo(new File(filePath+fileName));
                user_mes.setUserMesPic("/static/img/"+fileName);
            } catch (Exception e) {
                resultStr = "个人图片修改失败！";
                e.printStackTrace();
            }
        }
        else {
            user_mes.setUserMesPic(userMesRepo.findByUserName(user_mes.getUserName()).getUserMesPic());
        }
        User_mes user_mesinfo = userMesRepo.save(user_mes);
        if (user_mesinfo==null)
        {
            resultStr = "个人信息修改失败！";
            model.addAttribute("result", resultStr);
            model.addAttribute("hre", hrefStr);
        }
        model.addAttribute("result", resultStr);
        model.addAttribute("hre", hrefStr);
        return "success";
    }
}
