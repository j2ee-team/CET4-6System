package com.example.enlistenglish.demo.controller;

import com.example.enlistenglish.demo.Repo.EnlistRepo;
import com.example.enlistenglish.demo.Repo.ExamRepo;
import com.example.enlistenglish.demo.Repo.UserMesRepo;
import com.example.enlistenglish.demo.entity.Enlist;
import com.example.enlistenglish.demo.entity.Exam;
import com.example.enlistenglish.demo.entity.User_mes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EnlistController {
    @Autowired
    private EnlistRepo enlistRepo;
    @Autowired
    private UserMesRepo userMesRepo;
    @Autowired
    private ExamRepo examRepo;

    //来到报名身份确认页面
    @RequestMapping("/enlistInfo/{username}")
    public String ToEnlistInfo(@PathVariable String username, Model model){
        User_mes mes = userMesRepo.findByUserName(username);
        model.addAttribute("mes",mes);
        return "Enlist";
    }

    //来到选择报名等级页面
    @RequestMapping("/choose")
    public String ToEnlist(){
        return "EnlistChoose";
    }

    //来到四级报名页面
    @RequestMapping("/choose/four")
    public String ToFour(HttpServletRequest request, Model model){
        String username = (String) request.getSession().getAttribute("username");
        List<Exam> exams = examRepo.findExamByExamLevel("四级考试");
        User_mes mes = userMesRepo.findByUserName(username);
        model.addAttribute("mes",mes);
        model.addAttribute("level","四级考试");
        model.addAttribute("exams",exams);
        return "Enlist";
    }

    //来到六级报名页面
    @RequestMapping("/choose/six")
    public String ToSix(HttpServletRequest request,Model model){
        String username = (String) request.getSession().getAttribute("username");
        List<Exam> exams = examRepo.findExamByExamLevel("六级考试");
        User_mes mes = userMesRepo.findByUserName(username);
        model.addAttribute("mes",mes);
        model.addAttribute("level","六级考试");
        model.addAttribute("exams",exams);
        return "Enlist";
    }

    @PostMapping("/enlist")
    public String ExamEnlist(Enlist enlist,Model model){
        String resultStr = "你已成功报名！";
        String hrefStr = "/";
        Enlist enlistinfo = enlistRepo.findByUserMesId(enlist.getUserMesId());
        if (enlistinfo!=null){
            resultStr = "你已经参与过了报名！";
            model.addAttribute("result",resultStr);
            model.addAttribute("hre",hrefStr);
            return "success";
        }
        enlist.setEnlistIsPay("Y");
        Enlist enlist1 =  enlistRepo.save(enlist);
        if (enlist1 == null)
        {
            resultStr = "报名失败！";
        }
        model.addAttribute("result",resultStr);
        model.addAttribute("hre",hrefStr);
        return "success";
    }

    //来到报名信息查询页面,查看报名后的信息
    @RequestMapping("/findenlist")
    public String ToEnlistInfo(HttpServletRequest request, Model model){
        String username = (String) request.getSession().getAttribute("username");
        try
        {
            User_mes mes = userMesRepo.findByUserName(username);
            Enlist enlist = enlistRepo.findByUserMesId(mes.getUserMesId());
            Exam exam = examRepo.findByExamId(enlist.getExamId());
            model.addAttribute("mes",mes);
            model.addAttribute("enlist",enlist);
            model.addAttribute("exam",exam);
        }catch (Exception e){
            model.addAttribute("point","您还未进行报名，请先去报名！");
        }
        return "enlistedinfo";
    }

}
