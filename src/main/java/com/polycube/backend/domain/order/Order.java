package com.polycube.backend.domain.order;

import com.polycube.backend.domain.member.Member;

public record Order(String itemName, int itemPrice, Member member) {}