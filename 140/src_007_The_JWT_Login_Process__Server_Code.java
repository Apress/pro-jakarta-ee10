package book.jakartapro.restsecurity.jwt;

import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static jakarta.ws.rs.core.MediaType.
    APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.*;

import java.security.Key;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/jwtlogin")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class JWTLogin {
	
  public static class Credentials {
    public String user;
    public String passwd;
    public String getUser() {
      return user;
    }
    public void setUser(String user) {
      this.user = user;
    }
    public String getPasswd() {
      return passwd;
    }
    public void setPasswd(String passwd) {
      this.passwd = passwd;
    }
  }
  
  @Context
  private UriInfo uriInfo;
	   
  @Inject
  private KeyGenerator keyGenerator;
    
  @PUT
  @Path("login")
  public Response authenticateUser(Credentials creds, 
        @Context HttpServletRequest req) {
    try {
      // Authenticate the user using the credentials 
      // provided
      authenticate(creds.user, creds.passwd, req);
 
      // Issue a token for the user
      String token = issueToken(creds.user);

      // TODO: possibly save token in some database
 
      // Return the token on the response
      return Response.ok().header(AUTHORIZATION, 
          "Bearer " + token).entity("{}").build();
 
    } catch (Exception e) {
      e.printStackTrace(System.err);
      return Response.status(UNAUTHORIZED).build();
    }  
  }

  @PUT
  @Path("logout")
  public Response unauthenticateUser(
        @Context HttpServletRequest req) {
    try {
      // TODO: possibly remove token from database

      // Unauthenticate
      unauthenticate(req);
 
      // Return empty response
      return Response.ok().entity("{}").build();
    } catch (Exception e) {
      e.printStackTrace(System.err);
      return Response.status(INTERNAL_SERVER_ERROR).
            build();
    }
  }
 
  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////
    
  private void authenticate(String login, String password, 
        HttpServletRequest req) throws Exception {
    // Authenticate using the container. As an 
    // alternative, you could implement
    // your own authentication procedure.
    boolean authenticated = false;
    Principal p = req.getUserPrincipal();
    if(p != null && p.getName().equals(login))
      authenticated = true;
    try {
      if(!authenticated)
        req.login(login, password); 
    } catch(ServletException ex) {
      throw new SecurityException("Invalid user/password");
    }	
  }

  private void unauthenticate(HttpServletRequest req) 
        throws Exception {
    // Unauthenticate using the container. As an 
    // alternative, you could implement
    // your own authentication procedure.
    boolean authenticated = false;
    Principal p = req.getUserPrincipal();
    if(p != null)
      authenticated = true;
    if(authenticated)
      req.logout(); 
  }
    
  private String issueToken(String login) {
    Key key = keyGenerator.generateKey();
    String jwtToken = Jwts.builder()
        .setSubject(login)
        .setIssuer(uriInfo.getAbsolutePath().toString())
        .setIssuedAt(new Date())
        .setExpiration(toDate(
            LocalDateTime.now().plusMinutes(15L)))
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
    return jwtToken;
  }
    
  private Date toDate(LocalDateTime localDateTime) {
    return Date.from(
      localDateTime.atZone(ZoneId.systemDefault()).
      toInstant());
  }
}
