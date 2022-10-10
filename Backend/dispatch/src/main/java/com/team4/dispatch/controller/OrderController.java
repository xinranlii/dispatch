package com.team4.dispatch.controller;

import com.team4.dispatch.model.Order;
import com.team4.dispatch.model.Robot;
import com.team4.dispatch.model.Station;
import com.team4.dispatch.model.User;
import com.team4.dispatch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public void addOrder(@RequestBody Order order, Robot robot, Principal principal){
        Station station = orderService.getStation(order.getStationID());
        robot.setStationID(station);
        orderService.addRobot(robot);

        order.setGuest(new User.Builder().setUsername(principal.getName()).build());
        order.setRobotID(robot);
        orderService.addOrder(order);
    }
}
