package io.ekwateur.domain.services.calculator;

import io.ekwateur.domain.model.Client;
import io.ekwateur.domain.model.Consumption;

import java.math.BigDecimal;

public abstract class AbstractConsumptionPriceCalculator implements IConsumptionPriceCalculator {
    protected abstract BigDecimal electricityKwhPrice();
    protected abstract BigDecimal gazKwhPrice();

    @Override
    public BigDecimal calculatePrice(Client client, Consumption consumption) {
        var electricityPrice = electricityKwhPrice().multiply(BigDecimal.valueOf(consumption.getElectricityKWh()));
        var gazPrice = gazKwhPrice().multiply(BigDecimal.valueOf(consumption.getGazKwh()));
        return electricityPrice.add(gazPrice);
    }
}
