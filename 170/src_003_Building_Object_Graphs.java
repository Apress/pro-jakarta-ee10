  // Faces: -------------------------------------
  @ManagedBean
  @SessionScoped
  public class SomeJsfBean {
    @Inject Invoice inv;
    ...      
  }

  // REST endpoint: -----------------------------
  @Path("/invoice")
  public class RestInvoice {
    @Inject Invoice inv;

    @GET
    @Path("show")
    @Produces("application/json")
    public String show() {
      return "{ ... }"; 
    }
  }


  // EJB: ---------------------------------------
  @Stateful
  public class InvoiceBean {
      @Inject Invoice inv;
      ...
  }
