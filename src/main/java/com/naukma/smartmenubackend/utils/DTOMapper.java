package com.naukma.smartmenubackend.utils;

import com.naukma.smartmenubackend.menu_item.model.MenuItem;
import com.naukma.smartmenubackend.menu_item.model.MenuItemDTO;
import com.naukma.smartmenubackend.order.model.Order;
import com.naukma.smartmenubackend.order.model.OrderDTO;
import com.naukma.smartmenubackend.order_item.model.OrderItem;
import com.naukma.smartmenubackend.order_item.model.OrderItemDTO;
import com.naukma.smartmenubackend.review.model.Review;
import com.naukma.smartmenubackend.review.model.ReviewDTO;
import com.naukma.smartmenubackend.table.model.Table;
import com.naukma.smartmenubackend.table.model.TableDTO;
import com.naukma.smartmenubackend.user.model.User;
import com.naukma.smartmenubackend.user.model.UserDTO;
import com.naukma.smartmenubackend.waiter.model.Waiter;
import com.naukma.smartmenubackend.waiter.model.WaiterDTO;

import java.util.Comparator;

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
    public static OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getOrderItemId(),
                orderItem.getMenuItem().getMenuItemId(),
                orderItem.getQuantity(),
                orderItem.getSpecialInstructions()
        );
    }
    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getOrderTime(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getPaymentTime(),
                order.getReview().getReviewId(),
                order.getWaiter().getWaiterId(),
                order.getTable().getTableId(),
                order.getOrderItems()
                        .stream()
                        .sorted(Comparator.comparing(orderItem -> orderItem.getMenuItem().getName()))
                        .map(DTOMapper::toDTO)
                        .toList()
        );
    }
    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getRole()
        );
    }
    public static TableDTO toDTO(Table table) {
        return new TableDTO(
                table.getTableId()
        );
    }
}
