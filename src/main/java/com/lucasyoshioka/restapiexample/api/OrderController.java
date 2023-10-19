package com.lucasyoshioka.restapiexample.api;

import com.lucasyoshioka.restapiexample.api.dto.OrderRequest;
import com.lucasyoshioka.restapiexample.api.dto.OrderResponse;
import com.lucasyoshioka.restapiexample.domain.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return this.service.getAllOrders();
    }

    @GetMapping(value = "{orderId}")
    public OrderResponse getOrderById(@NotNull @PathVariable Integer orderId) {
        return this.service.getOrderById(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@Validated @RequestBody OrderRequest request) {
        return this.service.createOrder(request);
    }

    @PutMapping(value = "{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@NotNull @PathVariable Integer orderId,
                                     @Validated @RequestBody OrderRequest orderRequest) {
        this.service.updateOrder(orderId, orderRequest);
    }

    @DeleteMapping(value = "{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@NotNull @PathVariable Integer orderId) {
        this.service.deleteOrder(orderId);
    }

}
