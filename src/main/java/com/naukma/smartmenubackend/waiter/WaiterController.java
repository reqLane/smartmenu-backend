package com.naukma.smartmenubackend.waiter;

import com.naukma.smartmenubackend.waiter.model.WaiterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waiters")
public class WaiterController {
    private final WaiterService waiterService;

    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @PostMapping("")
    public ResponseEntity<WaiterDTO> create(@RequestBody WaiterDTO waiterDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(waiterService.createWaiter(waiterDTO));
    }

    @PatchMapping("/{waiterId}")
    public ResponseEntity<WaiterDTO> edit(@PathVariable("waiterId") Long waiterId,
                                            @RequestBody WaiterDTO waiterDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(waiterService.updateWaiter(waiterId, waiterDTO));
    }

    @DeleteMapping("/{waiterId}")
    public ResponseEntity<Void> delete(@PathVariable("waiterId") Long waiterId) {
        waiterService.deleteWaiter(waiterId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("")
    public ResponseEntity<List<WaiterDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(waiterService.getAllWaiters());
    }
}
