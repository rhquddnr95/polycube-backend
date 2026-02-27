package com.polycube.backend.domain.discount;


import com.polycube.backend.domain.member.Level;
import com.polycube.backend.domain.order.Order;
import org.springframework.stereotype.Component;

@Component
public class VvipDiscount implements DiscountPolicy {
    @Override
    public int calculateDiscount(Order order) {
        if (order.member().level() == Level.VVIP) {
            return (int) (order.itemPrice() * 0.1);
        }
        return 0;
    }
}