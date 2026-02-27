package com.polycube.backend.domain.discount;

import com.polycube.backend.domain.member.Level;
import com.polycube.backend.domain.member.Member;
import com.polycube.backend.domain.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalDiscountTest {

    @Test
    @DisplayName("일반(NORMAL) 등급은 어떤 할인 정책에서도 0원이 적용되어야 한다")
    void normal_no_discount() {
        // given
        Member member = new Member(3L, Level.NORMAL);
        Order order = new Order("커피", 5000, member);

        DiscountPolicy vip = new VipDiscount();
        DiscountPolicy vvip = new VvipDiscount();

        // when & then
        assertThat(vip.calculateDiscount(order)).isEqualTo(0);
        assertThat(vvip.calculateDiscount(order)).isEqualTo(0);
    }
}