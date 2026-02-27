package com.polycube.backend.domain.discount;

import com.polycube.backend.domain.member.Level;
import com.polycube.backend.domain.order.Order;
import org.springframework.stereotype.Component;

@Component
public class VipDiscount implements DiscountPolicy {
    @Override
    public int calculateDiscount(Order order) {
        return order.member().level() == Level.VIP ? 1000 : 0;
    }
}