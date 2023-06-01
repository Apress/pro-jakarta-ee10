package book.jakartapro.xmlbinding;

import java.util.List;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(namespace = "http://book.jakartapro.catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {
  @XmlElementWrapper(name = "recordList")
  @XmlElement(name = "record")
  private List<Record> records;

  // Getters and setters...
}
