package com.example.enlistenglish.demo.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity//表明这是一个实体类
@Table(name = "Admin")//Table指定数据库中的表名
@EntityListeners(AuditingEntityListener.class)//对实体属性变化进行跟踪
public class Admin {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id自动增长
    @Column(name = "admin_id",length = 11)
    private Long adminId;  //管理员Id
    @Column(name = "admin_name",length = 20)
    private String adminName;
    @Column(name = "admin_password",length = 32)
    private String adminPassword;
    @Column(name = "admin_limit",length = 10)
    private String adminLimit;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminLimit() {
        return adminLimit;
    }

    public void setAdminLimit(String adminLimit) {
        this.adminLimit = adminLimit;
    }
}
