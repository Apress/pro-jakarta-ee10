public interface MonitoringBeanMXBean {
    double getElapse();
    void addElapse(double elapse);
}

public class MonitoringBean 
        implements MonitoringBeanMXBean {
    private static final double ELAPSE_EMA_PARAM = 0.95; 
    private double elapseEMA = 0.0;
    
    @Override
    synchronized
    public double getElapse() {
        return elapseEMA;
    }

    @Override
    synchronized
    public void addElapse(double elapse) {
        this.elapseEMA = 
            ELAPSE_EMA_PARAM * this.elapseEMA +
            (1.0-ELAPSE_EMA_PARAM) * elapse;
    }
}
