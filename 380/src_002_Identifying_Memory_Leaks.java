@Path("/")
public class MemLeak {
    static class A {  int x = 7;  }
    static List<A> list = new ArrayList<>();

    @Path("/memleak")
    @GET
    @Produces("application/json")
    public String memLeak() {
        ...
    }
}
