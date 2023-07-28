package com.krishna.orderapi.controllers;

import com.krishna.orderapi.entity.ProductDetail;
import com.krishna.orderapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public OrderService orderService;

    @Value("${check.active.profile}")
    private String activeProfile;

    @Value("${paymentapi.url}")
    private String paymentApiUrl;

    @Value("${productapi.url}")
    private String productApiUrl;

    @GetMapping("/hello")
    public String getHello(){
        return activeProfile;
    }

    @GetMapping("/callToPayment")
    private String getHelloOrder(){
        System.out.println("inside callToPayment");
        String uri=paymentApiUrl+"400";
        String result=restTemplate.getForObject(uri,String.class);
        return result;
    }

    @GetMapping("/{orderId}")
    private ProductDetail getOrderDetails(@PathVariable int orderId){
        System.out.println("inside orderId : "+orderId);
        if(orderService.isvalidOrderId(orderId)){
            String uri=productApiUrl+orderId;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ProductDetail pd=restTemplate.postForObject(uri,headers, ProductDetail.class);
            return pd;
        }
        return null;
    }
}
