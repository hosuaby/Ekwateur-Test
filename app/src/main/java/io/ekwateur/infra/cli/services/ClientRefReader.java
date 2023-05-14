package io.ekwateur.infra.cli.services;

import com.google.inject.Inject;
import io.ekwateur.domain.model.ClientRef;
import org.beryx.textio.InputReader;
import org.beryx.textio.TextIO;

import java.util.List;

public final class ClientRefReader {
    private final TextIO textIO;

    @Inject
    public ClientRefReader(TextIO textIO) {
        this.textIO = textIO;
    }

    public ClientRef readClientRef() {
        String clientRef = textIO
                .newStringInputReader()
                .withValueChecker(ClientRefChecker.INSTANCE)
                .read("Enter the client reference");
        return ClientRef.parse(clientRef);
    }

    private static final class ClientRefChecker implements InputReader.ValueChecker<String> {
        private static final ClientRefChecker INSTANCE = new ClientRefChecker();

        private ClientRefChecker() {
        }

        @Override
        public List<String> getErrorMessages(String val, String itemName) {
            try {
                ClientRef.parse(val);
                return null;
            } catch (IllegalArgumentException invalidClientRefException) {
                return List.of(invalidClientRefException.getMessage());
            }
        }
    }
}
