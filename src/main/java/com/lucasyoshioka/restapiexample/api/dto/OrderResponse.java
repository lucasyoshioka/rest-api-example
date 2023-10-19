package com.lucasyoshioka.restapiexample.api.dto;

import com.lucasyoshioka.restapiexample.domain.Item;
import com.lucasyoshioka.restapiexample.domain.Order;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderResponse {

    private Integer orderId;

    private String customerName;

    private Set<String> itemsDescription;

    private BigDecimal totalPrice;

    public OrderResponse() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<String> getItemsDescription() {
        return itemsDescription;
    }

    public void setItemsDescription(Set<String> itemsDescription) {
        this.itemsDescription = itemsDescription;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static OrderResponse from(Order order) {
        var response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setCustomerName(order.getCustomer().getName());
        response.setItemsDescription(order.getItems().stream()
                .map(Item::getDescription)
                .collect(Collectors.toSet()));
        response.setTotalPrice(order.calculateTotalPrice());
        return response;
    }

}
