String xml = """
  <?xml version=\"1.0\" encoding=\"UTF-8\" 
        standalone=\"yes\"?> 
  <ns2:catalog 
    xmlns:ns2=\"http://book.jakartapro.catalog\"
    xmlns:xsi=" +
          \"http://www.w3.org/2001/XMLSchema-instance\" 
    xsi:schemaLocation= 
          \"http://book.jakartapro.catalog\"> 
      <recordList> 
          <record id=\"173658\" type=\"cd\" 
                  make=\"1733\"> 
              <fileType>wav</fileType> 
              <genre>classics</genre> 
              <composer>J.S. Bach</composer> 
              <title>Mass in B-flat Minor</title> 
              <performer>H. Masters</performer> 
          </record> 
          <record id=\"1734536\" type=\"usb\" 
                  make=\"2001\"> 
              <fileType>mp3</fileType> 
              <genre>rock</genre> 
              <composer>G. Jung</composer> 
              <title>Butterflies</title> 
              <performer>B. Hoyden</performer> 
          </record> 
      </recordList> 
  </ns2:catalog> 
  """;
				
JAXBContext context = JAXBContext.
      newInstance(Catalog.class);
Unmarshaller um = context.createUnmarshaller();

ByteArrayInputStream bis = 
      new ByteArrayInputStream(xml.getBytes());
Object o = um.unmarshal(bis);
System.out.println(o.getClass());
Catalog c = (Catalog) o;
System.out.println(c.getRecords().size());
System.out.println(c.getRecords().get(0).getComposer());	
