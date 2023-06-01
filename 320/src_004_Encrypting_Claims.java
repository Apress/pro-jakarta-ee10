import org.jose4j.jwk.JsonWebKey;
...
  String jwkJson = "{" +
     "\"kty\":\"oct\"," +
     "\"k\":" +
       "\"ffdbdh4grh5tffnkkj5tvrfcddehjihhhfddeesdfff\"" +
  "}";
  JsonWebKey jwk = JsonWebKey.Factory.newJwk(jwkJson);	
