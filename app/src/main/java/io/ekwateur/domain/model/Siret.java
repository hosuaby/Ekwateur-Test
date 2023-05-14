package io.ekwateur.domain.model;

import java.util.regex.Pattern;

public final class Siret {
    private static final Pattern SIRET_PATTERN = Pattern.compile("^\\d{14}$");
    private static final String INVALID_SIRET = """
            String '%s' does not match format of french SIRET.
            SIRET should be composed of 14 digits.
            """;

    private final String value;

    private Siret(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Siret parse(String str) {
        if (SIRET_PATTERN.matcher(str).matches()) {
            return new Siret(str);
        } else {
            throw new IllegalArgumentException(String.format(INVALID_SIRET, str));
        }
    }
}
