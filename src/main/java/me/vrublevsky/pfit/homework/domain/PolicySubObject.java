package me.vrublevsky.pfit.homework.domain;

import java.math.BigDecimal;
import java.util.List;

public record PolicySubObject(String name, BigDecimal sumInsured, List<RiskType> riskTypes) {
}
