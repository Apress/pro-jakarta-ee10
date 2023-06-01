import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.
      ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;
...

  String incommingJwe = ...;

  String jwkJson = "{" +
     "\"kty\":\"oct\"," +
     "\"k\":" +
       "\"ffdbdh4grh5tffnkkj5tvrfcddehjihhhfddeesdfff\"" +
  "}";
  JsonWebKey jwk = JsonWebKey.Factory.newJwk(jwkJson);	

  JsonWebEncryption jwe = new JsonWebEncryption();

  // Set the algorithm constraints. 
  // For explanation, see API documentation
  AlgorithmConstraints algConstraints = 
    new AlgorithmConstraints(ConstraintType.PERMIT,
    KeyManagementAlgorithmIdentifiers.DIRECT);
  jwe.setAlgorithmConstraints(
      algConstraints);
  AlgorithmConstraints encConstraints = 
    new AlgorithmConstraints(ConstraintType.PERMIT,
    ContentEncryptionAlgorithmIdentifiers.
        AES_128_CBC_HMAC_SHA_256);
  jwe.setContentEncryptionAlgorithmConstraints(
      encConstraints);

  // Register the JWE token
  jwe.setCompactSerialization(incommingJwe);

  // Set the key
  receiverJwe.setKey(jwk.getKey());
