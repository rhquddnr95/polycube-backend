package com.polycube.backend.domain.discount;

import com.polycube.backend.domain.member.Level;
import com.polycube.backend.domain.member.Member;
import com.polycube.backend.domain.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VipDiscountTest {

    private final VipDiscount discount = new VipDiscount();

    @Test
    @DisplayName("VIP 등급은 1000원 고정 할인이 적용되어야 한다")
    void vip_discount_success() {
        // given
        Member member = new Member(1L, Level.VIP);
        Order order = new Order("상품A", 10000, member);

        // when
        int result = discount.calculateDiscount(order);

        // then
        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아닌 등급(NORMAL)은 할인이 적용되지 않아야 한다")
    void vip_discount_fail() {
        // given
        Member member = new Member(2L, Level.NORMAL);
        Order order = new Order("상품A", 10000, member);

        // when
        int result = discount.calculateDiscount(order);

        // then
        assertThat(result).isEqualTo(0);
    }
}