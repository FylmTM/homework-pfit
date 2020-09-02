package me.vrublevsky.pfit.homework;

import me.vrublevsky.pfit.homework.domain.*;
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
                        new Policy("LV1", PolicyStatus.APPROVED, asList(
                                new PolicyObject("Object", asList(
                                        new PolicySubObject("SubObject#1", BigDecimal.valueOf(100.00), asList(RiskType.FIRE)),
                                        new PolicySubObject("SubObject#2", BigDecimal.valueOf(8.00), asList(RiskType.THEFT))
                                ))
                        )),
                        BigDecimal.valueOf(2.28)
                ),
                arguments(
                        new Policy("LV1", PolicyStatus.APPROVED, asList(
                                new PolicyObject("Object", asList(
                                        new PolicySubObject("SubObject#1", BigDecimal.valueOf(500.00), asList(RiskType.FIRE)),
                                        new PolicySubObject("SubObject#2", BigDecimal.valueOf(102.51), asList(RiskType.THEFT))
                                ))
                        )),
                        BigDecimal.valueOf(17.13)
                )
        );
    }
}
