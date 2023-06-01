  ...
  @GET
  @Produces("application/json")
  
  // MicroProfile Metrics
	@Timed(name = "stdDate.timer",
	    absolute = true,
	    displayName = "stdDate Timer",
	    description = "Time taken by stdDate.")
	@Counted(name = "stdDate",
	    absolute = true,
	    displayName = "stdDate call count",
	    description = "Number of times we invoked stdDate")
	@Metered(name = "stdDateMeter",
	    displayName = "stdDate call frequency",
	   description = "Rate the trhoughput of stdDate.")
  
  // OpenAPI
  @Operation(summary = "Outputs the date",
      description = "This method outputs the date")
  
  public String stdDate() {
    return "{\"date\":\"" + 
        ZonedDateTime.now().toString() + "\"}"; 
  }
  ...
