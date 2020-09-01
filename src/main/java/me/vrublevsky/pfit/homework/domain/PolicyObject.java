package me.vrublevsky.pfit.homework.domain;

import java.util.List;

public record PolicyObject(String name, List<PolicySubObject> subObjects) {
}
