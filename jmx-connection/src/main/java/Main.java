import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("JMX connection to WildFly ...");

        String host = "localhost";
        int port = 9990;
        String urlString = System.getProperty("jmx.service.url","service:jmx:remote+http://" + host + ":" + port);
        JMXServiceURL wildflyJmxUrl = new JMXServiceURL(urlString);
        try (JMXConnector connector = JMXConnectorFactory.connect(wildflyJmxUrl, null)) {
            MBeanServerConnection connection = connector.getMBeanServerConnection();
            System.out.println(connection.getMBeanCount());
        }
    }
}
