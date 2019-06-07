package com.example.enlistenglish.demo.Repo;


import com.example.enlistenglish.demo.entity.Admin;
import com.example.enlistenglish.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends CrudRepository<Admin,Long> {
    public List<Admin> findByAdminNameAndAdminPassword(String name, String password);
}
