package book.jakartapro.restdate;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestRestDate {
    @Test public void testStdDate() { 
        assertTrue("Unexpected date format", 
            new RestDate().stdDate().matches(
                "\\{\"date\":\" +
                "\\d{4}-\\d{2}-\\d{2}T" + 
                "\\d{2}:\\d{2}:\\d{2}\\..*"));
    }
}
