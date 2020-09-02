package me.vrublevsky.pfit.homework;

import me.vrublevsky.pfit.homework.domain.Policy;
import me.vrublevsky.pfit.homework.premiums.Premium;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;

public class PremiumCalculator {

    private final Currency currency;
    private final List<Premium> premiums;

    public PremiumCalculator(Currency currency, List<Premium> premiums) {
        this.currency = currency;
        this.premiums = premiums;
    }

    public BigDecimal calculate(Policy policy) {
        return premiums.stream()
                .map(p -> p.calculate(policy))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_DOWN);
    }
}
