  // Corresponds to for example AJAX calls from jQuery:
  // $.ajax({
  //  method: "POST",
  //  contentType: "application/json",
  //  url: "../webapi/date",
  //  data: '{ timeZone: "UTC", format: "standard" }'
  // })
  // .done(function( msg, textStatus, jqXHR ) {
  //   alert( "Data Saved: " + msg );
  // });

  @POST
  @Path("/date")
  @Consumes(MediaType.APPLICATION_JSON)
  ...
  public Response stdDate(JsonStructure data) {
      ...
  }
