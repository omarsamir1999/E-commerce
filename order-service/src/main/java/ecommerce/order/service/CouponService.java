package ecommerce.order.service;

import ecommerce.order.models.Order;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CouponService {
    private final String COUPON_SERVICE_BASE_URL = "http://coupon-service-url"; // Replace with your actual URL

    private final RestTemplate restTemplate;

    public CouponService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValidCoupon(String couponCode) {
        String url = COUPON_SERVICE_BASE_URL + "/validateCoupon?code=" + couponCode;
        // Make a GET request to the coupon service endpoint to validate the coupon
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }


    public void consumeCoupon(Order order) {
        String couponCode = order.getCouponCode();
        Double totalCost = order.getPrice();

        String url = COUPON_SERVICE_BASE_URL + "/getCouponDetails?code=" + couponCode;
        // Make a GET request to the coupon service endpoint to fetch coupon details
        // Example:
        CouponDetails couponDetails = restTemplate.getForObject(url, CouponDetails.class);

        // Assume CouponDetails has fields like type (FIXED_VALUE or PERCENTAGE) and value
        Double value = couponDetails.getValue();
        String type = couponDetails.getType();

        Double paidPrice = order.getPrice();
        if (type.equals("FIXED")) {
            paidPrice -= value;
        } else if (type.equals("PERCENTAGE")) {
            // Calculate the discount based on percentage
            Double discount = (value / 100.0) * totalCost;
            paidPrice -= discount;
        }
        if (paidPrice < 0.0) {
            paidPrice = 0.0;
        }
        // Update the order's total cost after applying the coupon
        order.setPaidPrice(paidPrice);
    }

}


@Data
class CouponDetails {
    private String type; // Example: "FIXED_VALUE" or "PERCENTAGE"
    private Double value; // Example: 10.0 for fixed value or 20.0 for percentage
}
