    public interface Audit {
        ...
    }

    public class BasicAudit implements Audit {
        ...
    }

    public class ComplexAudit implements Audit {
        ...
    }

    public class SomeBean {
        @Inject Audit audit;
    }
