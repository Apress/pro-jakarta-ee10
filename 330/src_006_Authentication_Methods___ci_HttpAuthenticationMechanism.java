public class MyAuth 
implements HttpAuthenticationMechanism {
  @Inject IdentityStoreHandler ish;

  AuthenticationStatus validateRequest(
    HttpServletRequest request,
    HttpServletResponse response,
    HttpMessageContext httpMessageContext) 
  throws AuthenticationException {
      // use ish to validate the request
      ...
      CredentialValidationResult vr = ...;
      // we need to inform the container about the result
      // of the validation
      AuthenticationStatus res = 
        httpMessageContext.notifyContainerAboutLogin(vr);
      return res; 
  }
}
