String dd = new String(Base64.getDecoder().
      decode(token.split("\\.")[1]));
JsonReader reader = Json.createReader(
      new StringReader(dd));
JsonObject jsonObject = reader.readObject();
String user = jsonObject.getString("sub");
