m.setProperty(
    Marshaller.JAXB_SCHEMA_LOCATION, 
    "http://some.server/catalog.xsd");
// or, if the schema does not specify a namespace
m.setProperty(
    Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, 
    "http://some.server/catalog.xsd");
