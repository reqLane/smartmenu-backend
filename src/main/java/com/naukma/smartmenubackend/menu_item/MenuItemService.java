package com.naukma.smartmenubackend.menu_item;

import com.naukma.smartmenubackend.exception.InvalidMenuItemDataException;
import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import com.naukma.smartmenubackend.menu_item.model.MenuItemDTO;
import com.naukma.smartmenubackend.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.naukma.smartmenubackend.utils.Utils.isNullOrEmpty;

@Service
public class MenuItemService {
    private final MenuItemRepo menuItemRepo;

    public MenuItemService(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    // BUSINESS LOGIC

    public MenuItemDTO createMenuItem(MenuItemDTO menuItemDTO) {
        if (isNullOrEmpty(menuItemDTO.name())
                || isNullOrEmpty(menuItemDTO.description())
                || menuItemDTO.price() == null
                || isNullOrEmpty(menuItemDTO.imageUrl()))
            throw new InvalidMenuItemDataException("MENU ITEM REQUIRED FIELD IS EMPTY");

        MenuItem menuItem = new MenuItem(
                menuItemDTO.name(),
                menuItemDTO.description(),
                menuItemDTO.price(),
                menuItemDTO.imageUrl()
        );

        menuItem = menuItemRepo.save(menuItem);
        return DTOMapper.toDTO(menuItem);
    }

    public MenuItemDTO updateMenuItem(Long menuItemId, MenuItemDTO menuItemDTO) {
        MenuItem menuItem = findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("MENU ITEM ID-%d NOT FOUND TO UPDATE", menuItemId)));

        if (!isNullOrEmpty(menuItemDTO.name()))
            menuItem.setName(menuItemDTO.name());
        if (!isNullOrEmpty(menuItemDTO.description()))
            menuItem.setDescription(menuItem.getDescription());
        if (menuItemDTO.price() != null)
            menuItem.setPrice(menuItemDTO.price());
        if (!isNullOrEmpty(menuItemDTO.imageUrl()))
            menuItem.setImageUrl(menuItemDTO.imageUrl());

        menuItem = menuItemRepo.save(menuItem);
        return DTOMapper.toDTO(menuItem);
    }

    public void deleteMenuItem(Long menuItemId) {
        MenuItem menuItem = findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("MENU ITEM ID-%d NOT FOUND TO DELETE", menuItemId)));

        delete(menuItem);
    }

    public List<MenuItemDTO> getAllMenuItems() {
        return findAll()
                .stream()
                .map(DTOMapper::toDTO)
                .toList();
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
