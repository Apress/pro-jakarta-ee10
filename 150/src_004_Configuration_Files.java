package book.jakartapro.javamvc;

import jakarta.servlet.FilterChain;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Redirecting http://localhost:8080/JavaMvc/
 * This way we don't need a <welcome-file-list> in web.xml
 */
@WebFilter(urlPatterns = "/")
public class RootRedirector extends HttpFilter {
    private static final long serialVersionUID = 
          7332909156163673868L;

    @Override
    protected void doFilter(final HttpServletRequest req, 
          final HttpServletResponse res, 
          final FilterChain chain) throws IOException {
        res.sendRedirect("mvc/pets");
    }
}
