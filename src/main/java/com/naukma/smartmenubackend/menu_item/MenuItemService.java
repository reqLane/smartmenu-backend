package com.naukma.smartmenubackend.menu_item;

import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MenuItemService {
    private final MenuItemRepo menuItemRepo;

    public MenuItemService(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    // BUSINESS LOGIC



    // CRUD OPERATIONS

    public Set<MenuItem> findAll() {
        return new HashSet<>(menuItemRepo.findAll());
    }

    public Optional<MenuItem> findById(Long id) {
        return menuItemRepo.findById(id);
    }

    public MenuItem save(MenuItem menuItem) {
        return menuItemRepo.save(menuItem);
    }

    public void deleteById(Long id) {
        menuItemRepo.deleteById(id);
    }

    public void delete(MenuItem menuItem) {
        menuItemRepo.deleteById(menuItem.getMenuItemId());
    }

    public void deleteAll() {
        menuItemRepo.deleteAll();
    }
}
