package book.jakartapro.xmlbinding;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
    @XmlAttribute private long id;
    @XmlAttribute(name = "type") private String typ;
    @XmlAttribute private int make;
    private String fileType;
    private String genre;
    private String composer;
    private String title;
    private String performer;

    // Getters and setters...
}
