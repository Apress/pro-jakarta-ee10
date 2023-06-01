List<Record> records = new ArrayList<>();

Record r1 = new Record();
r1.setId(173658);
r1.setTyp("cd");
r1.setMake(1733);
r1.setFileType("wav");
r1.setGenre("classics");
r1.setComposer("J.S. Bach");
r1.setTitle("Mass in B-flat Minor");
r1.setPerformer("H. Masters");

Record r2 = new Record();
r2.setId(1734536);
r2.setTyp("usb");
r2.setMake(2001);
r2.setFileType("mp3");
r2.setGenre("rock");
r2.setComposer("G. Jung");
r2.setTitle("Butterflies");
r2.setPerformer("B. Hoyden");

records.add(r1);
records.add(r2);
	        
Catalog cat = new Catalog();
cat.setRecords(records);

JAXBContext context = JAXBContext.newInstance(
      Catalog.class);
Marshaller m = context.createMarshaller();
m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, 
      Boolean.TRUE);

// Write to System.out
m.marshal(cat, System.out);

// Write to File
//m.marshal(cat, new File("catalog.xml"));
