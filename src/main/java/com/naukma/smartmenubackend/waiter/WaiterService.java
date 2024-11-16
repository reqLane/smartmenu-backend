package com.naukma.smartmenubackend.waiter;

import com.naukma.smartmenubackend.utils.DTOMapper;
import com.naukma.smartmenubackend.waiter.model.Waiter;
import com.naukma.smartmenubackend.waiter.model.WaiterDTO;
import jakarta.persistence.EntityNotFoundException;
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

    public WaiterDTO createWaiter(WaiterDTO waiterDTO) {
        Waiter waiter = new Waiter(
                waiterDTO.name()
        );
        Waiter savedWaiter = waiterRepo.save(waiter);
        return DTOMapper.toDTO(savedWaiter);
    }

    public WaiterDTO updateWaiter(Long waiterId, WaiterDTO waiterDTO) {
        Waiter waiter = findById(waiterId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("WAITER ID-%d NOT FOUND TO UPDATE.", waiterId)));

        waiter.setName(waiterDTO.name());

        Waiter updatedWaiter = waiterRepo.save(waiter);
        return DTOMapper.toDTO(updatedWaiter);
    }

    public void deleteWaiter(Long waiterId) {
        Waiter waiter = findById(waiterId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("WAITER ID-%d NOT FOUND TO DELETE.", waiterId)));

        delete(waiter);
    }

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
