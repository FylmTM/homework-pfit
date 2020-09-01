package me.vrublevsky.pfit.homework.test;

import me.vrublevsky.pfit.homework.domain.*;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Fixtures {

    public static Policy policy = new Policy("LV1", PolicyStatus.APPROVED, asList(
            new PolicyObject("Object#1", asList(
                    new PolicySubObject("SubObject#1", BigDecimal.valueOf(1), asList(RiskType.FIRE)),
                    new PolicySubObject("SubObject#2", BigDecimal.valueOf(2), asList(RiskType.THEFT)),
                    new PolicySubObject("SubObject#3", BigDecimal.valueOf(3), asList())
            )),
            new PolicyObject("Object#2", asList(
                    new PolicySubObject("SubObject#1", BigDecimal.valueOf(1), asList(RiskType.FIRE))
            )),
            new PolicyObject("Object#3", asList(
                    new PolicySubObject("SubObject#1", BigDecimal.valueOf(2), asList(RiskType.THEFT))
            )),
            new PolicyObject("Object#4", asList(
                    new PolicySubObject("SubObject#1", BigDecimal.valueOf(3), asList())
            ))
    ));

    public static Policy buildPolicyForRiskType(RiskType riskType, int... insuredSums) {
        var subObjects = Arrays.stream(insuredSums)
                .boxed()
                .map(sumInsured -> new PolicySubObject(
                        "SubObject",
                        BigDecimal.valueOf(sumInsured),
                        asList(riskType)
                ))
                .collect(toList());

        return new Policy(
                "LV1",
                PolicyStatus.APPROVED,
                asList(new PolicyObject("Object", subObjects))
        );
    }
}
