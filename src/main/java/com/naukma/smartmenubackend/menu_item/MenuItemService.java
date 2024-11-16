package com.naukma.smartmenubackend.menu_item;

import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import com.naukma.smartmenubackend.menu_item.model.MenuItemDTO;
import com.naukma.smartmenubackend.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
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

    public MenuItemDTO createMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem(
                menuItemDTO.name(),
                menuItemDTO.description(),
                menuItemDTO.price(),
                menuItemDTO.imageURL()
        );
        MenuItem savedMenuItem = menuItemRepo.save(menuItem);
        return DTOMapper.toDTO(savedMenuItem);
    }

    public MenuItemDTO updateMenuItem(Long menuItemId, MenuItemDTO menuItemDTO) {
        MenuItem menuItem = findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("MENU ITEM ID-%d NOT FOUND TO UPDATE.", menuItemId)));

        menuItem.setName(menuItemDTO.name());
        menuItem.setDescription(menuItem.getDescription());
        menuItem.setPrice(menuItemDTO.price());
        menuItem.setImageURL(menuItemDTO.imageURL());

        MenuItem updatedMenuItem = menuItemRepo.save(menuItem);
        return DTOMapper.toDTO(updatedMenuItem);
    }

    public void deleteMenuItem(Long menuItemId) {
        MenuItem menuItem = findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("MENU ITEM ID-%d NOT FOUND TO DELETE.", menuItemId)));

        delete(menuItem);
    }

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
