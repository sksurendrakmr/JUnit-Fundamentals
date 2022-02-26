package com.surendra.order.service;

import com.surendra.order.dto.Order;
import com.surendra.order.exception.BOException;

public interface OrderService {
    boolean placeOrder(Order order) throws BOException;
    boolean cancelOrder(int id) throws BOException;
    boolean deleteOrder(int id) throws BOException;
}
