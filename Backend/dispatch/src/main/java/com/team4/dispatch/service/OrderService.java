package com.team4.dispatch.service;

import com.team4.dispatch.model.Order;
import com.team4.dispatch.model.Robot;
import com.team4.dispatch.model.Station;
import com.team4.dispatch.model.User;
import com.team4.dispatch.repository.OrderRepository;
import com.team4.dispatch.repository.RobotRepository;
import com.team4.dispatch.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
  private OrderRepository orderRepository;
  private RobotRepository robotRepository;
  private StationRepository stationRepository;
  @Autowired
  public OrderService(OrderRepository orderRepository, RobotRepository robotRepository, StationRepository stationRepository) {
    this.orderRepository = orderRepository;
    this.robotRepository = robotRepository;
    this.stationRepository = stationRepository;
  }
  public Station getStation(int stationID) {
    return stationRepository.findByStationID(stationID);
  }

  public void addOrder(Order order) {
    orderRepository.save(order);
  }

  public void addRobot(Robot robot) {
    robotRepository.save(robot);
  }

//  @Transactional(isolation = Isolation.SERIALIZABLE)
//  public void deleteOrder(Long orderId) throws OrderNotExistException {
//    Order order = orderRepository.findById(orderId);
//    if (order == null) {
//      throw new OrderNotExistException("Order doesn't exist");
//    }
//    orderRepository.deleteById(orderId);
//  }
}