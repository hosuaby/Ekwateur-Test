package io.ekwateur;

import com.google.inject.Guice;
import io.ekwateur.infra.cli.CliModule;
import io.ekwateur.infra.cli.services.CliService;

public class App {
    public static void main(String[] args) {
        var injector = Guice.createInjector(new CliModule());
        var cliService = injector.getInstance(CliService.class);
        cliService.run();
    }
}
