package com.naukma.smartmenubackend.table;

import com.naukma.smartmenubackend.order.OrderRepo;
import com.naukma.smartmenubackend.order.OrderService;
import com.naukma.smartmenubackend.order.model.Order;
import com.naukma.smartmenubackend.order.model.OrderDTO;
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
    private final OrderRepo orderRepo;

    public TableService(TableRepo tableRepo, OrderRepo orderRepo) {
        this.tableRepo = tableRepo;
        this.orderRepo = orderRepo;
    }

    // BUSINESS LOGIC

    public TableDTO createTable() {
        Table table = new Table();

        table = save(table);
        return DTOMapper.toDTO(table);
    }

    public List<TableDTO> getAllTables() {
        return findAll()
                .stream()
                .sorted(Comparator.comparing(Table::getTableId))
                .map(DTOMapper::toDTO)
                .toList();
    }

    public Boolean hasActiveOrder(Long tableId) {
        return orderRepo.findAll()
                .stream()
                .filter(order -> order.getTable().getTableId().equals(tableId))
                .anyMatch(order -> order.getStatus() == OrderStatus.PENDING || order.getStatus() == OrderStatus.COOKED);
    }

    public OrderDTO getActiveOrder(Long tableId) {
        Order activeOrder = findAll()
                .stream()
                .filter(table -> table.getTableId().equals(tableId))
                .map(Table::getOrders)
                .flatMap(Collection::stream)
                .filter(order -> order.getStatus() == OrderStatus.PENDING || order.getStatus() == OrderStatus.COOKED)
                .max(Comparator.comparing(Order::getOrderTime))
                .orElseThrow(() -> new EntityNotFoundException(String.format("TABLE ID-%d DOESN'T HAVE ACTIVE ORDER", tableId)));
        return DTOMapper.toDTO(activeOrder);
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
