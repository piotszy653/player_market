package utils;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertionUtils {

    public static <T> void recursiveEqualsIgnoringFields(T expected, T actual, String... fields) {
        assertThat(actual).usingRecursiveComparison().ignoringFields(fields).isEqualTo(expected);
    }

    public static <T> void recursiveEquals(T expected, T actual) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
