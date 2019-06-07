package com.example.enlistenglish.demo.controller;

import com.example.enlistenglish.demo.Repo.EnlistRepo;
import com.example.enlistenglish.demo.Repo.ExamRepo;
import com.example.enlistenglish.demo.Repo.UserMesRepo;
import com.example.enlistenglish.demo.Repo.UserRepo;
import com.example.enlistenglish.demo.config.WebSecurityConfig;
import com.example.enlistenglish.demo.entity.*;
import com.example.enlistenglish.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserMesRepo userMesRepo;
    @Autowired
    private EnlistRepo enlistRepo;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/admin")
    public String Toadmin(){
        return "adminlogin";
    }


    //登录核实
    @PostMapping("/adminlogin")
    public String loginVerify(String username, String password, HttpSession session, Model model) throws NoSuchAlgorithmException {
        Admin admin = new Admin();
        admin.setAdminName(username);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        password = new BigInteger(1,md.digest()).toString(16);

        admin.setAdminPassword(password);
        //判断是否登录
        boolean verify = loginService.adminLogin(admin);
        if (verify) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            model.addAttribute("username",username);
            //登录成功返回首页
            return "redirect:/admin/studentinfo";
        }else {
            //如果登录失败则返回登录界面
            return "redirect:/admin";
        }
    }

    //来到学生信息页面
    @GetMapping("/admin/studentinfo")
    public String ToStudentinfo(Model model, HttpServletRequest request ){
        Iterable<User_mes> meses = userMesRepo.findAll();
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        model.addAttribute("meses",meses);
        return "/admin/studentinfo";
    }

    //查询学生信息
    @PostMapping("/admin/findStudent")
    public String findStudent(Model model,HttpServletRequest request,String mesStr){
        List<User_mes> meses = userMesRepo.queryStudent(mesStr);
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        model.addAttribute("meses",meses);
        return "/admin/studentinfo";
    }

    //来到报名管理页面
    @GetMapping("/admin/enlistinfo")
    public String ToEnlistinfo(Model model,HttpServletRequest request){
        List<User_mes> mes = (List<User_mes>) userMesRepo.findAll();
        List<Enlist> enlists = (List<Enlist>) enlistRepo.findAll();
        for(int i=0;i<enlists.size();i++){
            for (int j=0;j<mes.size();j++)
            {
                if (enlists.get(i).getUserMesId()==mes.get(j).getUserMesId()){
                    enlists.get(i).setRemark(mes.get(j).getUserMesName());
                }
            }
        }
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        model.addAttribute("enlists",enlists);
        return "/admin/enlistinfo";
    }

    //查询符合条件的报名信息
    @PostMapping("/admin/findenlist")
    public String findenlist(Model model,HttpServletRequest request,String level){
        List<Enlist> enlists;
        if (level.equalsIgnoreCase("所有"))
        {
            enlists = (List<Enlist>) enlistRepo.findAll();
        }else{
            enlists = enlistRepo.findByEnlistLevel(level);
        }
        List<User_mes> mes = (List<User_mes>) userMesRepo.findAll();
        for(int i=0;i<enlists.size();i++){
            for (int j=0;j<mes.size();j++)
            {
                if (enlists.get(i).getUserMesId()==mes.get(j).getUserMesId()){
                    enlists.get(i).setRemark(mes.get(j).getUserMesName());
                }
            }
        }
        model.addAttribute("enlists",enlists);
        return "/admin/enlistinfo";
    }

    //来到考点管理页面
    @GetMapping("/admin/examinfo")
    public String Toexaminfo(Model model,HttpServletRequest request){
        Iterable<Exam> exams = examRepo.findAll();
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        model.addAttribute("exams",exams);
        return "/admin/examinfo";
    }

    //来到考点信息新增界面
    @GetMapping("/admin/addexam")
    public String Toaddexam(){
        return "/admin/examadd";
    }

    //来到修改页面
    @RequestMapping(value = "/admin/addexam/{id}")
    public String toEditexam(@PathVariable Long id, Model model) {
        Exam exam = examRepo.findByExamId(id);
        model.addAttribute("exam", exam);
        return "/admin/examadd";
    }

    //考点信息添加
    @PostMapping(value = "/admin/addexam")
    public String addExam(Exam exam,Model model){
        String resultStr = "考点信息添加成功！";
        String hrefStr = "/admin/examinfo";
        Exam examinfo = examRepo.save(exam);
        if (examinfo == null)
            resultStr = "考点信息添加出错";
        model.addAttribute("result", resultStr);
        model.addAttribute("hre", hrefStr);
        return "/success";
    }

    //考点信息修改
    @PutMapping(value = "/admin/addexam")
    public String updteExam(Exam exam,Model model){
        String resultStr = "考点信息修改成功！";
        String hrefStr = "/admin/examinfo";
        Exam examinfo = examRepo.save(exam);
        if (examinfo == null)
            resultStr = "考点信息修改出错";
        model.addAttribute("result", resultStr);
        model.addAttribute("hre", hrefStr);
        return "/success";
    }

    //考点信息删除
    @DeleteMapping(value = "/admin/exam/{id}")
    public String updteExam(@PathVariable("id") Long id,Model model){
        String resultStr = "考点信息删除成功！";
        String hrefStr = "/admin/examinfo";
        try {
            examRepo.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            resultStr = "考点信息删除失败！";
            model.addAttribute("result", resultStr);
            model.addAttribute("hre", hrefStr);
            return "/success";
        }
        model.addAttribute("result", resultStr);
        model.addAttribute("hre", hrefStr);
        return "/success";
    }

    //查询符合条件的考点信息
    @PostMapping("/admin/findexam")
    public String findExam(String mesStr,Model model,HttpServletRequest request){
        List<Exam> exams = examRepo.queryExam(mesStr);
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        model.addAttribute("exams",exams);
        return "/admin/examinfo";
    }


    //来到用户管理页面
    @GetMapping("/admin/userinfo")
    public String Touserinfo(Model model,HttpServletRequest request){
        Iterable<User> users = userRepo.findAll();
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        model.addAttribute("users",users);
        return "/admin/userinfo";
    }

    //查询用户
    @PostMapping("/admin/finduser")
    public String findUser(Model model,HttpServletRequest request,String mesStr){
        Iterable<User> users = userRepo.queryUsers(mesStr);
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        model.addAttribute("users",users);
        return "/admin/userinfo";
    }
}
