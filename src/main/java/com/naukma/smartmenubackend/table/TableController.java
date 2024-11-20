package com.naukma.smartmenubackend.table;

import com.naukma.smartmenubackend.order.model.OrderDTO;
import com.naukma.smartmenubackend.table.model.TableDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("")
    public ResponseEntity<TableDTO> create() {
        return ResponseEntity.status(HttpStatus.CREATED).body(tableService.createTable());
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete() {
        tableService.deleteTable();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("")
    public ResponseEntity<List<TableDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(tableService.getAllTables());
    }

    @GetMapping("/{tableId}/has-active-order")
    public ResponseEntity<Boolean> hasActiveOrder(@PathVariable Long tableId) {
        return ResponseEntity.status(HttpStatus.OK).body(tableService.hasActiveOrder(tableId));
    }

    @GetMapping("/{tableId}/get-active-order")
    public ResponseEntity<OrderDTO> getActiveOrder(@PathVariable Long tableId) {
        return ResponseEntity.status(HttpStatus.OK).body(tableService.getActiveOrder(tableId));
    }
}
