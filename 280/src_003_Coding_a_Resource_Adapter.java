// ---------- File HelloWorldManagedConnection.java
package book.jakartapro.jcahello;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.resource.NotSupportedException;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionEvent;
import jakarta.resource.spi.ConnectionEventListener;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.LocalTransaction;
import jakarta.resource.spi.ManagedConnection;
import jakarta.resource.spi.ManagedConnectionMetaData;

import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

import book.jakartapro.jcahello.interfaces.
    HelloWorldConnection;

public class HelloWorldManagedConnection 
        implements ManagedConnection {
    private HelloWorldManagedConnectionFactory mcf;
    private List<ConnectionEventListener> listeners;
    private Object connection;
    private PrintWriter logWriter;

    public HelloWorldManagedConnection(
          HelloWorldManagedConnectionFactory mcf) {
        this.mcf = mcf;
        this.logWriter = null;
        this.listeners = 
            new ArrayList<ConnectionEventListener>(1);
        this.connection = null;
    }

    /**
     * Creates a new connection handle for the underlying 
     * physical connection represented by the 
     * ManagedConnection instance.
     */
    public Object getConnection(
          Subject subject, 
          ConnectionRequestInfo cxRequestInfo) 
          throws ResourceException {
        connection = new HelloWorldConnectionImpl(this, mcf);
        return connection;
    }

    /**
     * Used by the container to change the association of 
     * an application-level connection handle with a 
     * ManagedConneciton instance.
     */
    public void associateConnection(Object connection) 
          throws ResourceException {
        this.connection = connection;
    }

    /**
     * Application server calls this method to force any 
     * cleanup on the ManagedConnection instance.
     */
    public void cleanup() throws ResourceException {
    }

    /**
     * Destroys the physical connection to the underlying
     * resource manager.
     */
    public void destroy() throws ResourceException {
        this.connection = null;
    }

    public void addConnectionEventListener(
          ConnectionEventListener listener) {
        listeners.add(listener);
    }

    public void removeConnectionEventListener(
          ConnectionEventListener listener) {
        listeners.remove(listener);
    }

    public PrintWriter getLogWriter() 
          throws ResourceException {
        return logWriter;
    }

    public void setLogWriter(PrintWriter out) 
          throws ResourceException {
        this.logWriter = out;
    }

    public LocalTransaction getLocalTransaction() 
          throws ResourceException {
        throw new NotSupportedException(
            "LocalTransaction not supported");
    }

    public XAResource getXAResource() 
          throws ResourceException {
        throw new NotSupportedException(
            "GetXAResource not supported");
    }

    public ManagedConnectionMetaData getMetaData() 
          throws ResourceException {
        return new HelloWorldManagedConnectionMetaData();
    }

    String helloWorld(String name) {
        return "Hello World, " + name + " !";
    }

    void closeHandle(HelloWorldConnection handle) {
        ConnectionEvent event = 
            new ConnectionEvent(this, 
                ConnectionEvent.CONNECTION_CLOSED);
        event.setConnectionHandle(handle);

        for (ConnectionEventListener cel : listeners) {
            cel.connectionClosed(event);
        }
    }
}


// ---------- File HelloWorldManagedConnectionFactory.java
package book.jakartapro.jcahello;

import java.util.Objects;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionDefinition;
import jakarta.resource.spi.ConnectionManager;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.ManagedConnection;
import jakarta.resource.spi.ManagedConnectionFactory;
import jakarta.resource.spi.ResourceAdapter;
import jakarta.resource.spi.ResourceAdapterAssociation;

import javax.security.auth.Subject;

import book.jakartapro.jcahello.interfaces.
    HelloWorldConnection;
import book.jakartapro.jcahello.interfaces.
    HelloWorldConnectionFactory;

@ConnectionDefinition(
  connectionFactory = 
    HelloWorldConnectionFactory.class, 
  connectionFactoryImpl = 
    HelloWorldConnectionFactoryImpl.class, 
  connection = 
     HelloWorldConnection.class, 
  connectionImpl = 
    HelloWorldConnectionImpl.class)
public class HelloWorldManagedConnectionFactory 
        implements ManagedConnectionFactory, 
                   ResourceAdapterAssociation {
    private ResourceAdapter ra;
    private PrintWriter logwriter;

    public HelloWorldManagedConnectionFactory() {
        this.ra = null;
        this.logwriter = null;
    }

    public Object createConnectionFactory() 
          throws ResourceException {
        throw new ResourceException(
            "This resource adapter doesn't " + 
            "support non-managed environments");
    }

    public Object createConnectionFactory(
          ConnectionManager cxManager) 
          throws ResourceException {
        return new HelloWorldConnectionFactoryImpl(this, 
            cxManager);
    }

    public ManagedConnection createManagedConnection(
          Subject subject, 
          ConnectionRequestInfo cxRequestInfo)
          throws ResourceException {
        return new HelloWorldManagedConnection(this);
    }

    public ManagedConnection matchManagedConnections(
          Set connectionSet, Subject subject,
          ConnectionRequestInfo cxRequestInfo) 
          throws ResourceException {
        ManagedConnection result = null;
        Iterator it = connectionSet.iterator();
        while (result == null && it.hasNext()) {
            ManagedConnection mc = 
                (ManagedConnection) it.next();
            if (mc instanceof 
                  HelloWorldManagedConnection) {
                HelloWorldManagedConnection hwmc = 
                   (HelloWorldManagedConnection) mc;
                result = hwmc;
            }
        }
        return result;
    }

    public PrintWriter getLogWriter() 
          throws ResourceException {
        return logwriter;
    }

    public void setLogWriter(PrintWriter out) 
          throws ResourceException {
        logwriter = out;
    }

    public ResourceAdapter getResourceAdapter() {
        return ra;
    }

    public void setResourceAdapter(ResourceAdapter ra) {
        this.ra = ra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(17);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof 
               HelloWorldManagedConnectionFactory))
           return false;
        return true;
    }
}
