package ...;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, 
       region="Region1")
public class Person {
    .... some JPA class
}
