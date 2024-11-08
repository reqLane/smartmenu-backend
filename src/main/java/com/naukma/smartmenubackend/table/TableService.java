package com.naukma.smartmenubackend.table;

import com.naukma.smartmenubackend.table.model.Table;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TableService {
    private final TableRepo tableRepo;

    public TableService(TableRepo tableRepo) {
        this.tableRepo = tableRepo;
    }

    // BUSINESS LOGIC



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
