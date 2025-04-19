package me.dionclei.auth;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class TokenService {
	
    private final RSAPublicKey publicKey;

    public TokenService(Path publicKeyPath) throws Exception {
        this.publicKey = loadPublicKey(publicKeyPath);
    }

    public UserAuth validateToken(String token) {
        try {
            var decoded = JWT.require(Algorithm.RSA256(publicKey, null))
                    .withIssuer("Ecommerce")
                    .build()
                    .verify(token);
            return new UserAuth(Long.parseLong(decoded.getSubject()), decoded.getClaim("roles").asList(String.class));
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private RSAPublicKey loadPublicKey(Path filepath) throws Exception {
        String key = Files.readString(filepath)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
    }
}
