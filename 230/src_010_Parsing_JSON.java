...
import jakarta.json.*;
...
    final String json = 
      "{\"name\":\"John\",\"age\":27,\"employed\":false}";
    final JsonParser parser = 
      Json.createParser(new StringReader(json));
    String key = null;
    String value = null;
    while (parser.hasNext()) {
        final Event event = parser.next();
        switch (event) {
        case KEY_NAME:
            key = parser.getString();
            System.out.println(key);
            break;
        case VALUE_STRING:
            value = parser.getString();
            System.out.println(value);
            break;
        }
    }
    parser.close();
