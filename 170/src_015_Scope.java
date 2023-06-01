    @ApplicationScope
    public class SomeBean {
        ...
    } 

    // ------------------------------------------------

    public class SomeBean {
        @Produces @ApplicationScoped 
        public SomeOtherBean produce() {
            ...
        }
    } 
