package io.ekwateur.infra.cli.services;

import com.google.inject.Inject;
import io.ekwateur.domain.services.calculator.ConsumptionPriceCalculator;
import io.ekwateur.infra.cli.model.ClientType;
import org.beryx.textio.TextIO;

public class CliService implements Runnable {
    private final TextIO textIO;
    private final PrivateClientReader privateClientReader;
    private final ProfessionalClientReader professionalClientReader;
    private final ConsumptionReader consumptionReader;
    private final ConsumptionPriceCalculator consumptionPriceCalculator;
    private final InvoicePrinter invoicePrinter;

    @Inject
    public CliService(
            TextIO textIO,
            PrivateClientReader privateClientReader,
            ProfessionalClientReader professionalClientReader,
            ConsumptionReader consumptionReader,
            ConsumptionPriceCalculator consumptionPriceCalculator,
            InvoicePrinter invoicePrinter) {
        this.textIO = textIO;
        this.privateClientReader = privateClientReader;
        this.professionalClientReader = professionalClientReader;
        this.consumptionReader = consumptionReader;
        this.consumptionPriceCalculator = consumptionPriceCalculator;
        this.invoicePrinter = invoicePrinter;
    }

    @Override
    public void run() {
        var clientType = textIO
                .newEnumInputReader(ClientType.class)
                .read("What is the type of your client");

        var client = switch (clientType) {
            case PRIVATE -> privateClientReader.readPrivateClient();
            case PRO -> professionalClientReader.readProfessionalClient();
        };
        var consumption = consumptionReader.readConsumption();
        var totalPrice = consumptionPriceCalculator.calculatePrice(client, consumption);

        invoicePrinter.printInvoice(client, totalPrice);
    }
}
