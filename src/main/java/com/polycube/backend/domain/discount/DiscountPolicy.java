package com.polycube.backend.domain.discount;

import com.polycube.backend.domain.order.Order;

public interface DiscountPolicy {
    int calculateDiscount(Order order);
}