package me.vrublevsky.pfit.homework.premiums;

import me.vrublevsky.pfit.homework.domain.Policy;

import java.math.BigDecimal;

public interface Premium {

    BigDecimal calculate(Policy policy);
}
