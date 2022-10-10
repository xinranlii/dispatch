package com.team4.dispatch.controller;

import com.team4.dispatch.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UpdateController {
    private UpdateService updateService;

    @Autowired
    public UpdateController(UpdateService updateService) {
        this.updateService = updateService;
    }

    @PutMapping("/update/{orderID}/{status}")
    public void updateOrder(@PathVariable Long orderID, @PathVariable Integer status){
        updateService.updateOrder(orderID, status);
    }

}
