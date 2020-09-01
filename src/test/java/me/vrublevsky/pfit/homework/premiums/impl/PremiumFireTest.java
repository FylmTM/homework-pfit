package me.vrublevsky.pfit.homework.premiums.impl;

import me.vrublevsky.pfit.homework.PremiumCalculatorConfiguration;
import me.vrublevsky.pfit.homework.PremiumCalculatorConfiguration.PremiumFireConfiguration;
import me.vrublevsky.pfit.homework.domain.RiskType;
import me.vrublevsky.pfit.homework.test.Fixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PremiumFireTest {

    private PremiumFire premium;
    private PremiumFireConfiguration configuration;

    @BeforeEach
    public void setUp() {
        this.configuration = PremiumCalculatorConfiguration.normal.premiumFireConfiguration();
        this.premium = new PremiumFire(configuration);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 50, 90, 100})
    public void getDefaultCoefficient(int insuredSum) {
        BigDecimal coefficient = premium.getCoefficient(Fixtures.policy, BigDecimal.valueOf(insuredSum));
        assertThat(coefficient).isEqualTo(configuration.coefficientDefault());
    }

    @ParameterizedTest
    @ValueSource(ints = {101, 150})
    public void getAdjustedCoefficient(int insuredSum) {
        BigDecimal coefficient = premium.getCoefficient(Fixtures.policy, BigDecimal.valueOf(insuredSum));
        assertThat(coefficient).isEqualTo(configuration.coefficientAdjusted());
    }

    @Test
    public void calculateZero() {
        var policy = Fixtures.buildPolicyForRiskType(RiskType.FIRE);
        var premiumAmount = premium.calculate(policy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    public void calculateWithDefaultCoefficient() {
        var policy = Fixtures.buildPolicyForRiskType(RiskType.FIRE, 10, 10);
        var premiumAmount = premium.calculate(policy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.valueOf(0.28));
    }

    @Test
    public void calculateWithAdjustedCoefficient() {
        var policy = Fixtures.buildPolicyForRiskType(RiskType.FIRE, 100, 100);
        var premiumAmount = premium.calculate(policy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.valueOf(4.8));
    }
}
