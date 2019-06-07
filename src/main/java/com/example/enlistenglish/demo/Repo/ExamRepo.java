package com.example.enlistenglish.demo.Repo;


import com.example.enlistenglish.demo.entity.Exam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepo extends CrudRepository<Exam,Long> {
    List<Exam> findExamByExamLevel(String level);
    Exam findByExamId(Long id);

    //模糊查询考点
    @Query(value = "Select * from exam where concat(exam_address,exam_name,exam_level,exam_time) like %?%",nativeQuery = true)
    List<Exam> queryExam(String querytext);

    @Override
    void deleteById(Long id);

}
