package me.dionclei.ecommerce_user_service.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalUnit;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import me.dionclei.ecommerce_user_service.entities.EcommerceUser;
import me.dionclei.ecommerce_user_service.services.interfaces.TokenService;

@Service
public class TokenServiceRSA implements TokenService {
	
	private RSAPrivateKey privateKey;
	private RSAPublicKey publicKey;
	
	public TokenServiceRSA() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
		privateKey = generatePrivateKey();
		publicKey = generatePublicKey();
	}
	
	public String generateToken(EcommerceUser user) {
		try {
			return JWT.create().withIssuer("Ecommerce")
					.withExpiresAt(generateExpiresAt())
					.withSubject(user.getId().toString())
					.sign(Algorithm.RSA256(privateKey));
		} catch (JWTCreationException e) {
			return null;
		}
	}
	

	@Override
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.RSA256(publicKey, null);
			return JWT.require(algorithm)
					.withIssuer("Ecommerce")
					.build()
					.verify(token)
					.getSubject();
		} catch (Exception e) {
			return null;
		}
	}
	
	private RSAPrivateKey generatePrivateKey() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
	    byte[] keyBytes = Files.readAllBytes(Paths.get("../private_key.pem"));
	    String privateKeyPEM = new String(keyBytes)
	        .replaceAll("-----BEGIN PRIVATE KEY-----", "")
	        .replaceAll("-----END PRIVATE KEY-----", "")
	        .replaceAll("\\s", "");
	    byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
	    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
	    return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);
	}

	private RSAPublicKey generatePublicKey() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
	    byte[] keyBytes = Files.readAllBytes(Paths.get("../public_key.pem"));
	    String publicKeyPEM = new String(keyBytes)
	        .replaceAll("-----BEGIN PUBLIC KEY-----", "")
	        .replaceAll("-----END PUBLIC KEY-----", "")
	        .replaceAll("\\s", "");
	    byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
	    return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
	}
	
	private Instant generateExpiresAt() {
		return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.UTC);
	}
}
