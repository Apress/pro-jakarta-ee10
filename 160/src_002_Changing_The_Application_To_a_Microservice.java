@Path("/d")
public class RestDate {
  ...
  @GET
  @Produces("application/json")
  
  // OpenAPI
  @Operation(summary = "Outputs the date",
      description = "This method outputs the date")
  
  public String stdDate() {
    return "{\"date\":\"" + 
        ZonedDateTime.now().toString() + "\"}"; 
  }
  ...
}
