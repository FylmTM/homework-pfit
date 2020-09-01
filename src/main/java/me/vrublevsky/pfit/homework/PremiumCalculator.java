package me.vrublevsky.pfit.homework;

import me.vrublevsky.pfit.homework.premiums.Premium;

import java.math.BigDecimal;
import java.util.List;

public class PremiumCalculator {

    private final List<Premium> premiums;

    public PremiumCalculator(List<Premium> premiums) {
        this.premiums = premiums;
    }

    public BigDecimal calculate() {
        return BigDecimal.ZERO;
    }
}
