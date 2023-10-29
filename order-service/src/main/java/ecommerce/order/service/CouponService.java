package ecommerce.order.service;

import ecommerce.order.dto.CouponResponse;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

public class CouponService {
    private final RestTemplate restTemplate;


    public CouponService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean couponValid(int couponCode) {
        CouponResponse couponResponse = coupon(couponCode);

        if (Objects.equals(couponResponse.endType(), "limit")) {
            return  (couponResponse.usageLimit() < couponResponse.usages());
        } else if (Objects.equals(couponResponse.endType(), "time")) {
            return couponResponse.expirationTime().isAfter(LocalDateTime.now());
        } else {
            return false;
        }
    }

    public CouponResponse coupon(int couponCode) {
        return restTemplate.getForObject(
                "http://localhost:8084/api/v1/coupons/" + couponCode,
                CouponResponse.class);
    }

    public void applyCoupon(int orderId, int couponCode) {

    }


}
