package me.vrublevsky.pfit.homework.premiums.impl;

import me.vrublevsky.pfit.homework.PremiumCalculatorConfiguration;
import me.vrublevsky.pfit.homework.PremiumCalculatorConfiguration.PremiumTheftConfiguration;
import me.vrublevsky.pfit.homework.domain.RiskType;
import me.vrublevsky.pfit.homework.test.Fixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PremiumTheftTest {

    private PremiumTheft premium;
    private PremiumTheftConfiguration configuration;

    @BeforeEach
    public void setUp() {
        this.configuration = PremiumCalculatorConfiguration.normal.premiumTheftConfiguration();
        this.premium = new PremiumTheft(configuration);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 14})
    public void getDefaultCoefficient(int insuredSum) {
        BigDecimal coefficient = premium.getCoefficient(Fixtures.policy, BigDecimal.valueOf(insuredSum));
        assertThat(coefficient).isEqualTo(configuration.coefficientDefault());
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30})
    public void getAdjustedCoefficient(int insuredSum) {
        BigDecimal coefficient = premium.getCoefficient(Fixtures.policy, BigDecimal.valueOf(insuredSum));
        assertThat(coefficient).isEqualTo(configuration.coefficientAdjusted());
    }

    @Test
    public void calculateZero() {
        var policy = Fixtures.buildPolicyForRiskType(RiskType.THEFT);
        var premiumAmount = premium.calculate(policy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    public void calculateWithDefaultCoefficient() {
        var policy = Fixtures.buildPolicyForRiskType(RiskType.THEFT, 2, 3);
        var premiumAmount = premium.calculate(policy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.valueOf(0.55));
    }

    @Test
    public void calculateWithAdjustedCoefficient() {
        var policy = Fixtures.buildPolicyForRiskType(RiskType.THEFT, 10, 10);
        var premiumAmount = premium.calculate(policy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.valueOf(1));
    }
}
