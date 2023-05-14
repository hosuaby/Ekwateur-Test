package io.ekwateur.domain.services.calculator;

import java.math.BigDecimal;

public final class LargeProConsumptionPriceCalculator extends AbstractConsumptionPriceCalculator {
    private static final BigDecimal ELECTRICITY_KWH_PRICE = new BigDecimal("0.114");
    private static final BigDecimal GAZ_KWH_PRICE = new BigDecimal("0.111");

    @Override
    protected BigDecimal electricityKwhPrice() {
        return ELECTRICITY_KWH_PRICE;
    }

    @Override
    protected BigDecimal gazKwhPrice() {
        return GAZ_KWH_PRICE;
    }
}
