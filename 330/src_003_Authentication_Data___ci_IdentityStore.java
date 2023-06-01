@LdapIdentityStoreDefinition(
  url = "ldap://localhost:4321",
  bindDn = "ldap@thecompany",
  bindDnPassword = "password"
)

@DatabaseIdentityStoreDefinition(
  dataSourceLookup = "java:comp/env/ExampleDS",
  callerQuery = "SELECT password from USERS where name = ?"
)
