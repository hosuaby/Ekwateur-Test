package io.ekwateur.domain.services.calculator;

import com.google.inject.Inject;
import io.ekwateur.domain.model.Client;
import io.ekwateur.domain.model.Consumption;
import io.ekwateur.domain.model.PrivateClient;

import java.math.BigDecimal;

public final class ConsumptionPriceCalculator implements IConsumptionPriceCalculator {
    private final PrivateConsumptionPriceCalculator privateConsumptionPriceCalculator;
    private final ProConsumptionPriceCalculator proConsumptionPriceCalculator;

    @Inject
    public ConsumptionPriceCalculator(
            PrivateConsumptionPriceCalculator privateConsumptionPriceCalculator,
            ProConsumptionPriceCalculator proConsumptionPriceCalculator) {
        this.privateConsumptionPriceCalculator = privateConsumptionPriceCalculator;
        this.proConsumptionPriceCalculator = proConsumptionPriceCalculator;
    }

    @Override
    public BigDecimal calculatePrice(Client client, Consumption consumption) {
        if (client instanceof PrivateClient) {
            return privateConsumptionPriceCalculator.calculatePrice(client, consumption);
        } else {
            return proConsumptionPriceCalculator.calculatePrice(client, consumption);
        }
    }
}
