package com.team4.dispatch.controller;

import com.team4.dispatch.model.Order;
import com.team4.dispatch.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class HistoryController {
    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;

    }
    @GetMapping(value = "/history/guest")
    public List<Order> listOrdersUser(Principal principal) {
        return historyService.listByUser(principal.getName());
    }

    @GetMapping(value = "/history/admin")
    public List<Order> listOrdersAdmin(Principal principal) {
        return historyService.listByAdmin(principal.getName());
    }
}
