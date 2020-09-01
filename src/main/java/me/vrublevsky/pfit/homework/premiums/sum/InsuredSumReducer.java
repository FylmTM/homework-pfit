package me.vrublevsky.pfit.homework.premiums.sum;

import me.vrublevsky.pfit.homework.domain.Policy;

import java.math.BigDecimal;

public interface InsuredSumReducer {

    BigDecimal reduce(Policy policy);
}
