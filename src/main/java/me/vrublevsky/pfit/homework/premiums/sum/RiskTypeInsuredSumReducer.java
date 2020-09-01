package me.vrublevsky.pfit.homework.premiums.sum;

import me.vrublevsky.pfit.homework.domain.Policy;
import me.vrublevsky.pfit.homework.domain.PolicySubObject;
import me.vrublevsky.pfit.homework.domain.RiskType;

import java.math.BigDecimal;

public class RiskTypeInsuredSumReducer implements InsuredSumReducer {

    private RiskType riskType;

    public RiskTypeInsuredSumReducer(RiskType riskType) {
        this.riskType = riskType;
    }

    @Override
    public BigDecimal reduce(Policy policy) {
        return policy.objects().stream()
                .flatMap(o -> o.subObjects().stream())
                .filter(so -> so.riskTypes().contains(riskType))
                .map(PolicySubObject::sumInsured)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
