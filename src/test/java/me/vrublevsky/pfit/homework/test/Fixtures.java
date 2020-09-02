package me.vrublevsky.pfit.homework.test;

import me.vrublevsky.pfit.homework.domain.Policy;

import static me.vrublevsky.pfit.homework.test.PolicyBuilder.createPolicy;

public class Fixtures {

    public static Policy emptyPolicy = createPolicy().build();
}
