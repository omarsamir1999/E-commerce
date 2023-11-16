package com.jobhacker.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotalPriceResponse {

    private Double totalPrice;
}
