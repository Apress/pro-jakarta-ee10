    @Path("/pi")
    @GET
    @Produces("application/json")
    public String pi() {
      return "{\"pi\":" + calcPi() + "}";
    }

    private double calcPi() {
      int d = 1;
      double sum = 0;	 
      for(int i=0;i<100_000_000;i++) {
        if(i % 2 == 0) sum += 4.0/d; else sum -= 4.0/d;
        d += 2;
      }
      return sum;
    }
