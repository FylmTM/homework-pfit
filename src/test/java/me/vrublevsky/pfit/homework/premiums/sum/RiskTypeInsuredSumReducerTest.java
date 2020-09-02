package me.vrublevsky.pfit.homework.premiums.sum;

import me.vrublevsky.pfit.homework.domain.RiskType;
import me.vrublevsky.pfit.homework.test.Fixtures;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static me.vrublevsky.pfit.homework.test.Fixtures.emptyPolicy;
import static org.assertj.core.api.Assertions.assertThat;

class RiskTypeInsuredSumReducerTest {

    @Test
    public void reduceEmpty() {
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
