...
import org.hibernate.Session;
...
    @PersistenceContext(unitName = "my-persistence-unit")
    private EntityManager entityManager;
...
    Session session = entityManager.unwrap(Session.class);
    // directly use the Hibernate session:
    // ...  				