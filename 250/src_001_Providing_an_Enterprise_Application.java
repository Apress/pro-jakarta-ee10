// ----- file TheDate.java
package book.jakartapro.appclient.ejb;

import java.time.ZonedDateTime;

import book.jakartapro.appclient.ejb.interfaces.
    TheDateLocal;
import book.jakartapro.appclient.ejb.interfaces.
    TheDateRemote;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

@Stateless
@Local(TheDateLocal.class)
@Remote(TheDateRemote.class)
public class TheDate {
    public String fetchDate() {
        return ZonedDateTime.now().toString();
    }
}


// ----- file TheDateLocal.java
package book.jakartapro.appclient.ejb.interfaces;

public interface TheDateLocal {
    String fetchDate();
}


// ----- file TheDateRemote.java
package book.jakartapro.appclient.ejb.interfaces;

public interface TheDateRemote {
    String fetchDate();
}
