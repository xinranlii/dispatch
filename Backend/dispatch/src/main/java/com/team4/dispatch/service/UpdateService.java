package com.team4.dispatch.service;

import com.team4.dispatch.model.Order;
import com.team4.dispatch.model.Robot;
import com.team4.dispatch.model.User;
import com.team4.dispatch.repository.OrderRepository;
import com.team4.dispatch.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateService {
    private OrderRepository orderRepository;

    @Autowired
    public UpdateService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void updateOrder(Long orderID, Integer status){
        Order order = orderRepository.findByOrderID(orderID);
        if(order == null){
            System.out.println("No order found");
        }
        assert order != null;
        order.setOrderStatus(status);
        orderRepository.save(order);
    }
}