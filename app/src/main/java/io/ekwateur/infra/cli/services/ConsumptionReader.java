package io.ekwateur.infra.cli.services;

import com.google.inject.Inject;
import io.ekwateur.domain.model.Consumption;
import org.beryx.textio.TextIO;

public class ConsumptionReader {
    private final TextIO textIO;

    @Inject
    public ConsumptionReader(TextIO textIO) {
        this.textIO = textIO;
    }

    public Consumption readConsumption() {
        var electricityKWh = textIO
                .newDoubleInputReader()
                .read("How much client consumed of electricity (kWh)");
        var gazKwh = textIO
                .newDoubleInputReader()
                .read("How much client consumed of gaz (kWh)");
        return new Consumption(electricityKWh, gazKwh);
    }
}
