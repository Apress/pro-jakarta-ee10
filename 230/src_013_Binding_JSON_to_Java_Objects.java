  String json = "[ ... ]";
  
  Jsonb jsonb = JsonbBuilder.create();

  @SuppressWarnings("unchecked")
  List<Member> members = jsonb.fromJson(json, List.class);

  // just to check, the other way round, getting the
  // JSON string from a Java object tree:
  jsonb = JsonbBuilder.create();
  String json2 = jsonb.toJson(members);
  // <- semantically the same as 'json' above.
