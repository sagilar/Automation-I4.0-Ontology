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
public class SendMessageMethod {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @UaMethod
    public void invoke(
        InvocationContext context,

        @UaInputArgument(
            name = "d",
            description = "A data variable (string).")
            String d,
        
        @UaOutputArgument(
            name = "send_data",
            description = "Returns a data content")
            Out<String> data) {

        logger.debug("Invoking sendMessage() method of Object '{}'", context.getObjectNode().getBrowseName().getName());

        data.set(d);
    }
    
}
