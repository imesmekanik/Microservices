package org.example.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.example.exception.AuthServiceException;
import org.example.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    @Value("${myjwt.secretkey}")
    private String secretkey;
    @Value("${myjwt.audience}")
    private String audience;
    @Value("${myjwt.issuer}")
    private String issuer;
    public String createToken(Long authid){
        String token=null;
        try{
            Algorithm algorithm= Algorithm.HMAC256(secretkey);
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new java.util.Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60)))
                    .withClaim("authid",authid)
                    .sign(algorithm);
            return token;

        }catch (Exception e){
            return null;
        }
    }
    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(secretkey);
            JWTVerifier jwtVerifier=JWT.require(algorithm)
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build();
            DecodedJWT decodedToken= jwtVerifier.verify(token);
            if(decodedToken==null) throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
            Long authid=decodedToken.getClaim("authid").asLong();
            return Optional.of(authid);
        }catch (Exception e){
            throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
        }
    }
}
