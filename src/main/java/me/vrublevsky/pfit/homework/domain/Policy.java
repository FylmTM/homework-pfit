package me.vrublevsky.pfit.homework.domain;

import java.util.List;

public record Policy(String number, PolicyStatus status, List<PolicyObject> objects) {
}
