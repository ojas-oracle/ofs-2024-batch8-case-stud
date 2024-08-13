package com.ofss.main.inb.repo;
import org.springframework.data.repository.CrudRepository;
import com.ofss.main.inb.domain.Customer;
import java.util.List;


public interface CustomerRepo extends CrudRepository<Customer , Integer>{
    List<Customer> findByLoginId(String id);
}
