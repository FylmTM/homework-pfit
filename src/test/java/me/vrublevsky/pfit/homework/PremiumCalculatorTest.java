package me.vrublevsky.pfit.homework;

import me.vrublevsky.pfit.homework.domain.Policy;
import me.vrublevsky.pfit.homework.domain.RiskType;
import me.vrublevsky.pfit.homework.premiums.impl.PremiumFire;
import me.vrublevsky.pfit.homework.premiums.impl.PremiumTheft;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static me.vrublevsky.pfit.homework.PremiumCalculatorConfiguration.appConfiguration;
import static me.vrublevsky.pfit.homework.test.Fixtures.emptyPolicy;
import static me.vrublevsky.pfit.homework.test.PolicyBuilder.createPolicy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PremiumCalculatorTest {

    private PremiumCalculator calculator;

    @BeforeEach
    public void setUp() {
        this.calculator = new PremiumCalculator(
                appConfiguration.currency(),
                asList(
                        new PremiumFire(appConfiguration.premiumFireConfiguration()),
                        new PremiumTheft(appConfiguration.premiumTheftConfiguration())
                )
        );
    }

    @Test
    public void calculateForEmptyPolicy() {
        assertThat(calculator.calculate(emptyPolicy)).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @ParameterizedTest
    @MethodSource("policies")
    public void calculateForPolicy(Policy policy, BigDecimal premiumAmount) {
        assertThat(calculator.calculate(policy)).isEqualByComparingTo(premiumAmount);
    }

    public static Stream<Arguments> policies() {
        return Stream.of(
                arguments(
                        createPolicy()
                                .withObject(b -> b
                                        .withSubObject(100.00, RiskType.FIRE)
                                        .withSubObject(8.00, RiskType.THEFT)
                                )
                                .build(),
                        BigDecimal.valueOf(2.28)
                ),
                arguments(
                        createPolicy()
                                .withObject(b -> b
                                        .withSubObject(500.00, RiskType.FIRE)
                                        .withSubObject(102.51, RiskType.THEFT)
                                )
                                .build(),
                        BigDecimal.valueOf(17.13)
                )
        );
    }
}
