package book.jakartapro.marvin.war;

import java.util.HashMap;
import java.util.Map;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

import book.jakartapro.marvin.ejb.interfaces.
       LocalMirrorLocal;

/**
* REST Web Service
*/
@Path("/")
public class Marvin { 
  static Map<String,String> CM = new HashMap<>(); 
  static {
    CM.put(".jar", "application/java-archive");
    CM.put(".pom", "text/xml");
  }	  

  @EJB LocalMirrorLocal localMirror;
	
  @GET
  @Path("repo/{p : .*}")
  public Response fetch(@PathParam("p") String path, 
        @Context HttpServletRequest requ) { 
    return getFromRepo(Response.status(200).
          entity(path), path).build();  
  }
    
  private ResponseBuilder getFromRepo(ResponseBuilder rb, 
        String path) {
    String suffix = path.substring(path.lastIndexOf("."));
    String outType = CM.getOrDefault(suffix, "text/plain");
    rb.type(outType);			
    rb.entity(localMirror.fetchBytes(path, rb));
    return rb;
  }
}
