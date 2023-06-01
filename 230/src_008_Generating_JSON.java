...
import jakarta.json.*;
...
    JsonArrayBuilder builder = Json.createArrayBuilder();
    builder.add("Fibonacci");
    builder.add(1); // it is allowed to mix types in arrays
    builder.add(2);
    builder.add(3);
    builder.add(5);
    builder.add(8);
