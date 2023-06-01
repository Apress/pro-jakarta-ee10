package book.jakartapro.batch;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import jakarta.batch.api.chunk.ItemWriter;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Dependent
@Named("AttendanceWriter")
public class AttendanceWriter implements ItemWriter {
  private String outputDir;
	 
  @Inject
  private JobContext jobCtx;

  @Override
  public void open(Serializable checkpoint) 
        throws Exception {
    Properties props = jobCtx.getProperties();
    outputDir = props.getProperty("output_folder") + 
          File.separator + jobCtx.getExecutionId();

    if(checkpoint == null) {
      // first start
      new File(outputDir).mkdirs();
    } else {
      // restart
    }
  }

  @Override
  public void close() throws Exception {
  }

  @Override
  public void writeItems(List<Object> items) 
        throws Exception {
    for(Object o : items) {
      AttendanceItem itm = (AttendanceItem) o;
      String ssn = itm.getSsn().trim();
      long millis = itm.getWorkMillis();
			
      File f = new File(outputDir + File.separator + ssn);
      long tm = 0L;
      if(f.exists()) {
        // The following line is just a simple trick
        // to read in the contents of a file.
        try(Scanner s = new Scanner(f).
            useDelimiter("\\Z")) {
          tm = Long.parseLong(s.next().trim());
        }
      }
      tm += millis;
			
      f.delete();
      FileWriter fw = new FileWriter(f);
      fw.append(tm + "\n");
      fw.flush();
      fw.close();
    }
  }

  @Override
  public Serializable checkpointInfo() throws Exception {
    return null;
  }
}
