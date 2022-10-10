package com.team4.dispatch.controller;

import com.team4.dispatch.model.Order;
import com.team4.dispatch.service.SearchService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "/search")
    public Order searchOrder(@RequestParam(name = "receiver_name") String receiverName,
                             @RequestParam(name = "weight") int weight,
                             @RequestParam(name = "length") int length,
                             @RequestParam(name = "width") int width,
                             @RequestParam(name = "height") int height,
                             @RequestParam(name = "pick_up_address") String pickUpAddress,
                             @RequestParam(name = "delivery_address") String deliveryAddress,
                             @RequestParam(name = "pick_up_time")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                     LocalDateTime pickUpTime) throws IOException, JSONException {

        List<Double> pickUpLocation = searchService.getLngAndLat(pickUpAddress);
        List<Double> deliveryLocation = searchService.getLngAndLat(deliveryAddress);
        List<Double> res = searchService.getPriceAndTime(pickUpLocation, deliveryLocation);
        Order.Builder builder = new Order.Builder();

        Order order = new Order(builder.setReceiverName(receiverName).setHeight(height).setLength(length).setWeight(weight)
                .setWidth(width).setPickUpAddress(pickUpAddress).setPickUpTime(pickUpTime).setDeliveryAddress(deliveryAddress)
                .setDeliveryTime(pickUpTime.plusHours(res.get(1).longValue())).setTotalPrice(res.get(2).intValue())
                .setStationID(res.get(0).intValue()));
        return order;
    }
}
