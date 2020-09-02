package me.vrublevsky.pfit.homework.test;

import me.vrublevsky.pfit.homework.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class PolicyBuilder {

    public static PolicyBuilder createPolicy() {
        return new PolicyBuilder();
    }

    private String name = "Policy";
    private PolicyStatus status = PolicyStatus.APPROVED;
    private List<PolicyObject> policyObjects = new ArrayList<>();

    private PolicyBuilder() {
    }

    public PolicyBuilder withObject(Function<PolicyObjectBuilder, PolicyObjectBuilder> f) {
        policyObjects.add(f.apply(new PolicyObjectBuilder()).build());
        return this;
    }

    public Policy build() {
        return new Policy(name, status, policyObjects);
    }

    public static class PolicyObjectBuilder {

        private String name = "Object";
        private List<PolicySubObject> subObjects = new ArrayList<>();

        public PolicyObjectBuilder withSubObject(int sumInsured, RiskType... riskTypes) {
            withSubObject(BigDecimal.valueOf(sumInsured), riskTypes);
            return this;
        }

        public PolicyObjectBuilder withSubObject(double sumInsured, RiskType... riskTypes) {
            withSubObject(BigDecimal.valueOf(sumInsured), riskTypes);
            return this;
        }

        public PolicyObjectBuilder withSubObject(BigDecimal sumInsured, RiskType... riskTypes) {
            subObjects.add(new PolicySubObject("SubObject", sumInsured, Arrays.asList(riskTypes)));
            return this;
        }

        public PolicyObject build() {
            return new PolicyObject(name, subObjects);
        }
    }
}
