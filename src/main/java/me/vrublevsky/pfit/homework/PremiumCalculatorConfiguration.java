package me.vrublevsky.pfit.homework;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * With DI available configuration values could be injected directly into components.
 */
public record PremiumCalculatorConfiguration(
        Currency currency,
        PremiumFireConfiguration premiumFireConfiguration,
        PremiumTheftConfiguration premiumTheftConfiguration
) {
    public static PremiumCalculatorConfiguration appConfiguration = new PremiumCalculatorConfiguration(
            Currency.getInstance("EUR"),
            new PremiumFireConfiguration(
                    BigDecimal.valueOf(0.014),
                    BigDecimal.valueOf(100),
                    BigDecimal.valueOf(0.024)
            ),
            new PremiumTheftConfiguration(
                    BigDecimal.valueOf(0.11),
                    BigDecimal.valueOf(15),
                    BigDecimal.valueOf(0.05)
            )
    );

    public record PremiumFireConfiguration(
            BigDecimal coefficientDefault,
            BigDecimal coefficientDefaultLimit,
            BigDecimal coefficientAdjusted
    ) {
    }

    public record PremiumTheftConfiguration(
            BigDecimal coefficientDefault,
            BigDecimal coefficientDefaultLimit,
            BigDecimal coefficientAdjusted
    ) {
    }
}
