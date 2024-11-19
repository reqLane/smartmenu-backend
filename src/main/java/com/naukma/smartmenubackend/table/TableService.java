package com.naukma.smartmenubackend.table;

import com.naukma.smartmenubackend.order.OrderService;
import com.naukma.smartmenubackend.order.status.OrderStatus;
import com.naukma.smartmenubackend.table.model.Table;
import com.naukma.smartmenubackend.table.model.TableDTO;
import com.naukma.smartmenubackend.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableService {
    private final TableRepo tableRepo;
    private final OrderService orderService;

    public TableService(TableRepo tableRepo, OrderService orderService) {
        this.tableRepo = tableRepo;
        this.orderService = orderService;
    }

    // BUSINESS LOGIC

    public TableDTO createTable() {
        Table table = new Table();
        table.setTableId(findNextAvailableId());

        table = save(table);
        return DTOMapper.toDTO(table);
    }

    public void deleteTable() {
        Long lastTableId = findLastTableId()
                .orElseThrow(() -> new EntityNotFoundException("TABLES NOT FOUND TO DELETE"));

        deleteById(lastTableId);
    }

    public List<TableDTO> getAllTables() {
        return findAll()
                .stream()
                .sorted(Comparator.comparing(Table::getTableId))
                .map(DTOMapper::toDTO)
                .toList();
    }

    public Boolean hasActiveOrder(Long tableId) {
        return orderService.findAll()
                .stream()
                .filter(order -> order.getTable().getTableId().equals(tableId))
                .anyMatch(order -> order.getStatus() == OrderStatus.PENDING || order.getStatus() == OrderStatus.COOKED);
    }

    private Long findNextAvailableId() {
        List<Long> existingTableIds = findAll()
                .stream()
                .map(Table::getTableId)
                .toList();
        Long nextId = 1L;
        while (existingTableIds.contains(nextId)) {
            nextId++;
        }
        return nextId;
    }

    private Optional<Long> findLastTableId() {
        return findAll()
                .stream()
                .map(Table::getTableId)
                .max(Long::compareTo);
    }

    // CRUD OPERATIONS

    public Set<Table> findAll() {
        return new HashSet<>(tableRepo.findAll());
    }

    public Optional<Table> findById(Long id) {
        return tableRepo.findById(id);
    }

    public Table save(Table table) {
        return tableRepo.save(table);
    }

    public void deleteById(Long id) {
        tableRepo.deleteById(id);
    }

    public void delete(Table table) {
        tableRepo.deleteById(table.getTableId());
    }

    public void deleteAll() {
        tableRepo.deleteAll();
    }
}
