package ecommerce.order.api;


import ecommerce.order.dto.OrderRequest;
import ecommerce.order.models.Order;
import ecommerce.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
    }


    @GetMapping("/searchByCustomer")
    public ResponseEntity<List<Order>> searchOrdersByCustomer(@RequestParam String customerEmail) {
        List<Order> orders = orderService.searchOrdersByCustomer(customerEmail);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/searchByDateRange")
    public ResponseEntity<List<Order>> searchOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Order> orders = orderService.searchOrdersByDateRange(startDate, endDate);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
