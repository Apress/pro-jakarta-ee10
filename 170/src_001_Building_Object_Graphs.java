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
        private Address address;
        // getters and setters... 
    }

    public class Invoice {
        private Person buyer;
        private LocalDateTime time;
        private List entries = new ArrayList();
        // getters and setters... 
    }

    ...
    Person buyer = new Buyer();
    buyer.setFirstName("Peter");
    // Set other Buyer fields...
    Invoice inv = new Invoice();
    inv.setBuyer(buyer);
    inv.setTime(LocalDateTime.now());
    // Set other Invoice fields...
