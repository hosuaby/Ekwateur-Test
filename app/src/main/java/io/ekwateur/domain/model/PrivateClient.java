package io.ekwateur.domain.model;

public final class PrivateClient extends Client {
    private final String civility;
    private final String firstName;
    private final String lastName;

    public PrivateClient(ClientRef clientRef, String civility, String firstName, String lastName) {
        super(clientRef);
        this.civility = civility;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getCivility() {
        return civility;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
