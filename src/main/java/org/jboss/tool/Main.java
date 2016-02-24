package org.jboss.tool;

import java.io.IOException;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;


import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.NAME;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.READ_ATTRIBUTE_OPERATION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.RESULT;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Start tooling ...");
        ModelControllerClient client = ModelControllerClient.Factory.create("remote", "localhost", 9995);
        ModelNode request = new ModelNode();
        request.get(OP).set(READ_ATTRIBUTE_OPERATION);
        request.get(NAME).set("server-state");
        ModelNode response = client.execute(request);

        System.out.println(response.get(RESULT));
    }
}
