package io.ekwateur.domain.model;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SiretTest {

    @Test
    void shouldParseSiretWhenInputValid() {

        /* Given */
        var validSiret = "91433600300019";

        /* When */
        var parsed = Siret.parse(validSiret);

        /* Then */
        assertThat(parsed)
                .isNotNull()
                .hasFieldOrPropertyWithValue("value", "91433600300019");
    }

    @Test
    void shouldThrowExceptionWhenTryingParseInvalidSiret() {

        /* Given */
        var invalidSiret = "La vie est belle!";

        /* When */
        ThrowableAssert.ThrowingCallable parsing = () -> Siret.parse(invalidSiret);

        /* Then */
        assertThatThrownBy(parsing)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
