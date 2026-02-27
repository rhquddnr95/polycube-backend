package com.polycube.backend.exception;

/**
 * 비즈니스 로직 오류 처리를 위한 공통 예외
 * 런타임 예외를 상속받아 트랜잭션 롤백이 가능하도록 설계
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}