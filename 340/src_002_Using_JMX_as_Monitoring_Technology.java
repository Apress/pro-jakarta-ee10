import java.lang.management.ManagementFactory;
import javax.management.ObjectName;
...
    final String OBJECT_NAME = 
        "book.jakartapro.mxbeans:type=MonitoringBean";
...
    MonitoringBean m = ...;
...
    try {
        ManagementFactory.getPlatformMBeanServer().
            registerMBean(m, 
                new ObjectName(OBJECT_NAME));
    } catch (Exception e) {
        e.printStackTrace(System.err);
    }
