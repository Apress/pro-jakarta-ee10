package book.jakartapro.batch;

import java.io.Serializable;

public class AttendanceItem implements Serializable {
  private static final long serialVersionUID = 
      2413741734728319063L;
  private String ssn; // + getters/setters
  private int workMillis; // + getters/setters
	 
  public AttendanceItem(String ssn, int workMillis) {
    this.ssn = ssn;
    this.workMillis = workMillis;
  }
}
