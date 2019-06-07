package com.example.enlistenglish.demo.Repo;


import com.example.enlistenglish.demo.entity.Enlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnlistRepo extends CrudRepository<Enlist,Long> {
    Enlist findByUserMesId(Long usermesid);

    List<Enlist> findByEnlistLevel(String level);
}
