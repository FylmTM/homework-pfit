package me.vrublevsky.pfit.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PremiumCalculatorTest {

    private PremiumCalculator calculator;

    @BeforeEach
    public void setUp() {
        this.calculator = new PremiumCalculator();
    }

    @Test
    public void name() {
        assertThat(calculator.calculate()).isEqualTo(BigDecimal.ZERO);
    }
}
