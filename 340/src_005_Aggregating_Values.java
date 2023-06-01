public interface MonitoringBeanMXBean {
    double getElapse();
    void addElapse(double elapse);
}

public class MonitoringBean 
        implements MonitoringBeanMXBean {
    private static final int ELAPSE_LEN = 1000; 
    private double[] elapse = new double[ELAPSE_LEN];
    private int elapseInd = 0;
    
    @Override
    synchronized
    public double getElapse() {
        double x = 0.0; 
        for(int i = 0; i < ELAPSE_LEN; i++) x += elapse[i];
        return x / ELAPSE_LEN;
    }

    @Override
    synchronized
    public void addElapse(double elapse) {
        this.elapse[elapseInd++] = elapse;
        if(elapseInd >= ELAPSE_LEN) elapseInd = 0;
    }
}
