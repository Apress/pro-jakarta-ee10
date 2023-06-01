		
SchemaFactory sf = SchemaFactory
      .newInstance("http://www.w3.org/2001/XMLSchema");
Schema schem = sf.newSchema(new File("NewXMLSchema2.xsd"));
...		
JAXBContext context = JAXBContext.
      newInstance(Catalog.class);
Unmarshaller um = context.createUnmarshaller();
um.setSchema(schem);
...
