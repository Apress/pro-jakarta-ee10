    public class SomeBean {
        ...

        @PostConstruct
        public void postContruct() {
            System.out.println("POST-CONSTRUCT");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("PRE-DESTROY");
        }
    } 
