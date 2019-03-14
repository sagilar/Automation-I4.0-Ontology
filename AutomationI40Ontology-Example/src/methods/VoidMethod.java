/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import org.eclipse.milo.opcua.sdk.server.annotations.UaInputArgument;
import org.eclipse.milo.opcua.sdk.server.annotations.UaMethod;
import org.eclipse.milo.opcua.sdk.server.annotations.UaOutputArgument;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler.InvocationContext;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler.Out;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Santiago
 */
public class VoidMethod {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @UaMethod
    public void invoke(
        InvocationContext context,
           
        @UaInputArgument(
            name = "anyData",
            description = "A data variable (for testing).")
            String d,
        
        @UaOutputArgument(
            name = "any_data",
            description = "Returns a any value (for testing)")
            Out<String> data) {

        logger.debug("Invoking voidMethod() method of Object '{}'", context.getObjectNode().getBrowseName().getName());

        data.set(d);
    }
}
