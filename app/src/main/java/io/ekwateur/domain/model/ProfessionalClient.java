package io.ekwateur.domain.model;

public final class ProfessionalClient extends Client {
    private final Siret siret;
    private final String companyName;
    private final int annualRevenueInEuros;

    public ProfessionalClient(ClientRef clientRef, Siret siret, String companyName, int annualRevenueInEuros) {
        super(clientRef);
        this.siret = siret;
        this.companyName = companyName;
        this.annualRevenueInEuros = annualRevenueInEuros;
    }

    public Siret getSiret() {
        return siret;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getAnnualRevenueInEuros() {
        return annualRevenueInEuros;
    }
}
