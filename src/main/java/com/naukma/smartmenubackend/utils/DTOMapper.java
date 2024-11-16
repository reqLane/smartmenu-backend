package com.naukma.smartmenubackend.utils;

import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import com.naukma.smartmenubackend.menu_item.model.MenuItemDTO;
import com.naukma.smartmenubackend.review.model.Review;
import com.naukma.smartmenubackend.review.model.ReviewDTO;
import com.naukma.smartmenubackend.waiter.model.Waiter;
import com.naukma.smartmenubackend.waiter.model.WaiterDTO;

public class DTOMapper {
    public static MenuItemDTO toDTO(MenuItem menuItem) {
        return new MenuItemDTO(
                menuItem.getMenuItemId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getImageURL()
        );
    }
    public static WaiterDTO toDTO(Waiter waiter) {
        return new WaiterDTO(
                waiter.getWaiterId(),
                waiter.getName()
        );
    }
    public static ReviewDTO toDTO(Review review) {
        return new ReviewDTO(
                review.getReviewId(),
                review.getRating(),
                review.getReviewTime()
        );
    }
}
