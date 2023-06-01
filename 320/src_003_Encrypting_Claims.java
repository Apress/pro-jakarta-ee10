import org.jose4j.jwt.JwtClaims;
...
    JwtClaims claims = new JwtClaims();
    claims.setIssuer("Issuer");  
    // <- token creator
    claims.setIssuedAtToNow();  
    // <- token created now
    claims.setAudience("Audience"); 
    // <- intended token recipient
    claims.setExpirationTimeMinutesInTheFuture(10); 
    // <- expiry time
...                
