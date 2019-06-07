package com.example.enlistenglish.demo.Repo;


import com.example.enlistenglish.demo.entity.Exam;
import com.example.enlistenglish.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {
    public List<User> findByUserNameAndAndUserPassword(String name, String password);

    User findByUserId(Long id);
    List<User> findByUserName(String username);

    //模糊查询用户名
    @Query(value = "Select * from user where user_name like %?%;",nativeQuery = true)
    List<User> queryUsers(String querytext);

    @Override
    void deleteById(Long aLong);
}
