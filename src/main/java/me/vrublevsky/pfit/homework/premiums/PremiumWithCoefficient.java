package me.vrublevsky.pfit.homework.premiums;

import me.vrublevsky.pfit.homework.domain.Policy;
import me.vrublevsky.pfit.homework.premiums.sum.InsuredSumReducer;

import java.math.BigDecimal;

public interface PremiumWithCoefficient extends Premium {

    InsuredSumReducer getInsuredSumReducer();

    BigDecimal getCoefficient(Policy policy, BigDecimal insuredSum);

    @Override
    default BigDecimal calculate(Policy policy) {
        var insuredSum = getInsuredSumReducer().reduce(policy);
        var coefficient = getCoefficient(policy, insuredSum);
        return insuredSum.multiply(coefficient);
    }
}
