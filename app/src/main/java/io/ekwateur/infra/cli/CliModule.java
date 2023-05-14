package io.ekwateur.infra.cli;

import com.google.inject.AbstractModule;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class CliModule extends AbstractModule {
    @Override
    protected void configure() {
        var textIO = TextIoFactory.getTextIO();

        bind(TextIO.class)
                .toInstance(textIO);
        bind(TextTerminal.class)
                .toInstance(textIO.getTextTerminal());
    }
}
