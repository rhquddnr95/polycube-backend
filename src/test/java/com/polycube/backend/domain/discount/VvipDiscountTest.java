package com.polycube.backend.domain.discount;

import com.polycube.backend.domain.member.Level;
import com.polycube.backend.domain.member.Member;
import com.polycube.backend.domain.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VvipDiscountTest {

    private final VvipDiscount discount = new VvipDiscount();

    @Test
    @DisplayName("VVIP 등급은 주문 금액의 10% 할인이 적용되어야 한다")
    void vvip_discount_success() {
        // given
        Member member = new Member(1L, Level.VVIP);
        Order order = new Order("맥북", 2000000, member);

        // when
        int result = discount.calculateDiscount(order);

        // then
        assertThat(result).isEqualTo(200000);
    }

    @Test
    @DisplayName("VVIP가 아닌 등급은 할인이 적용되지 않아야 한다")
    void vvip_discount_fail() {
        Member member = new Member(2L, Level.VIP);
        Order order = new Order("맥북", 2000000, member);

        int result = discount.calculateDiscount(order);

        assertThat(result).isEqualTo(0);
    }
}