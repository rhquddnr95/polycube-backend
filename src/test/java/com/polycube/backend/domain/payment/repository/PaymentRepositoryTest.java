package com.polycube.backend.domain.payment.repository;

import com.polycube.backend.domain.payment.Payment;
import com.polycube.backend.domain.payment.PaymentMethod;
import com.polycube.backend.domain.payment.PaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // JPA 관련 설정만 불러와서 테스트 (속도 빠름, H2 자동 연결)
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    @DisplayName("결제 정보가 인메모리 DB에 성공적으로 저장되고 조회되어야 한다")
    void save_and_find_payment() {
        // given: 저장할 결제 데이터 생성 (Builder 사용)
        Payment payment = Payment.builder()
                .itemName("아이폰 15")
                .originalPrice(1500000)
                .discountAmount(150000)
                .method(PaymentMethod.CREDIT_CARD)
                .build();

        // when: DB 저장
        paymentRepository.save(payment);

        // then: DB에서 다시 조회하여 검증
        List<Payment> allPayments = paymentRepository.findAll();

        assertThat(allPayments).hasSize(1);
        Payment savedPayment = allPayments.get(0);

        assertThat(savedPayment.getItemName()).isEqualTo("아이폰 15");
        assertThat(savedPayment.getFinalPrice()).isEqualTo(1350000); // 150만 - 15만
        assertThat(savedPayment.getMethod()).isEqualTo(PaymentMethod.CREDIT_CARD);
    }
}