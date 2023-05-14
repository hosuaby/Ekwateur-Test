package io.ekwateur.infra.cli.services;

import com.google.inject.Inject;
import io.ekwateur.domain.model.PrivateClient;
import org.beryx.textio.TextIO;

public class PrivateClientReader {
    private final TextIO textIO;
    private final ClientRefReader clientRefReader;

    @Inject
    public PrivateClientReader(TextIO textIO, ClientRefReader clientRefReader) {
        this.textIO = textIO;
        this.clientRefReader = clientRefReader;
    }

    public PrivateClient readPrivateClient() {
        var clientRef = clientRefReader.readClientRef();
        var civility = textIO
                .newStringInputReader()
                .read("Enter clients civility");
        var firstName = textIO
                .newStringInputReader()
                .read("Enter clients first name");
        var lastName = textIO
                .newStringInputReader()
                .read("Enter clients last name");

        return new PrivateClient(clientRef, civility, firstName, lastName);
    }
}
