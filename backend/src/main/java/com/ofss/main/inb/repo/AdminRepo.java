package com.ofss.main.inb.repo;

import org.springframework.data.repository.CrudRepository;

import com.ofss.main.inb.domain.Admin;
import java.util.List;


public interface AdminRepo extends CrudRepository<Admin , Integer>{
    public List<Admin> findByLoginId(String loginId);
}
