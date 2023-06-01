  import java.util.HashMap;
  import java.util.Map;

  import jakarta.validation.ConstraintViolation;
  import jakarta.validation.ConstraintViolationException;
  import jakarta.ws.rs.core.Response;
  import jakarta.ws.rs.core.Response.Status;
  import jakarta.ws.rs.ext.ExceptionMapper;
  import jakarta.ws.rs.ext.Provider;

  @Provider
  public class ValidationExceptionMapper 
        implements 
        ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(
          ConstraintViolationException ex) {
        Map<String, String> errors = 
              new HashMap<String, String>();
        for(ConstraintViolation<?> viol : 
              ex.getConstraintViolations()) {
          errors.put(viol.getMessage(), 
                viol.getInvalidValue().toString());
        }
        return Response.status(
              Status.PRECONDITION_FAILED).
            entity(errors).build();
    }
}
