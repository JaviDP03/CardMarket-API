package com.daw.cardmarket.utils;

import com.daw.cardmarket.model.Actor;
import com.daw.cardmarket.model.Token;
import com.daw.cardmarket.service.ActorService;
import com.daw.cardmarket.service.AdminService;
import com.daw.cardmarket.service.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Autowired
    @Lazy
    private ActorService actorService;

    @Autowired
    @Lazy
    private AdminService adminService;

    @Autowired
    @Lazy
    private UsuarioService usuarioService;

    private static final String jwtSecret = "miSuperClaveSecretaParaJWT123456789";
    private static final int jwtExpirationMs = 86400000; // 1 día en milisegundos

    public static Token generateToken(Authentication authentication) {
        String usuario = authentication.getName();
        String rol = authentication.getAuthorities().iterator().next().getAuthority();
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        Date fechaExpiracion = new Date(new Date().getTime() + jwtExpirationMs);

        String token = Jwts.builder()
                .subject(usuario)
                .issuedAt(new Date())
                .expiration(fechaExpiracion)
                .claim("rol", rol)
                .signWith(key)
                .compact();

        return new Token(token, fechaExpiracion);
    }

    public static String getToken(HttpServletRequest request) {
        String tokenBearer = request.getHeader("Authorization");
        if (tokenBearer != null && tokenBearer.startsWith("Bearer ")) {
            return tokenBearer.substring(7);
        }
        return null;
    }

    public static String getUsernameFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public static Date getExpirationDateFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload().getExpiration();
    }

    public static boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

            // Verifica la firma y parsea el token
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (SignatureException e) {
            // La firma del JWT no es válida
            System.err.println("Firma JWT inválida: " + e.getMessage());
        } catch (MalformedJwtException e) {
            // El JWT no tiene el formato esperado
            System.err.println("Token JWT malformado: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            // El JWT ha expirado
            System.err.println("Token JWT expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            // El JWT contiene algo no soportado
            System.err.println("Token JWT no soportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // El JWT está vacío o es nulo
            System.err.println("JWT vacío: " + e.getMessage());
        }

        return false;
    }

    public <T> T userLogin() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        Actor actor = actorService.findByUsername(username);

        if (actor == null) {
            return null;
        }

        switch (actor.getRol()) {
            case ADMIN:
                return (T) adminService.findByUsername(username).orElse(null);
            case USUARIO:
                return (T) usuarioService.findByUsername(username).orElse(null);
            default:
                return null;
        }
    }
}
