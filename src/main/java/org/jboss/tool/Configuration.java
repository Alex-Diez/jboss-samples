package org.jboss.tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public final String protocol;
    public final String host;
    public final int port;

    private Configuration(final String protocol, final String host, final int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }

    public static class Builder {

        private static final String CONF_FILE = "conf/connection.properties";
        private static final String SERVER_PORT_PROPERTY = "server.port";
        private static final String SERVER_HOST_PROPERTY = "server.host";
        private static final String CONNECTION_PROTOCOL_PROPERTY = "connection.protocol";

        private String host;
        private String protocol;
        private int port;

        public Builder readProperties() throws IOException {
            Properties properties = loadProperties();
            setupRemoteJBossConnectionProtocol(properties);
            setupRemoteJBossHostName(properties);
            setupRemoteJBossPort(properties);
            return this;
        }

        private void setupRemoteJBossPort(Properties properties) {
            port = Integer.parseInt(properties.getProperty(SERVER_PORT_PROPERTY));
        }

        private void setupRemoteJBossConnectionProtocol(Properties properties) {
            protocol = properties.getProperty(CONNECTION_PROTOCOL_PROPERTY);
        }

        private void setupRemoteJBossHostName(Properties properties) {
            host = properties.getProperty(SERVER_HOST_PROPERTY);
        }

        private Properties loadProperties() throws IOException {
            Properties properties = new Properties();
            properties.load(new FileInputStream(CONF_FILE));
            return properties;
        }

        public Configuration build() {
            return new Configuration(protocol, host, port);
        }
    }
}
