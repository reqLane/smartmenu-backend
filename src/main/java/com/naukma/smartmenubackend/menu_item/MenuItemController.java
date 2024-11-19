package com.naukma.smartmenubackend.menu_item;

import com.naukma.smartmenubackend.menu_item.model.MenuItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("")
    public ResponseEntity<MenuItemDTO> create(@RequestBody MenuItemDTO menuItemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.createMenuItem(menuItemDTO));
    }

    @PatchMapping("/{menuItemId}")
    public ResponseEntity<MenuItemDTO> edit(@PathVariable("menuItemId") Long menuItemId,
                                            @RequestBody MenuItemDTO menuItemDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(menuItemService.updateMenuItem(menuItemId, menuItemDTO));
    }

    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<Void> delete(@PathVariable("menuItemId") Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
