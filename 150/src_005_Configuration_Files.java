package book.jakartapro.javamvc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;

import org.glassfish.jersey.message.internal.MediaTypes;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.Providers;

@Provider
@PreMatching
public class HttpMethodOverride 
      implements ContainerRequestFilter {
  @Context
  private Providers providers;	

  @Override
  public void filter(
      ContainerRequestContext requestContext) 
      throws IOException {
    if (isForm(requestContext)) {			
      Form form = getForm(requestContext);
      String pseudoMethod = (String) form.asMap().
            getFirst("_method");
      if(pseudoMethod != null) {
        requestContext.setMethod(pseudoMethod);				
      }
    }

    String methodFromHeader = requestContext.
          getHeaderString("X-HTTP-Method-Override");
    if(methodFromHeader != null) {
      requestContext.setMethod(methodFromHeader);
    }		
  }

  //////////////////////////////////////////////////
  //////////////////////////////////////////////////
	
  private boolean isForm(
        ContainerRequestContext requestContext) {
    return requestContext.hasEntity()
          && MediaTypes.typeEqual(
             MediaType.APPLICATION_FORM_URLENCODED_TYPE,
             requestContext.getMediaType());		
  }

  private Form getForm(
        ContainerRequestContext requestContext) 
        throws IOException {
    ByteArrayInputStream bis = toResettableStream(
          requestContext.getEntityStream());
    Form form = providers.getMessageBodyReader(
          Form.class, Form.class, new Annotation[0],
          MediaType.APPLICATION_FORM_URLENCODED_TYPE).
          readFrom(
            Form.class, 
            Form.class, 
            new Annotation[0],
            MediaType.APPLICATION_FORM_URLENCODED_TYPE,
            null, 
            bis);
    bis.reset();
    requestContext.setEntityStream(bis);
    return form;
  }

  private ByteArrayInputStream toResettableStream(
        InputStream entityStream) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int len;
    while ((len = entityStream.read(buffer)) > -1) {
      baos.write(buffer, 0, len);
    }
    baos.flush();
    return new ByteArrayInputStream(baos.toByteArray());
  }
}
