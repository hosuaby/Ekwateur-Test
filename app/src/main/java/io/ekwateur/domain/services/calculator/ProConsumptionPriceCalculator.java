package io.ekwateur.domain.services.calculator;

import com.google.inject.Inject;
import io.ekwateur.domain.model.Client;
import io.ekwateur.domain.model.Consumption;
import io.ekwateur.domain.model.ProfessionalClient;

import java.math.BigDecimal;

public final class ProConsumptionPriceCalculator implements IConsumptionPriceCalculator {
    private static final int discountThreshold = 1_000_000;

    private final SmallProConsumptionPriceCalculator smallProConsumptionPriceCalculator;
    private final LargeProConsumptionPriceCalculator largeProConsumptionPriceCalculator;

    @Inject
    public ProConsumptionPriceCalculator(
            SmallProConsumptionPriceCalculator smallProConsumptionPriceCalculator,
            LargeProConsumptionPriceCalculator largeProConsumptionPriceCalculator) {
        this.smallProConsumptionPriceCalculator = smallProConsumptionPriceCalculator;
        this.largeProConsumptionPriceCalculator = largeProConsumptionPriceCalculator;
    }

    @Override
    public BigDecimal calculatePrice(Client client, Consumption consumption) {
        var proClient = (ProfessionalClient) client;

        if (proClient.getAnnualRevenueInEuros() < discountThreshold) {
            return smallProConsumptionPriceCalculator.calculatePrice(client, consumption);
        } else {
            return largeProConsumptionPriceCalculator.calculatePrice(client, consumption);
        }
    }
}
