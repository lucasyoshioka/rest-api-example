package com.lucasyoshioka.restapiexample.domain;

import com.lucasyoshioka.restapiexample.api.dto.OrderRequest;
import com.lucasyoshioka.restapiexample.api.dto.OrderResponse;
import com.lucasyoshioka.restapiexample.infra.messaging.MessagePublisher;
import com.lucasyoshioka.restapiexample.infra.repository.CustomerRepository;
import com.lucasyoshioka.restapiexample.infra.repository.ItemRepository;
import com.lucasyoshioka.restapiexample.infra.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    private final MessagePublisher messagePublisher;

    public OrderService(CustomerRepository customerRepository,
                        ItemRepository itemRepository,
                        OrderRepository orderRepository,
                        MessagePublisher messagePublisher) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.messagePublisher = messagePublisher;
    }

    public List<OrderResponse> getAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Integer orderId) {
        return OrderResponse.from(this.findOrderById(orderId));
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        var customer = this.findCustomerById(request.getCustomerId());
        var items = this.findItemByIds(request.getItemIds());

        var order = Order.from(request);
        order.add(customer);
        items.forEach(order::add);

        var orderResponse = orderRepository.save(order);
        messagePublisher.send(orderResponse);

        return OrderResponse.from(orderResponse);
    }

    @Transactional
    public void updateOrder(Integer orderId, OrderRequest request) {
        var order = this.findOrderById(orderId);
        var customer = this.findCustomerById(request.getCustomerId());
        var items = this.findItemByIds(request.getItemIds());

        order.setDeliveryAddress(request.getDeliveryAddress());
        order.add(customer);
        items.forEach(order::add);

        orderRepository.save(order);
        messagePublisher.send(order);
    }

    public void deleteOrder(Integer orderId) {
        this.orderRepository.deleteById(orderId);
    }

    private Order findOrderById(Integer orderId) {
        return this.orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }

    private Customer findCustomerById(Integer customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    private List<Item> findItemByIds(Set<Integer> itemIds) {
        var items = this.itemRepository.findAllById(itemIds);
        if (items.isEmpty()) {
            throw new EntityNotFoundException("Produtos não encontrados");
        }
        return items;
    }
}
