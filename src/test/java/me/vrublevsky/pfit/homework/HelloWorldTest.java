package me.vrublevsky.pfit.homework;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldTest {

    @Test
    public void name() {
        assertThat(HelloWorld.NUMBER).isEqualTo(42);
    }
}
