package book.jakartapro.jmxmonitoring;

public interface MonitoringBeanMXBean {
    double getElapse();
    void setElapse(double elapse);
}


package book.jakartapro.jmxmonitoring;

public class MonitoringBean 
      implements MonitoringBeanMXBean {
    private double elapse;
    
    public double getElapse() {
        return elapse;
    }

    public void setElapse(double elapse) {
        this.elapse = elapse;
    }
}
