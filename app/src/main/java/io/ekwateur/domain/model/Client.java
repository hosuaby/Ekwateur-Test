package io.ekwateur.domain.model;

public abstract class Client {
    protected final ClientRef clientRef;

    public Client(ClientRef clientRef) {
        this.clientRef = clientRef;
    }

    public ClientRef getClientRef() {
        return clientRef;
    }
}
