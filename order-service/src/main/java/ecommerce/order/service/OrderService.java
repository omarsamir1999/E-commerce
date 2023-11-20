package ecommerce.order.service;

import ecommerce.order.dtos.*;
import ecommerce.order.helpers.NotificationDataExtractor;
import ecommerce.order.helpers.OrderStatus;
import ecommerce.order.models.Order;
import ecommerce.order.models.OrderItem;
import ecommerce.order.models.Shipment;
import ecommerce.order.repository.OrderRepository;
import ecommerce.order.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;
    private final ProductService productService;
    private final CouponService couponService;
    private final StoreService storeService;
    private final BankService bankService;
    private final NotificationService notificationService;

    public Order findById(long id) {
        return orderRepository.findById(id).orElse(null);
    }


    public List<Order> searchOrdersByCustomer(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }

    public List<Order> searchOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByDateBetween(startDate, endDate);
    }

    public void placeOrder(OrderRequest orderRequest) {

        // the order details data
        Order order = new Order();
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setCouponCode(orderRequest.getCouponCode());
        List<OrderItem> orderItems = orderRequest.getOrderItemDtosList()
                .stream()
                .map(this::mapToEntity).toList();
        order.setOrderItemsList(orderItems);

        // 1. call the stock service to check if all order items exist
//        if (!orderItemsExists(orderRequest.getOrderItemDtosList())) {
//            throw new IllegalArgumentException("order items are not available in the stock!");
//        }

        // 2. get the total price from the product service
//        Double price = productService.calculateTotalPrice(orderRequest.getOrderItemDtosList());
//        order.setPrice(price);
//        order.setPaidPrice(price);

        // 3. call coupon service to check if the given coupon is valid or not
//        if (!order.getCouponCode().isEmpty() && couponService.isValidCoupon(order.getCouponCode())) { // if order has a coupon
//            couponService.consumeCoupon(order);
//        }
        order.setStatus(OrderStatus.PENDING);

        // 4. Make the transaction
//        PaymentDetails paymentDetails = getPaymentDetails(orderRequest, order);
//        TransactionResponse transactionResponse = bankService.makeTransaction(paymentDetails);

//        if (transactionResponse.getTransactionStatus() == TransactionStatus.FAIL) {
//            throw new IllegalArgumentException("Invalid Transaction!");
//        }
        // 5. persist the transactionId
//        order.setTransactionId(transactionResponse.getTransactionId());

        // 6. Create the shipment
        Shipment shipment = getShipment(orderRequest, order);

        // 7. update order status
        order.setStatus(OrderStatus.SHIPPING);


        // 8. call the stock service to decrement the items from the stock
//        storeService.consumeFromStock(orderRequest.getOrderItemDtosList());

        // 9. persist the order
        orderRepository.save(order);
        shipmentRepository.save(shipment);

        // 10. send notifivation to the customer
        Map<String, Object> notification = NotificationDataExtractor.extractNotificationData(order, shipment);
        notificationService.sendNotification(notification);
    }

    private static PaymentDetails getPaymentDetails(OrderRequest orderRequest, Order order) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setCustomerEmail(orderRequest.getCustomerEmail());
        paymentDetails.setCardName(orderRequest.getCardName());
        paymentDetails.setCardNumber(orderRequest.getCardNumber());
        paymentDetails.setSecurityCode(orderRequest.getSecurityCode());
        paymentDetails.setExpirationMonth(orderRequest.getExpirationMonth());
        paymentDetails.setExpirationYear(orderRequest.getExpirationYear());
        paymentDetails.setAmount(order.getPaidPrice());
        return paymentDetails;
    }

    private static Shipment getShipment(OrderRequest orderRequest, Order order) {
        Shipment shipment = new Shipment();
        shipment.setCountry(orderRequest.getCountry());
        shipment.setState(orderRequest.getState());
        shipment.setCity(orderRequest.getCity());
        shipment.setAddressLine(orderRequest.getAddressLine());
        shipment.setOrder(order);
        return shipment;
    }

    private boolean orderItemsExists(List<OrderItemDto> orderedItems) {
        return true;
    }

//    private boolean isValidCoupon(String couponCode) {
//        return true;
//    }
//
//    private static Double getCouponValue(String CouponCode, Double price) {
//        return price;
//    }
//
//    private Double calculateTotalPrice(List<OrderItemDto> orderItems) {
//        return 0.0;
//    }
//
//    private void consumeFromStock(List<OrderItemDto> orderItems) {
//    }
//
//    private TransactionResponse makeTransaction(PaymentDetails paymentDetails) {
//        return new TransactionResponse();
//    }

    private OrderItem mapToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProductCode(orderItemDto.getProductCode());
        orderItem.setStoreId(orderItemDto.getStoreId());
        return orderItem;
    }
}
