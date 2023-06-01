    @ApplicationScoped
    public class AuditProducer {
        @Produces @SomeQualifier
        public Audit produce() {
          ...
        }  

        @Produces @SomeOtherQualifier
        public Audit produce() {
          ...
        } 
    }

    // ------------------------------------------------

    public class SomeBean {
        @Inject @SomeQualifier Audit audit1;
        @Inject @SomeOtherQualifier Audit audit2;
    }
