package com.team4.dispatch.repository;

import com.team4.dispatch.model.Order;
import com.team4.dispatch.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Integer> {

}