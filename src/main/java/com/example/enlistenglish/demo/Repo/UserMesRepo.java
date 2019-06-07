package com.example.enlistenglish.demo.Repo;


import com.example.enlistenglish.demo.entity.User;
import com.example.enlistenglish.demo.entity.User_mes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMesRepo extends CrudRepository<User_mes,Long> {
    User_mes findByUserName(String username);

    //模糊查询用户名
    @Query(value = "Select * from user_mes where user_mes_name like %?%;",nativeQuery = true)
    List<User_mes> queryStudent(String querytext);
}
