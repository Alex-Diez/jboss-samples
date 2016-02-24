package org.jboss.tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public final String host;
    public final String remoteConnectionProtocol;
    public final int remoteConnectionPort;
    public final String jmxConnectionProtocol;
    public final int jmxConnectionPort;

    private Configuration(
            final String host,
            final String remoteConnectionProtocol,
            final int remoteConnectionPort,
            final String jmxConnectionProtocol,
            final int jmxConnectionPort) {
        this.host = host;
        this.remoteConnectionProtocol = remoteConnectionProtocol;
        this.remoteConnectionPort = remoteConnectionPort;
        this.jmxConnectionProtocol = jmxConnectionProtocol;
        this.jmxConnectionPort = jmxConnectionPort;
    }

    public static class Builder {

        private static final String CONF_FILE = "conf/connection.properties";
        private static final String SERVER_HOST_PROPERTY = "server.host";
        private static final String NATIVE_CONNECTION_PROTOCOL_PROPERTY = "native.connection.protocol";
        private static final String NATIVE_CONNECTION_PORT_PROPERTY = "native.connection.port";
        private static final String JMX_CONNECTION_PROTOCOL_PROPERTY = "jmx.connection.protocol";
        private static final String JMX_CONNECTION_PORT_PROPERTY = "jmx.connection.port";

        private String host;
        private String remoteConnectionProtocol;
        private int remoteConnectionPort;
        private String jmxConnectionProtocol;
        private int jmxConnectionPort;

        public Builder readProperties() throws IOException {
            Properties properties = loadProperties();
            setupRemoteHostName(properties);
            setupNativeConnectionProtocol(properties);
            setupNativeConnectionPort(properties);
            setupJMXConnectionProtocol(properties);
            setupJMXConnectionPort(properties);
            return this;
        }

        private Properties loadProperties() throws IOException {
            Properties properties = new Properties();
            properties.load(new FileInputStream(CONF_FILE));
            return properties;
        }

        private void setupRemoteHostName(Properties properties) {
            host = properties.getProperty(SERVER_HOST_PROPERTY);
        }

        private void setupJMXConnectionPort(Properties properties) {
            jmxConnectionPort = Integer.parseInt(properties.getProperty(JMX_CONNECTION_PORT_PROPERTY));
        }

        private void setupJMXConnectionProtocol(Properties properties) {
            jmxConnectionProtocol = properties.getProperty(JMX_CONNECTION_PROTOCOL_PROPERTY);
        }

        private void setupNativeConnectionProtocol(Properties properties) {
            remoteConnectionProtocol = properties.getProperty(NATIVE_CONNECTION_PROTOCOL_PROPERTY);
        }

        private void setupNativeConnectionPort(Properties properties) {
            remoteConnectionPort = Integer.parseInt(properties.getProperty(NATIVE_CONNECTION_PORT_PROPERTY));
        }

        public Configuration build() {
            return new Configuration(host, remoteConnectionProtocol, remoteConnectionPort, jmxConnectionProtocol, jmxConnectionPort);
        }
    }
}
