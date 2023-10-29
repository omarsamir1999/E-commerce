package ecommerce.order.service;

import ecommerce.order.models.Order;
import ecommerce.order.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findById(long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
