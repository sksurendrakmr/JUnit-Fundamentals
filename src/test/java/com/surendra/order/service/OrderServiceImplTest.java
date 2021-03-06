package com.surendra.order.service;

import com.surendra.order.dao.OrderDao;
import com.surendra.order.dto.Order;
import com.surendra.order.exception.BOException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    @Mock
    OrderDao dao;

    private OrderServiceImpl ordService;

    @Before
    public void setup(){
        //to run the mocked dao class at runtime
        //By doing so, it will scan through all the objects and any object
        //that is marked with @Mock, will be mocked out.
        MockitoAnnotations.initMocks(this);
        ordService = new OrderServiceImpl();
        ordService.setDao(dao);
    }

    @Test
    public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
        //Mocking out and setting the expectation using when class
        Order order = new Order();
        when(dao.create(any(Order.class))).thenReturn(Integer.valueOf(1));
        //assert the result and verifying
        boolean result = ordService.placeOrder(order);
        assertTrue(result);
        verify(dao).create(order); //The method that we are asking to verify is called once.
    }

    @Test
    public void placeOrder_ShouldNot_Create_An_Order() throws SQLException, BOException {
        //Mocking out and setting the expectation using when class
        Order order = new Order();
        when(dao.create(order)).thenReturn(0);
        //assert the result and verifying
        boolean result = ordService.placeOrder(order);
        assertFalse(result);
        verify(dao).create(order); //The method that we are asking to verify is called once.
    }

    @Test(expected = BOException.class)
    public void placeOrder_Should_Throw_Error() throws SQLException, BOException {
        //Mocking out and setting the expectation using when class
        Order order = new Order();
        when(dao.create(order)).thenThrow(SQLException.class);
        //assert the result and verifying
        ordService.placeOrder(order);
        /**
         * Verification mode allow us to verify in a different way.
         * We can tell how many times a method should be invoked by.
         * ByDefault it is 1.
         */
        verify(dao,times(1)).create(order); //method is called how many times
        verify(dao,atLeast(1)).create(order); //method is called at least specified number of times


    }

    @Test
    public void cancelOrderShouldCancelAOrder() throws SQLException, BOException {
        /**
         * So far while stubbing out our method calls using 'when' method or when we verify the methods
         * on the mocked out  object, we have been passing the exact object type or primitive time.
         *
         * Instead of this, mockito gives us a matcher API which makes our job a lot easier.
         *
         * Thus, it is a convenient way to use the matchers and to make sure that the values that we
         * are passing in is of exact type.
         */
        Order order = new Order();
        when(dao.read(anyInt())).thenReturn(order);
        when(dao.update(any(Order.class))).thenReturn(Integer.valueOf(1));
        boolean result = ordService.cancelOrder(anyInt());
        assertTrue(result);
        verify(dao).update(any(Order.class));
        verify(dao).read(anyInt());
    }

    @Test
    public void cancelOrderShouldNotCancelAOrderWhenDataIsNotAvailableInDB() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);
        boolean result = ordService.cancelOrder(123);
        assertFalse(result);
        verify(dao).update(order);
        verify(dao).read(123);
    }

    @Test(expected = BOException.class)
    public void cancelOrderShouldThrowAnException() throws SQLException, BOException {
        when(dao.read(123)).thenThrow(SQLException.class);
        ordService.cancelOrder(123);
    }
    @Test(expected = BOException.class)
    public void cancelOrderShouldThrowAnException1() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenThrow(SQLException.class);
        ordService.cancelOrder(123);
    }

    @Test
    public void deleteOrderShouldDeleteAOrder() throws SQLException, BOException {
        when(dao.delete(123)).thenReturn(Integer.valueOf(1));
        boolean result = ordService.deleteOrder(123);
        assertTrue(result);
        verify(dao).delete(123);
    }
    @Test
    public void deleteOrderShouldNotDeleteAOrder() throws SQLException, BOException {
        when(dao.delete(123)).thenReturn(0);
        boolean result = ordService.deleteOrder(123);
        assertFalse(result);
        verify(dao).delete(123);
    }

    @Test(expected = BOException.class)
    public void deleteOrderShouldThrowAnException() throws SQLException, BOException {
        when(dao.delete(123)).thenThrow(SQLException.class);
        ordService.deleteOrder(123);
    }

}
