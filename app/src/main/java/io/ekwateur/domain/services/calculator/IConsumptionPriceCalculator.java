package io.ekwateur.domain.services.calculator;

import io.ekwateur.domain.model.Client;
import io.ekwateur.domain.model.Consumption;

import java.math.BigDecimal;

public interface IConsumptionPriceCalculator {
    BigDecimal calculatePrice(Client client, Consumption consumption);
}
