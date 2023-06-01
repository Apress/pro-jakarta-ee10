package book.jakartapro.batch;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Dependent
@Named("AttendanceProcessor")
public class AttendanceProcessor implements ItemProcessor {

  @Override
  public Object processItem(Object item) throws Exception {
    // Use this to convert an object, or return null to
    // exclude objects
    AttendanceItem itm = (AttendanceItem) item;
    if(itm.getSsn() == null || 
       itm.getSsn().trim().isEmpty()) return null;
    return item;
  }
}
