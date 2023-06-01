package book.jakartapro.batch;

import java.io.File;
import java.util.Properties;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Dependent
@Named("Cleanup")
public class Cleanup extends AbstractBatchlet {

  @Inject
  private JobContext jobCtx;

  @Override
  public String process() throws Exception {
    backupInput();
    removeEmptyOutputDirs();

    return "COMPLETED"; // "FAILED"
  }

  private void removeEmptyOutputDirs() {
    Properties props = jobCtx.getProperties();
    String outputDir = props.getProperty("output_folder") +
          File.separator + jobCtx.getExecutionId();
    if(new File(outputDir).list().length == 0)
      new File(outputDir).delete();
  }

  private void backupInput() {
    Properties props = jobCtx.getProperties();
    String fileName = props.getProperty("input_file");
		
    String bakName = fileName + ".bak" + 
          System.currentTimeMillis();
    new File(fileName).renameTo(new File(bakName));
  }
}
