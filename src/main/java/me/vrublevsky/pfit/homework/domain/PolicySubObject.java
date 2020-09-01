package me.vrublevsky.pfit.homework.domain;

import java.math.BigDecimal;
import java.util.Set;

public record PolicySubObject(String name, BigDecimal sumInsured, Set<RiskType> riskType) {
}
