public interface IdentityStore {
  CredentialValidationResult 
      validate(Credential credential);
  Set<String> 
      getCallerGroups(CredentialValidationResult result);
  int priority();
  Set<ValidationType> validationTypes();
  enum ValidationType { VALIDATE, PROVIDE_GROUPS }
}
