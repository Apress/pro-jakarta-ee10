public interface HttpAuthenticationMechanism {

  AuthenticationStatus validateRequest(
    HttpServletRequest request,
    HttpServletResponse response,
    HttpMessageContext httpMessageContext) 
  throws AuthenticationException;

  AuthenticationStatus secureResponse(
    HttpServletRequest request, 
    HttpServletResponse response, 
    HttpMessageContext httpMessageContext) 
  throws AuthenticationException;

  void cleanSubject(
    HttpServletRequest request, 
    HttpServletResponse response, 
    HttpMessageContext httpMessageContext) 
  throws AuthenticationException;
}
