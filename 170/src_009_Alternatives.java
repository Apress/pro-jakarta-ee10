    public interface Audit {
        ...
    }

    // ------------------------------------------------

    @Default
    public class BasicAudit implements Audit {
        ...
    }

    // ------------------------------------------------

    @Alternative
    public class ComplexAudit implements Audit {
        ...
    }

    // ------------------------------------------------

    public class SomeBean {
        @Inject Audit audit;
    }
