package com.surendra.order.service;

import com.surendra.order.dao.OrderDao;
import com.surendra.order.dto.Order;
import com.surendra.order.exception.BOException;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {

    OrderDao dao;

    public OrderDao getDao() {
        return dao;
    }

    public void setDao(OrderDao dao) {
        this.dao = dao;
    }


    @Override
    public boolean placeOrder(Order order) throws BOException {
        try {
            int result = dao.create(order);
            if (result == 0){
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws BOException {
        try {
            Order order = dao.read(id);
            order.setStatus("cancelled");
            int updatedOrder = dao.update(order);
            if (updatedOrder == 0){
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws BOException {
        try {
            int result = dao.delete(id);
            if (result == 0){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BOException(e);
        }
        return true;
    }
}
