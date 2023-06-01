package book.jakartapro.jmxmonitoring;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * REST Web Service
 */
@Path("/pi")
public class Jmx {
    @Inject MonitoringBean jmxBean; 
    
    @Produces("application/json")
    public String pi() {
        long t1 = System.currentTimeMillis();
        double pi = calcPi(100_000_001);
        long ela = System.currentTimeMillis() - t1;
        jmxBean.setElapse(ela);
        return "{\"pi\":\"" + pi + "\"}";
    }

    private double calcPi(int n) {
        // This is a stupid way of calculating PI. 
        // It is just an example of a time-consuming
        // operation.
        double pi = 0.0;
        for (int i = 1; i < n; i++) {
            if (i % 2 == 1) {
                pi = pi + (4/((i*2.0)-1));
            } else {
                pi = pi - (4/((i*2.0)-1));
            }
        }
        return pi;
    }
}
