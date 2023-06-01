    public interface Audit {
        ...
    }

    // ------------------------------------------------

    @Vetoed
    public class BasicAudit implements Audit {
        ...
    }

    // ------------------------------------------------

    @Vetoed
    public class ComplexAudit implements Audit {
        ...
    }

    // ------------------------------------------------

    @ApplicationScoped
    public class AuditProducer {
      @Produces
      public Audit produce() {
        Audit res = null;
        if(...) { // make a decision
          res = new BasicAudit(); 
        } else {
          res = new ComplexAudit(); 
        }
        return res;
      }
    } 

    // ------------------------------------------------

    public class SomeBean {
        @Inject Audit audit;
    }
