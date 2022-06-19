package com.dagobah.helloWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GreeterTest {
    @Test
    public void testGetText() {
        Greeter g = new Greeter();
        assertEquals("Hello World!", g.getText());
    }
}
