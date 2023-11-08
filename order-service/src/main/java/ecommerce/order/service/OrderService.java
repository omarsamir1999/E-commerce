package ecommerce.order.service;

import ecommerce.order.dto.OrderItemDto;
import ecommerce.order.dto.OrderRequest;
import ecommerce.order.models.Order;
import ecommerce.order.models.OrderItem;
import ecommerce.order.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerEmail(orderRequest.getCustomerEmail());

        // call the stock service to check if all order items exist
        if (orderItemsExists(orderRequest.getOrderItemDtosList())) {
            List<OrderItem> orderItems = orderRequest.getOrderItemDtosList()
                    .stream()
                    .map(this::mapToEntity).toList();
            order.setOrderItemsList(orderItems);
        } else {
            throw new IllegalArgumentException("order items are not available in the stock!");
        }

        // get the total price from the product service
        Double price = calculateTotalPrice(orderRequest.getOrderItemDtosList());
        order.setPrice(price);

        // call coupon service to check if the given coupon is valid or not
        if (isValidCoupon(orderRequest.getCouponCode())) {
            order.setCouponCode(orderRequest.getCouponCode());
            Double couponValue = getCouponValue(orderRequest.getCouponCode(), price);
            if (couponValue > price) {
                throw new RuntimeException("Can't process this coupon on this order!");
            }
            order.setCouponValue(couponValue);
            order.setPaidPrice(price - couponValue);
        }

        // call the stock service to increment the items from the stock
        consumeFromStock(orderRequest.getOrderItemDtosList());

        orderRepository.save(order);
    }

    private boolean orderItemsExists(List<OrderItemDto> orderedItems) {
        return true;
    }
    private boolean isValidCoupon(String couponCode) {
        return true;
    }

    private Double getCouponValue(String CouponCode, Double price) {
        return price;
    }
    private Double calculateTotalPrice(List<OrderItemDto> orderItems) {
        return 0.0;
    }

    private void consumeFromStock(List<OrderItemDto> orderItems) {

    }


    private OrderItem mapToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProductCode(orderItemDto.getProductCode());
        orderItem.setStoreId(orderItemDto.getStoreId());
        return orderItem;
    }
}
