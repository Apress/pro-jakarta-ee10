// ---------- File HelloWorldManagedConnectionMetaData.java
package book.jakartapro.jcahello;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ManagedConnectionMetaData;

public class HelloWorldManagedConnectionMetaData 
        implements ManagedConnectionMetaData {
    @Override
    public String getEISProductName() 
          throws ResourceException {
        return "HelloWorld Resource Adapter";
    }

    @Override
    public String getEISProductVersion() 
          throws ResourceException {
        return "1.0";
    }

    @Override
    public int getMaxConnections() 
          throws ResourceException {
        return 0;
    }

    @Override
    public String getUserName() 
          throws ResourceException {
        return null;
    }
}
