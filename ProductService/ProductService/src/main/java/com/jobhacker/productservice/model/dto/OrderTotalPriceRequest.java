package com.jobhacker.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotalPriceRequest {

    private List<OrderItemDto> orderItemDtoList;
}
