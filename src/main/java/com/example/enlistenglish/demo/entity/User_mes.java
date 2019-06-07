package com.example.enlistenglish.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity//表明这是一个实体类
@Table(name = "User_mes")//Table指定数据库中的表名
@EntityListeners(AuditingEntityListener.class)//对实体属性变化进行跟踪
public class User_mes {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id自动增长
    @Column(name = "user_mes_id",length = 11)
    private Long userMesId;  //考生信息Id
    @Column(name = "user_mes_name",length = 20)
    private String userMesName;//考生名称
    @Column(name = "user_mes_gender",length = 1)
    private Integer userMesGender;//考生性别
    @Column(name = "user_mes_tel",length = 11)
    private String userMesTel;//考生电话
    @Column(name = "user_mes_pic",length = 100)
    private String userMesPic;//考生照片路径
    @Column(name = "user_mes_card",length = 18)
    private String userMesCard;//考生身份证号
    @Column(name = "user_mes_birth")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date userMesBirth;//考生出生日期
    @Column(name ="user_mes_email",length = 100)
    private String userMesEmail;//考生电子邮箱
    @Column(name ="username",length = 20)
    private String userName;//考生登录账号
    @Column(name = "user_mes_school",length = 100)
    private String userMesSchool;//考生就读大学

    public Long getUserMesId() {
        return userMesId;
    }

    public void setUserMesId(Long userMesId) {
        this.userMesId = userMesId;
    }

    public String getUserMesName() {
        return userMesName;
    }

    public void setUserMesName(String userMesName) {
        this.userMesName = userMesName;
    }

    public Integer getUserMesGender() {
        return userMesGender;
    }

    public void setUserMesGender(Integer userMesGender) {
        this.userMesGender = userMesGender;
    }

    public String getUserMesTel() {
        return userMesTel;
    }

    public void setUserMesTel(String userMesTel) {
        this.userMesTel = userMesTel;
    }

    public String getUserMesPic() {
        return userMesPic;
    }

    public void setUserMesPic(String userMesPic) {
        this.userMesPic = userMesPic;
    }

    public String getUserMesCard() {
        return userMesCard;
    }

    public void setUserMesCard(String userMesCard) {
        this.userMesCard = userMesCard;
    }

    public Date getUserMesBirth() {
        return userMesBirth;
    }

    public void setUserMesBirth(Date userMesBirth) {
        this.userMesBirth = userMesBirth;
    }

    public String getUserMesEmail() {
        return userMesEmail;
    }

    public void setUserMesEmail(String userMesEmail) {
        this.userMesEmail = userMesEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMesSchool() {
        return userMesSchool;
    }

    public void setUserMesSchool(String userMesSchool) {
        this.userMesSchool = userMesSchool;
    }
}
