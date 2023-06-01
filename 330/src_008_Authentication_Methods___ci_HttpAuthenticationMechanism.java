@FormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
        loginPage="/login/login.xhtml",
        errorPage="/login/error.xhtml"
    )
)
@WebServlet("/secured")
@DeclareRoles({ "user", "authorized" })
@ServletSecurity(
    @HttpConstraint(rolesAllowed = "authorized"))
public class SecuredContentServlet extends HttpServlet {
    ...
}
