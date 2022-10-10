package com.team4.dispatch.service;

import com.team4.dispatch.model.Order;
import com.team4.dispatch.model.User;
import com.team4.dispatch.repository.AuthorityRepository;
import com.team4.dispatch.repository.OrderRepository;
import com.team4.dispatch.repository.RobotRepository;
import com.team4.dispatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private AuthorityRepository authorityRepository;
    private OrderRepository orderRepository;
    private RobotRepository robotRepository;
    private UserRepository userRepository;

    @Autowired
    public HistoryService(AuthorityRepository authorityRepository,OrderRepository orderRepository, RobotRepository robotRepository, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.orderRepository = orderRepository;
        this.robotRepository = robotRepository;
        this.userRepository = userRepository;
    }

    public List<Order> listByUser(String username) {
        return orderRepository.findByGuest(new User.Builder().setUsername(username).build());
    }

    public List<Order> listByAdmin(String username) {
        return orderRepository.findAll();
    }
}
