@Path("registerPzn/{pzn}")
@POST
@Produces("application/json")
public Response registerPzn(
      @PathParam("pzn") 
      @PZN8 @NotNull String pzn
    ) 
{
    ... maybe save in database ...
    return Response.status(200)...; // some OK message
}
