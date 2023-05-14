package io.ekwateur.infra.cli.services;

import com.google.inject.Inject;
import io.ekwateur.domain.model.ProfessionalClient;
import org.beryx.textio.TextIO;

public class ProfessionalClientReader {
    private final TextIO textIO;
    private final ClientRefReader clientRefReader;
    private final SiretReader siretReader;

    @Inject
    public ProfessionalClientReader(TextIO textIO, ClientRefReader clientRefReader, SiretReader siretReader) {
        this.textIO = textIO;
        this.clientRefReader = clientRefReader;
        this.siretReader = siretReader;
    }

    public ProfessionalClient readProfessionalClient() {
        var clientRef = clientRefReader.readClientRef();
        var siret = siretReader.readSiret();
        var companyName = textIO
                .newStringInputReader()
                .read("Enter the name of the company");
        var annualRevenueInEuros = textIO
                .newIntInputReader()
                .read("Enter the annual income of the company in euros (value should be rounded)");

        return new ProfessionalClient(clientRef, siret, companyName, annualRevenueInEuros);
    }
}
