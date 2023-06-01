  ... [same as above]
  records.add(r1);
  records.add(r2);
	   
  SchemaFactory sf = SchemaFactory.newInstance(
        "http://www.w3.org/2001/XMLSchema");
  Schema schem = sf.newSchema(new File("Catalog.xsd"));
	        
  Catalog cat = new Catalog();
  cat.setRecords(records);

  JAXBContext context = 
      JAXBContext.newInstance(Catalog.class);
  Marshaller m = context.createMarshaller();
  m.setSchema(schem);  // <==== NEW! ====
  ... [same as above]
