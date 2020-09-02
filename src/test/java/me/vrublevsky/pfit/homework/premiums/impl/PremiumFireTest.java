package me.vrublevsky.pfit.homework.premiums.impl;

import me.vrublevsky.pfit.homework.PremiumCalculatorConfiguration.PremiumFireConfiguration;
import me.vrublevsky.pfit.homework.domain.RiskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static me.vrublevsky.pfit.homework.PremiumCalculatorConfiguration.appConfiguration;
import static me.vrublevsky.pfit.homework.test.Fixtures.emptyPolicy;
import static me.vrublevsky.pfit.homework.test.PolicyBuilder.createPolicy;
import static org.assertj.core.api.Assertions.assertThat;

class PremiumFireTest {

    private PremiumFire premium;
    private PremiumFireConfiguration configuration;

    @BeforeEach
    public void setUp() {
        this.configuration = appConfiguration.premiumFireConfiguration();
        this.premium = new PremiumFire(configuration);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 50, 90, 100})
    public void getDefaultCoefficient(int insuredSum) {
        BigDecimal coefficient = premium.getCoefficient(emptyPolicy, BigDecimal.valueOf(insuredSum));
        assertThat(coefficient).isEqualTo(configuration.coefficientDefault());
    }

    @ParameterizedTest
    @ValueSource(ints = {101, 150})
    public void getAdjustedCoefficient(int insuredSum) {
        BigDecimal coefficient = premium.getCoefficient(emptyPolicy, BigDecimal.valueOf(insuredSum));
        assertThat(coefficient).isEqualTo(configuration.coefficientAdjusted());
    }

    @Test
    public void calculateZero() {
        var premiumAmount = premium.calculate(emptyPolicy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    public void calculateWithDefaultCoefficient() {
        var policy = createPolicy()
                .withObject(b -> b
                        .withSubObject(10, RiskType.FIRE)
                        .withSubObject(10, RiskType.FIRE)
                )
                .build();
        var premiumAmount = premium.calculate(policy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.valueOf(0.28));
    }

    @Test
    public void calculateWithAdjustedCoefficient() {
        var policy = createPolicy()
                .withObject(b -> b
                        .withSubObject(100, RiskType.FIRE)
                        .withSubObject(100, RiskType.FIRE)
                )
                .build();
        var premiumAmount = premium.calculate(policy);
        assertThat(premiumAmount).isEqualByComparingTo(BigDecimal.valueOf(4.8));
    }
}
