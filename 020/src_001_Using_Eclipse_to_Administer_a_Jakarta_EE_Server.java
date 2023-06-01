package book.jakartapro.restdate;

import java.time.ZonedDateTime;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

**
* REST Web Service
*/
@Path("/d")
public class RestDate {
  @GET
  @Produces("application/json")
  public String stdDate() {
    return "{\"date\":\"" + 
        ZonedDateTime.now().toString() + 
        "\"}"; 
  }
}
