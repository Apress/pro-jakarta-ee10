...
import jakarta.json.Json;
import jakarta.json.JsonStructure;
...
@GET
@Path("j")
@Produces("application/json")
public JsonStructure stdDate() {
    JsonStructure json = Json.createObjectBuilder()
        .add("date", ZonedDateTime.now().toString())
    .build();
    return json; 
}
