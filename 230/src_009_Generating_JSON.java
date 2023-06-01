...
import jakarta.json.*;
...
    JsonObjectBuilder builder = Json.createObjectBuilder();
    // ... add elements, then:
    JsonObject obj = builder.build();
...
    JsonArrayBuilder abuilder = Json.createArrayBuilder();
    // ... add elements, then:
    JsonArray aobj = abuilder.build();
