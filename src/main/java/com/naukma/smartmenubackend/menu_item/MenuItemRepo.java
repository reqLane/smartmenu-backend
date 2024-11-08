package com.naukma.smartmenubackend.menu_item;

import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {

}
