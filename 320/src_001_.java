import io.jsonwebtoken.Jwts;
...
    Key key = keyGenerator.generateKey();
    String jwtToken = Jwts.builder()
        .setSubject(login)
        .setIssuer(uriInfo.getAbsolutePath().toString())
        .setIssuedAt(new Date())
        .setExpiration(toDate(
            LocalDateTime.now().plusMinutes(15L)))
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
...    
