// ---------- File HelloWorldResourceAdapter.java
package book.jakartapro.jcahello;

import java.util.logging.Logger;
import java.util.Objects;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ActivationSpec;
import jakarta.resource.spi.BootstrapContext;
import jakarta.resource.spi.ConfigProperty;
import jakarta.resource.spi.Connector;
import jakarta.resource.spi.ResourceAdapter;
import jakarta.resource.spi.
    ResourceAdapterInternalException;
import jakarta.resource.spi.TransactionSupport;
import jakarta.resource.spi.endpoint.
    MessageEndpointFactory;

import javax.transaction.xa.XAResource;

@Connector(
    reauthenticationSupport = false, 
    transactionSupport = 
    TransactionSupport.TransactionSupportLevel.
        NoTransaction)
public class HelloWorldResourceAdapter 
        implements ResourceAdapter {
    /** Name property */
    @ConfigProperty(defaultValue = "Some Name", 
        supportsDynamicUpdates = true)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * This is called during the activation of a message
     * endpoint.
     */
    public void endpointActivation(
          MessageEndpointFactory endpointFactory, 
          ActivationSpec spec)
          throws ResourceException {
    }

    /**
     * This is called when a message endpoint is 
     * deactivated.
     */
    public void endpointDeactivation(
          MessageEndpointFactory endpointFactory, 
          ActivationSpec spec) {
    }

    /**
     * This is called when a resource adapter instance 
     * is bootstrapped.
     */
    public void start(BootstrapContext ctx) 
          throws ResourceAdapterInternalException {
    }

    /**
     * This is called when a resource adapter instance 
     * is undeployed or during application server shutdown.
     */
    public void stop() {
    }

    /**
     * This method is called by the application server 
     * during crash recovery.
     */
    public XAResource[] getXAResources(
          ActivationSpec[] specs) 
          throws ResourceException {
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object other) {
        return Objects.equal(this, other);
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof 
              HelloWorldResourceAdapter))
            return false;
        HelloWorldResourceAdapter obj = 
            (HelloWorldResourceAdapter) other;
        return Objects.equals(name, other.getName);
    }
}
