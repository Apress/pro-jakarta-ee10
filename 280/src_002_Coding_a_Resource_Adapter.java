// ---------- File HelloWorldConnectionImpl.java
package book.jakartapro.jcahello;

import java.util.logging.Logger;
import book.jakartapro.jcahello.interfaces.
    HelloWorldConnection;

public class HelloWorldConnectionImpl 
          implements HelloWorldConnection {
    private HelloWorldManagedConnection mc;
    private HelloWorldManagedConnectionFactory mcf;

    public HelloWorldConnectionImpl(
          HelloWorldManagedConnection mc, 
          HelloWorldManagedConnectionFactory mcf) {
        this.mc = mc;
        this.mcf = mcf;
    }

    public String helloWorld() {
        return helloWorld(
            ((HelloWorldResourceAdapter) mcf.
            getResourceAdapter()).getName());
    }

    public String helloWorld(String name) {
        return mc.helloWorld(name);
    }

    public void close() {
        mc.closeHandle(this);
    }
}


// ---------- File HelloWorldConnectionFactoryImpl.java
package book.jakartapro.jcahello;

import javax.naming.NamingException;
import javax.naming.Reference;

import book.jakartapro.jcahello.interfaces.
    HelloWorldConnection;
import book.jakartapro.jcahello.interfaces.
    HelloWorldConnectionFactory;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionManager;

public class HelloWorldConnectionFactoryImpl 
        implements HelloWorldConnectionFactory {
    private Reference reference;
    private HelloWorldManagedConnectionFactory mcf;
    private ConnectionManager connectionManager;

    public HelloWorldConnectionFactoryImpl(
          HelloWorldManagedConnectionFactory mcf, 
          ConnectionManager cxManager) {
        this.mcf = mcf;
        this.connectionManager = cxManager;
    }

    @Override
    public HelloWorldConnection getConnection() 
          throws ResourceException {
        return (HelloWorldConnection) connectionManager.
            allocateConnection(mcf, null);
    }

    @Override
    public Reference getReference() 
          throws NamingException {
        return reference;
    }

    @Override
    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
