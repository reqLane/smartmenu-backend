package com.naukma.smartmenubackend.waiter;

import com.naukma.smartmenubackend.exception.InvalidWaiterDataException;
import com.naukma.smartmenubackend.utils.DTOMapper;
import com.naukma.smartmenubackend.waiter.model.Waiter;
import com.naukma.smartmenubackend.waiter.model.WaiterDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.naukma.smartmenubackend.utils.Utils.isNullOrEmpty;

@Service
public class WaiterService {
    private final WaiterRepo waiterRepo;

    public WaiterService(WaiterRepo waiterRepo) {
        this.waiterRepo = waiterRepo;
    }

    // BUSINESS LOGIC

    public WaiterDTO createWaiter(WaiterDTO waiterDTO) {
        if (isNullOrEmpty(waiterDTO.name()))
            throw new InvalidWaiterDataException("WAITER REQUIRED FIELD IS EMPTY");

        Waiter waiter = new Waiter(
                waiterDTO.name()
        );

        waiter = waiterRepo.save(waiter);
        return DTOMapper.toDTO(waiter);
    }

    public WaiterDTO updateWaiter(Long waiterId, WaiterDTO waiterDTO) {
        Waiter waiter = findById(waiterId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("WAITER ID-%d NOT FOUND TO UPDATE", waiterId)));

        if (isNullOrEmpty(waiterDTO.name()))
            waiter.setName(waiterDTO.name());

        waiter = waiterRepo.save(waiter);
        return DTOMapper.toDTO(waiter);
    }

    public void deleteWaiter(Long waiterId) {
        Waiter waiter = findById(waiterId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("WAITER ID-%d NOT FOUND TO DELETE", waiterId)));

        delete(waiter);
    }

    public List<WaiterDTO> getAllWaiters() {
        return findAll()
                .stream()
                .sorted(Comparator.comparing(Waiter::getName))
                .map(DTOMapper::toDTO)
                .toList();
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
