package org.example;


import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    @Test
    void shouldReturn3() {
        Input:
        assertEquals("3", Main.calc("1 + 2"));
    }

    @Test
    void shouldReturnMinus1() {
        assertEquals("-1", Main.calc("1 - 2"));
    }

    @Test
    void shouldReturn2() {
        assertEquals("2", Main.calc("1 * 2"));
    }


    @Test
    void shouldReturnII() {
        assertEquals("II", Main.calc("VI / III"));
    }

    @Test
    void shouldReturnLXXII() {
        assertEquals("LXXII", Main.calc("IX * VIII"));
    }

    @Test
    void shouldThrows1() {
        assertThrows(NumberFormatException.class, () -> Main.calc("I-II"));
    }

    @Test
    void shouldThrows2() {
        assertThrows(NumberFormatException.class, () -> Main.calc("I + 1"));
    }

    @Test
    void shouldThrows3() {
        assertThrows(NumberFormatException.class, () -> Main.calc("1"));
    }

    @Test
    void shouldThrows4() {
        assertThrows(NumberFormatException.class, () -> Main.calc("1 + 2 + 3"));

    }
}