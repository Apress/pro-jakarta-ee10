@GET
@Produces("application/json")
public String stdDate() {
    return "{\"date\":\"" + 
          ZonedDateTime.now().toString() + "\"}"; 
}
