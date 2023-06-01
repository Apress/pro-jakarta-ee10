  class MyColorAdapter implements 
        JsonbAdapter<Color, String> {
    @Override
    public String adaptToJson(Color obj) 
    throws Exception {
      return "#" +   
        String.format("%02x", obj.r) +
        String.format("%02x", obj.g) +
        String.format("%02x", obj.b);
    }

    @Override
    public Color adaptFromJson(String obj) 
    throws Exception {
      int r = Integer.parseInt( obj.substring(1,3), 16);
      int g = Integer.parseInt( obj.substring(3,5), 16);
      int b = Integer.parseInt( obj.substring(5,7), 16);
      return new Color(r,g,b); 
    } 
  }
