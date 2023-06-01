import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.
      ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
...
  JsonWebEncryption jwe = new JsonWebEncryption();
	
  // The payload of the JWE token
  jwe.setPayload(claims.toJson());
	
  // The "alg" header indicates the key management mode 
  // for this JWE.
  // For more options, see API documentation
  jwe.setAlgorithmHeaderValue(
        KeyManagementAlgorithmIdentifiers.DIRECT);
	
  // The "enc" header indicates the content encryption 
  // algorithm to be used.
  // For more options, see API documentation
  jwe.setEncryptionMethodHeaderParameter(
    ContentEncryptionAlgorithmIdentifiers.
    AES_128_CBC_HMAC_SHA_256);
	
  // Set the key on the JWE. 
  jwe.setKey(jwk.getKey());	
