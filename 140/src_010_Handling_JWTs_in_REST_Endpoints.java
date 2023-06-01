package book.jakartapro.restsecurity.jwt;

import java.io.IOException;
import java.security.Key;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter 
      implements ContainerRequestFilter {
 
  @Inject
  private KeyGenerator keyGenerator;
 
  @Override
  public void filter(ContainerRequestContext 
        requestContext) throws IOException {
    // Get the HTTP Authorization header from the request
    String authorizationHeader = requestContext.
          getHeaderString(HttpHeaders.AUTHORIZATION);
    if(authorizationHeader == null) 
      authorizationHeader = "";
        
    if(authorizationHeader.equals("")) {
      requestContext.abortWith(Response.status(
            Response.Status.UNAUTHORIZED).build());
    } else {
      // Extract the token from the HTTP Authorization 
      // header
      String token = authorizationHeader.
            replaceAll("^Bearer ","");
      try {
        // Validate the token
        Key key = keyGenerator.generateKey();
        Jwts.parser().setSigningKey(key).
              parseClaimsJws(token);
      } catch (Exception e) {
        //e.printStackTrace(System.err);
        requestContext.abortWith(Response.status(
              Response.Status.UNAUTHORIZED).build());
      }
    }
  }
}
