package org.jboss.tool;

import java.io.IOException;
import org.jboss.as.controller.client.ModelControllerClient;

public class Server {

    private final Configuration configuration;

    public Server(final Configuration configuration) {
        this.configuration = configuration;
    }

    public ModelControllerClient connect() throws IOException {
        return ModelControllerClient.Factory.create(configuration.protocol, configuration.host, configuration.port);
    }
}
