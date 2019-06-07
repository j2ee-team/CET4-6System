package com.example.enlistenglish.demo.entity;



import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity//表明这是一个实体类
@Table(name = "Enlist")//Table指定数据库中的表名
@EntityListeners(AuditingEntityListener.class)//对实体属性变化进行跟踪
public class Enlist {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id自动增长
    @Column(name = "enlist_id",length = 11)
    private Long enlistId;  //报名表Id
    @Column(name = "enlist_level",length = 10)
    private String enlistLevel;//报名等级
    @Column(name = "enlist_is_pay",length = 1)
    private String enlistIsPay;//是否支付
    @Column(name = "user_mes_id",length = 11)
    private Long userMesId;//考生信息Id
    @Column(name = "exam_id",length = 11)
    private Long examId;//考点信息Id
    @Column(name = "remark",length = 100)
    private String remark;

    public Long getEnlistId() {
        return enlistId;
    }

    public void setEnlistId(Long enlistId) {
        this.enlistId = enlistId;
    }

    public String getEnlistLevel() {
        return enlistLevel;
    }

    public void setEnlistLevel(String enlistLevel) {
        this.enlistLevel = enlistLevel;
    }

    public String getEnlistIsPay() {
        return enlistIsPay;
    }

    public void setEnlistIsPay(String enlistIsPay) {
        this.enlistIsPay = enlistIsPay;
    }

    public Long getUserMesId() {
        return userMesId;
    }

    public void setUserMesId(Long userMesId) {
        this.userMesId = userMesId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}