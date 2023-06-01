    public class Address {
        private String country;
        private String state;
        private String city;
        private String street;
        private String number;
        private String zip;
        // getters and setters... 
    }

    public class Person {
        private String firstName;
        private String lastName;
        private char gender; 
        private String ssn;
        private LocalDate birthday;
        @Inject private Address address;
        // getters and setters... 
    }

    public class Invoice {
        @Inject private Person buyer;
        private LocalDateTime time;
        private List entries = new ArrayList();
        // getters and setters... 
    }

    ...
    // Somewhere, as a class member:
    @Inject Invoice inv;
