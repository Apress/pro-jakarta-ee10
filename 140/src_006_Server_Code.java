package book.jakartapro.restsecurity;

import java.io.InputStream;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

// http://localhost:8080/RestSecurity/static/index.html
@Path("/static")
@Stateless
public class StaticContent {

  @Inject ServletContext context;

  @GET
  @Path("/{path : .*}")
  public Response staticResources(
        @PathParam("path") final String path) {
    final InputStream resource = context.
        getResourceAsStream(String.format(
        "/static/%s", path));

    return null == resource ?
        Response.status(Response.Status.NOT_FOUND).build()
      : Response.ok().entity(resource).build();
  }
}
