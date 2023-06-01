import org.eclipse.microprofile.health.*;

@Readiness
public class RestDateHealth implements HealthCheck {
  @Override
  public HealthCheckResponse call() {
    HealthCheckResponseBuilder bldr = HealthCheckResponse.
          builder().name("RestDateHealth");
    try {
      String dateStr = new RestDate().stdDate();
      String dayRegex = "\\d{4}-\\d{2}-\\d{2}";
      String tmRegex = "\\d{2}:\\d{2}:\\d{2}\\..*";
      boolean fmtOk = dateStr.matches("\\{\"date\":\"" +
          dayRegex + "T" + tmRegex + "\\}"); 
      if (fmtOk) {
        return bldr.up().build();
      } else {
        return bldr.down().build();
      }
    } catch (Exception e) {
      return bldr.down().withData(
          "Exception", e.getMessage()).build();
    }
  }
}
