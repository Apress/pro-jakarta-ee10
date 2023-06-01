package book.jakartapro.batch;

import java.io.*;
import java.util.Properties;

import jakarta.batch.api.chunk.ItemReader;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Dependent
@Named("AttendanceReader")
public class AttendanceReader implements ItemReader {
  private Integer lineNumber = 0;
  private String fileName;
  private InputStream ins;
  private BufferedReader breader;

  @Inject
  private JobContext jobCtx;

  public AttendanceReader() {
  }

  @Override
  public void open(Serializable checkpoint) 
        throws Exception {
    Properties props = jobCtx.getProperties();
    fileName = props.getProperty("input_file");
    System.out.println("open file " + fileName);

    if (new File(fileName).exists()) {
      ins = new FileInputStream(fileName);
      breader = new BufferedReader(
            new InputStreamReader(ins));

      if (checkpoint != null) {
        lineNumber = (Integer) checkpoint;
        for (int i = 0; i < lineNumber; i++) {
          breader.readLine();
        }
      }
    }
  }

  @Override
  public void close() throws Exception {
    if(breader != null) breader.close();
    if(ins != null) ins.close();
  }

  @Override
  public Object readItem() throws Exception {
    Object result = null;
    if(breader == null) return result;

    String line = breader.readLine();
    if (line != null) {
      String[] fields = line.split("[, \t\r\n]+");
      if (fields.length > 0) {
        String ssn = fields[0].trim();
        long tm = Long.parseLong(fields[2]) - 
              Long.parseLong(fields[1]);
        AttendanceItem itemObj = 
              new AttendanceItem(ssn, (int) tm);
        result = itemObj;
      }
      lineNumber++;
    }
    return result;
  }

  @Override
  public Serializable checkpointInfo() throws Exception {
    return lineNumber;
  }
}
