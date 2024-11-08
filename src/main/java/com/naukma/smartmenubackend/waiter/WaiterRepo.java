package com.naukma.smartmenubackend.waiter;

import com.naukma.smartmenubackend.waiter.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepo extends JpaRepository<Waiter, Long> {

}
