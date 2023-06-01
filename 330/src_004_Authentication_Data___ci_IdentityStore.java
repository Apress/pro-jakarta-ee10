@DatabaseIdentityStoreDefinition(
  dataSourceLookup = "java:comp/env/ExampleDS",
  callerQuery = "SELECT password from USERS where name = ?"
)
@ApplicationScoped
public class ApplConfig {
}
