  final String json = 
      "{\"name\":\"John\",\"age\":27,\"employed\":false}";

  JsonReader jsonReader = 
      Json.createReader(new StringReader(json));

  JsonObject object = jsonReader.readObject();
  jsonReader.close();
  ...
  String name = object.getString("name", "<unknown>");
