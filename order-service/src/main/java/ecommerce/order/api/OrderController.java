package ecommerce.order.api;


import ecommerce.order.models.Order;
import ecommerce.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public Order findById(@PathVariable long id) {
        return orderService.findById(id);
    }

}
