package dev.academy.function.unclean;

import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.academy.function.unclean.ConvertFiles.*;
import static org.assertj.core.api.Assertions.assertThat;

class ConvertFilesTest {

    @Test
    public void testConvertListToString() {
        assertThat(convertListToString(List.of("a", "0", "0 0"), "#")).isEqualTo("a#0#0 0");
        assertThat(convertListToString(List.of(), "#")).isEqualTo("");
        assertThat(convertListToString(List.of("-.,", "", "abc"), "#")).isEqualTo("-.,##abc");
    }

    @Test
    public void testConvertStringToList() {
        assertThat(convertStringToList("a#0#0 0", "#")).isEqualTo(List.of("a", "0", "0 0"));
        assertThat(convertStringToList("", "#")).isEqualTo(List.of(""));
        assertThat(convertStringToList("-.,##abc", "#")).isEqualTo(List.of("-.,", "", "abc"));
    }

    @Test
    public void testIsNotNull() {
        assertThat(isNotNull("", "abc")).isTrue();
        assertThat(isNotNull(null, "abc")).isFalse();
        assertThat(isNotNull("", null)).isFalse();
    }

}
