package me.vrublevsky.pfit.homework.premiums.impl;

import me.vrublevsky.pfit.homework.PremiumCalculatorConfiguration.PremiumTheftConfiguration;
import me.vrublevsky.pfit.homework.domain.Policy;
import me.vrublevsky.pfit.homework.domain.RiskType;
import me.vrublevsky.pfit.homework.premiums.PremiumWithCoefficient;
import me.vrublevsky.pfit.homework.premiums.sum.InsuredSumReducer;
import me.vrublevsky.pfit.homework.premiums.sum.RiskTypeInsuredSumReducer;

import java.math.BigDecimal;

public class PremiumTheft implements PremiumWithCoefficient {

    private static final InsuredSumReducer insuredSumReducer = new RiskTypeInsuredSumReducer(RiskType.THEFT);
    private final BigDecimal coefficientDefault;
    private final BigDecimal coefficientDefaultLimit;
    private final BigDecimal coefficientAdjusted;

    public PremiumTheft(PremiumTheftConfiguration configuration) {
        this.coefficientDefault = configuration.coefficientDefault();
        this.coefficientDefaultLimit = configuration.coefficientDefaultLimit();
        this.coefficientAdjusted = configuration.coefficientAdjusted();
    }

    @Override
    public InsuredSumReducer getInsuredSumReducer() {
        return insuredSumReducer;
    }

    @Override
    public BigDecimal getCoefficient(Policy policy, BigDecimal insuredSum) {
        if (insuredSum.compareTo(coefficientDefaultLimit) >= 0) {
            return coefficientAdjusted;
        }
        return coefficientDefault;
    }
}
