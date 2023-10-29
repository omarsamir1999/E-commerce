package ecommerce.order.dto;

import java.time.LocalDateTime;

public record CouponResponse(int id, String code, LocalDateTime expirationTime, int usageLimit, int usages,
                             String endType, double value, double percentage, String discountType) {
}
