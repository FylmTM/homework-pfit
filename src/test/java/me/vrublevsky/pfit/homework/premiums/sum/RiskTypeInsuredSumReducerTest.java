package me.vrublevsky.pfit.homework.premiums.sum;

import me.vrublevsky.pfit.homework.domain.Policy;
import me.vrublevsky.pfit.homework.domain.RiskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static me.vrublevsky.pfit.homework.test.Fixtures.emptyPolicy;
import static me.vrublevsky.pfit.homework.test.PolicyBuilder.createPolicy;
import static org.assertj.core.api.Assertions.assertThat;

class RiskTypeInsuredSumReducerTest {

    private Policy policy;

    @BeforeEach
    public void setUp() {
        this.policy = createPolicy()
                .withObject(b -> b
                        .withSubObject(1, RiskType.FIRE, RiskType.THEFT)
                )
                .withObject(b -> b
                        .withSubObject(1, RiskType.FIRE)
                        .withSubObject(2, RiskType.THEFT)
                        .withSubObject(3)
                )
                .withObject(b -> b
                        .withSubObject(1, RiskType.FIRE)
                )
                .withObject(b -> b
                        .withSubObject(2, RiskType.THEFT)
                )
                .withObject(b -> b
                        .withSubObject(3)
                )
                .build();
    }

    @Test
    public void reduceEmpty() {
        var reducer = new RiskTypeInsuredSumReducer(RiskType.FIRE);
        assertThat(reducer.reduce(emptyPolicy)).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void reduceWithFireRisk() {
        var reducer = new RiskTypeInsuredSumReducer(RiskType.FIRE);
        assertThat(reducer.reduce(policy)).isEqualTo(BigDecimal.valueOf(3));
    }

    @Test
    public void reduceWithTheftRisk() {
        var reducer = new RiskTypeInsuredSumReducer(RiskType.THEFT);
        assertThat(reducer.reduce(policy)).isEqualTo(BigDecimal.valueOf(5));
    }
}
