package com.polycube.backend.application;

import com.polycube.backend.domain.discount.DiscountPolicy;
import com.polycube.backend.domain.order.Order;
import com.polycube.backend.domain.payment.Payment;
import com.polycube.backend.domain.payment.PaymentMethod;
import com.polycube.backend.domain.payment.PaymentRepository;
import com.polycube.backend.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final List<DiscountPolicy> discountPolicies;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment processPayment(Order order, PaymentMethod method) {
        // 비즈니스 검증: 결제 금액이 0원 이하가 될 수 없는 규칙이 있다면?
        if (order.itemPrice() < 0) {
            throw new BusinessException("결제 금액은 0원보다 커야 합니다.");
        }

        int discountAmount = discountPolicies.stream()
                .mapToInt(policy -> policy.calculateDiscount(order))
                .max()
                .orElse(0);

        // 결제 이력 생성 및 저장
        Payment payment = Payment.builder()
                .itemName(order.itemName())
                .originalPrice(order.itemPrice())
                .discountAmount(discountAmount)
                .method(method)
                .build();

        return paymentRepository.save(payment);
    }
}