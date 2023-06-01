    public interface Audit {
        ...
    }

    // ------------------------------------------------

    @Priority(100) @Alternative
    public class BasicAudit implements Audit {
        ...
    }

    // ------------------------------------------------

    @Priority(101) @Alternative
    public class ComplexAudit implements Audit {
        ...
    }

    // ------------------------------------------------

    public class SomeBean {
        @Inject Audit audit;
    }
