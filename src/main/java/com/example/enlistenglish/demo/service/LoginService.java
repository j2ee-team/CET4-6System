package com.example.enlistenglish.demo.service;



import com.example.enlistenglish.demo.Repo.AdminRepo;
import com.example.enlistenglish.demo.Repo.UserRepo;
import com.example.enlistenglish.demo.entity.Admin;
import com.example.enlistenglish.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AdminRepo adminRepo;

    //判断是否登录
    public  boolean verifyLogin(User user){
        List<User> userList = userRepo.findByUserNameAndAndUserPassword(user.getUserName(),user.getUserPassword());
        return userList.size()>0;
    }

    public boolean adminLogin(Admin admin){
        List<Admin> adminList = adminRepo.findByAdminNameAndAdminPassword(admin.getAdminName(),admin.getAdminPassword());
        return adminList.size()>0;
    }
}
