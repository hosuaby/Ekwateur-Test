package io.ekwateur.domain.model;

import io.ekwateur.domain.model.ClientRef;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClientRefTest {

    @Test
    void shouldParseRefWhenInputValid() {

        /* Given */
        var validClientRef = "EKW12345678";

        /* When */
        var parsed = ClientRef.parse(validClientRef);

        /* Then */
        assertThat(parsed)
                .isNotNull()
                .hasFieldOrPropertyWithValue("value", "EKW12345678");
    }

    @Test
    void shouldThrowExceptionWhenTryingParseInvalidRef() {

        /* Given */
        var invalidClientRef = "La vie est belle!";

        /* When */
        ThrowableAssert.ThrowingCallable parsing = () -> ClientRef.parse(invalidClientRef);

        /* Then */
        assertThatThrownBy(parsing)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
