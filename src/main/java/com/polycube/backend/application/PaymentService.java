package com.polycube.backend.application;

import com.polycube.backend.domain.discount.DiscountPolicy;
import com.polycube.backend.domain.order.Order;
import com.polycube.backend.domain.payment.Payment;
import com.polycube.backend.domain.payment.PaymentMethod;
import com.polycube.backend.domain.payment.PaymentRepository;
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