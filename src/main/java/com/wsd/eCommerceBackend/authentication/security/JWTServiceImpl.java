package com.wsd.eCommerceBackend.authentication.security;

import com.wsd.eCommerceBackend.authentication.models.entities.RefreshToken;
import com.wsd.eCommerceBackend.authentication.repositories.RefreshTokenRepository;
import com.wsd.eCommerceBackend.exceptions.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    @Value("${wsd.jwtSecret}")
    private String jwtSecret;

    @Value("${wsd.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${wsd.jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    public JWTServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateRefreshToken(Map<String,Object> extraClaims ,UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtRefreshExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    @Transactional
    @Modifying
    public void deleteRefreshToken(Long userId) {
        try {
            refreshTokenRepository.deleteByUserId(userId);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void saveRefreshToken(Long userId, String refreshToken) {
        try {
            refreshTokenRepository.save(new RefreshToken(refreshToken,userId));
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean validateRefreshToken(Long userId, String refreshToken) {
        try {
            return refreshTokenRepository.existsByUserIdAndToken(userId,refreshToken);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(key);
    }

}
