package me.vrublevsky.pfit.homework.premiums.sum;

import me.vrublevsky.pfit.homework.domain.Policy;
import me.vrublevsky.pfit.homework.domain.PolicyStatus;
import me.vrublevsky.pfit.homework.domain.RiskType;
import me.vrublevsky.pfit.homework.test.Fixtures;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class RiskTypeInsuredSumReducerTest {

    @Test
    public void reduceEmpty() {
        var emptyPolicy = new Policy("LV1", PolicyStatus.REGISTERED, emptyList());
        var reducer = new RiskTypeInsuredSumReducer(RiskType.FIRE);
        assertThat(reducer.reduce(emptyPolicy)).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void reduceWithFireRisk() {
        var reducer = new RiskTypeInsuredSumReducer(RiskType.FIRE);
        assertThat(reducer.reduce(Fixtures.policy)).isEqualTo(BigDecimal.valueOf(2));
    }

    @Test
    public void reduceWithTheftRisk() {
        var reducer = new RiskTypeInsuredSumReducer(RiskType.THEFT);
        assertThat(reducer.reduce(Fixtures.policy)).isEqualTo(BigDecimal.valueOf(4));
    }
}
