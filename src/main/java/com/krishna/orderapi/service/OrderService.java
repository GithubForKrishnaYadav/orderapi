package com.krishna.orderapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    List<Integer> orderList;
    private List<Integer> prepareOrderList(){
        orderList = new ArrayList<>();
        orderList.add(1);
        orderList.add(2);
        orderList.add(3);
        orderList.add(4);
        orderList.add(5);
        return orderList;
    }

    public boolean isvalidOrderId(int orderId){
        List<Integer> orderList = prepareOrderList();
        return orderList.contains(orderId);
    }
}
