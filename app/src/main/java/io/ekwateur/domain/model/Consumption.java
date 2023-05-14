package io.ekwateur.domain.model;

public final class Consumption {
    private final double electricityKWh;
    private final double gazKwh;

    public Consumption(double electricityKWh, double gazKwh) {
        this.electricityKWh = electricityKWh;
        this.gazKwh = gazKwh;
    }

    public double getElectricityKWh() {
        return electricityKWh;
    }

    public double getGazKwh() {
        return gazKwh;
    }
}
