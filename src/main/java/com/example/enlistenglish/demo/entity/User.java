package com.example.enlistenglish.demo.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity//表明这是一个实体类
@Table(name = "User")//Table指定数据库中的表名
@EntityListeners(AuditingEntityListener.class)//对实体属性变化进行跟踪
public class User {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id自动增长
    @Column(name = "user_id",length = 11)
    private Long userId;  //考生Id
    @Column(name = "user_name",length = 20)
    private String userName;//考生用户名
    @Column(name = "user_password",length = 32)
    private String userPassword;//考生密码

    @OneToOne
    private User_mes userMes;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public User_mes getUserMes() {
        return userMes;
    }

    public void setUserMes(User_mes userMes) {
        this.userMes = userMes;
    }


}
