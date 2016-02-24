package org.jboss.tool;

import java.io.IOException;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;


import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.NAME;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.READ_ATTRIBUTE_OPERATION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.RESULT;

public class Main {

    public static void main(String[] args) throws IOException {
        nativeConnection();
        jmxConnection();
    }

    public static void jmxConnection() throws IOException {
        System.out.println("JMX connection to WildFly ...");

        String host = "localhost";
        int port = 9990;
        String urlString = System.getProperty("jmx.service.url","service:jmx:remote+http://" + host + ":" + port);
        JMXServiceURL serviceURL = new JMXServiceURL(urlString);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
        MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();

        int count = connection.getMBeanCount();
        System.out.println(count);
        jmxConnector.close();
    }

    public static void nativeConnection() throws IOException {
        System.out.println("Native connection ...");

        try (ModelControllerClient client = makeConnection()) {
            ModelNode request = new ModelNode();
            request.get(OP).set(READ_ATTRIBUTE_OPERATION);
            request.get(NAME).set("server-state");
            ModelNode response = client.execute(request);

            System.out.println(response.get(RESULT));
        }
    }

    private static ModelControllerClient makeConnection() throws IOException {
        return new Server(new Configuration.Builder().readProperties().build()).createNativeConnection();
    }
}
