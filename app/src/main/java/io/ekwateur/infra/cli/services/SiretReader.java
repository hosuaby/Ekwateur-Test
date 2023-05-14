package io.ekwateur.infra.cli.services;

import com.google.inject.Inject;
import io.ekwateur.domain.model.Siret;
import org.beryx.textio.InputReader;
import org.beryx.textio.TextIO;

import java.util.List;

public final class SiretReader {
    private final TextIO textIO;

    @Inject
    public SiretReader(TextIO textIO) {
        this.textIO = textIO;
    }

    public Siret readSiret() {
        String siret = textIO
                .newStringInputReader()
                .withValueChecker(SiretChecker.INSTANCE)
                .read("Enter the SIRET of the company");
        return Siret.parse(siret);
    }

    private static final class SiretChecker implements InputReader.ValueChecker<String> {
        private static final SiretChecker INSTANCE = new SiretChecker();

        private SiretChecker() {
        }

        @Override
        public List<String> getErrorMessages(String val, String itemName) {
            try {
                Siret.parse(val);
                return null;
            } catch (IllegalArgumentException invalidClientRefException) {
                return List.of(invalidClientRefException.getMessage());
            }
        }
    }
}
