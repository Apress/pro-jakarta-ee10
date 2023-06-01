    public interface Audit {
        ...
    }

    // ------------------------------------------------

    import java.lang.annotation.Retention;
    import java.lang.annotation.Target;
    import static java.lang.annotation.RetentionPolicy.*;
    import static java.lang.annotation.ElementType.*;
    import jakarta.inject.Qualifier;

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    public @interface Basic {}

    // ------------------------------------------------

    import java.lang.annotation.Retention;
    import java.lang.annotation.Target;
    import static java.lang.annotation.RetentionPolicy.*;
    import static java.lang.annotation.ElementType.*;
    import jakarta.inject.Qualifier;

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    public @interface Complex {}

    // ------------------------------------------------

    @Basic
    public class BasicAudit implements Audit {
        ...
    }

    // ------------------------------------------------

    @Complex
    public class ComplexAudit implements Audit {
        ...
    }

    // ------------------------------------------------

    public class SomeBean {
        @Inject @Complex Audit audit;
        // or: @Inject @Basic Audit audit;
    }
