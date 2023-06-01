package book.jakartapro.jmxmonitoring;

import java.lang.management.ManagementFactory;
import javax.management.ObjectName;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitoringBean 
      implements MonitoringBeanMXBean {
    private static final String OBJECT_NAME = 
          "book.jakartapro.mxbeans:type=MonitoringBean";
    private double elapse;
    
    public double getElapse() {
        return elapse;
    }

    public void setElapse(double elapse) {
        this.elapse = elapse;
    }

    @PostConstruct
    public void start() {
        try {
            ManagementFactory.getPlatformMBeanServer().
                registerMBean(this, 
                    new ObjectName(OBJECT_NAME));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
