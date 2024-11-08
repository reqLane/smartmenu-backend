package com.naukma.smartmenubackend.waiter;

import com.naukma.smartmenubackend.waiter.model.Waiter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WaiterService {
    private final WaiterRepo waiterRepo;

    public WaiterService(WaiterRepo waiterRepo) {
        this.waiterRepo = waiterRepo;
    }

    // BUSINESS LOGIC



    // CRUD OPERATIONS

    public Set<Waiter> findAll() {
        return new HashSet<>(waiterRepo.findAll());
    }

    public Optional<Waiter> findById(Long id) {
        return waiterRepo.findById(id);
    }

    public Waiter save(Waiter waiter) {
        return waiterRepo.save(waiter);
    }

    public void deleteById(Long id) {
        waiterRepo.deleteById(id);
    }

    public void delete(Waiter waiter) {
        waiterRepo.deleteById(waiter.getWaiterId());
    }

    public void deleteAll() {
        waiterRepo.deleteAll();
    }
}
