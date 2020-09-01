package me.vrublevsky.pfit.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class PremiumCalculatorTest {

    private PremiumCalculator calculator;

    @BeforeEach
    public void setUp() {
        this.calculator = new PremiumCalculator(asList());
    }

    @Test
    public void getCoefficient() {
        assertThat(calculator.calculate()).isEqualTo(BigDecimal.ZERO);
    }
}
