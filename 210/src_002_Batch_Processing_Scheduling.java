package book.jakartapro.batch;

import java.util.Properties;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;

@Stateless
public class BatchEjb {
  @Schedule(
      // for development and testing, we start the 
      // job each 10 seconds
      hour = "*", 
      minute = "*", 
      second = "*/10", 
      persistent = false)
  public void timeout() {
    try {
      JobOperator jobOperator = 
            BatchRuntime.getJobOperator();

      // Just in case we want to programmatically add
      // a property
      Properties props = new Properties();
      props.setProperty("parameter1", "value1");

      // The "job1" corresponds to the name of 
      // the job definition file (without the .xml)
      // inside META-INF/batch-jobs
      long execID = jobOperator.start("job1", props);
    } catch (Exception e) {
      e.printStackTrace(System.err);
      ...
    }
  }
}
