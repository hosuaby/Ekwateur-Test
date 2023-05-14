package io.ekwateur.infra.cli.services;

import com.google.inject.Inject;
import io.ekwateur.domain.model.Client;
import io.ekwateur.domain.model.PrivateClient;
import io.ekwateur.domain.model.ProfessionalClient;
import org.beryx.textio.TextTerminal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public final class InvoicePrinter {
    private static final NumberFormat EURO_FORMATTER = NumberFormat.getCurrencyInstance(Locale.FRANCE);
    private final TextTerminal terminal;

    @Inject
    public InvoicePrinter(TextTerminal terminal) {
        this.terminal = terminal;
    }

    public void printInvoice(Client client, BigDecimal totalPriceInEuros) {
        if (client instanceof PrivateClient) {
            printPrivateClientInvoice((PrivateClient) client, totalPriceInEuros);
        } else {
            printProfessionalClientInvoice((ProfessionalClient) client,totalPriceInEuros);
        }
    }

    private void printPrivateClientInvoice(PrivateClient client, BigDecimal totalPriceInEuros) {
        terminal.printf("""
                ############################################

                INVOICE
                for %s %s %s
                Client ref: %s

                -----------
                Total: %s

                ############################################
                """,
                client.getCivility(),
                client.getFirstName(),
                client.getLastName(),
                client.getClientRef().getValue(),
                EURO_FORMATTER.format(totalPriceInEuros));
    }

    private void printProfessionalClientInvoice(ProfessionalClient client, BigDecimal totalPriceInEuros) {
        terminal.printf("""
                ############################################

                INVOICE
                for %s
                Client ref: %s
                SIRET: %s

                -----------
                Total: %s

                ############################################
                """,
                client.getCompanyName(),
                client.getClientRef().getValue(),
                client.getSiret().getValue(),
                EURO_FORMATTER.format(totalPriceInEuros));
    }
}
