// ---------- File HelloWorldConnection.java
package book.jakartapro.jcahello.interfaces;

public interface HelloWorldConnection {
    public String helloWorld();
    public String helloWorld(String name);
    public void close();
}


// ---------- File HelloWorldConnection.java
package book.jakartapro.jcahello.interfaces;

import java.io.Serializable;

import jakarta.resource.Referenceable;
import jakarta.resource.ResourceException;

public interface HelloWorldConnectionFactory extends 
      Serializable, Referenceable {
    public HelloWorldConnection getConnection() 
      throws ResourceException;
}
