package book.jakartapro.restsecurity;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import book.jakartapro.restsecurity.jwt.JWTTokenNeeded;

@Path("/secured")
@JWTTokenNeeded
public class Secured {
  @GET
  @Path("/{path : .*}")
  @Produces("application/json")
  public Response hello(@PathParam("path") String path) {
    return Response.ok().entity(
          "{\"msg\":\"" + "Secured Content" + "\"}" 
          ).build();
  }
}

==--

package book.jakartapro.restsecurity;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/unsecured")
public class NonSecured {
  @GET
  @Path("/{path : .*}")
  @Produces("application/json")
  public Response hello(@PathParam("path") String path) {
    return Response.ok().entity(
        "{\"msg\":\"" + "Unsecured Content" + "\"}" 
    ).build();
  }
}
