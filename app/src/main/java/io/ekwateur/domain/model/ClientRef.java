package io.ekwateur.domain.model;

import java.util.regex.Pattern;

public final class ClientRef {
    private static final Pattern REF_PATTERN = Pattern.compile("^EKW\\d{8}$");
    private static final String INVALID_REF_ERR = """
            String '%s' does not match format of client reference.
            Client reference should start with prefix 'EKW' followed by 8 digits.
            """;

    private final String value;

    private ClientRef(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ClientRef parse(String str) {
        if (REF_PATTERN.matcher(str).matches()) {
            return new ClientRef(str);
        } else {
            throw new IllegalArgumentException(String.format(INVALID_REF_ERR, str));
        }
    }
}
