package com.example.enlistenglish.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity//表明这是一个实体类
@Table(name = "Exam")//Table指定数据库中的表名
@EntityListeners(AuditingEntityListener.class)//对实体属性变化进行跟踪
public class Exam {
    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id自动增长
    @Column(name = "exam_id",length = 11)
    private Long examId;  //考点表Id
    @Column(name = "exam_name",length = 100)
    private String examName;//考点名称
    @Column(name = "exam_address",length = 100)
    private String examAddress;//考点地址
    @CreatedDate
    @Column(name = "exam_time")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date examTime;//考试时间
    @Column(name = "exam_level",length = 10)
    private String examLevel;//考点等级

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getExamAddress() {
        return examAddress;
    }

    public void setExamAddress(String examAddress) {
        this.examAddress = examAddress;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
