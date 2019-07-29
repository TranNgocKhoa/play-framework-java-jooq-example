package com.khoa.play.utils;

import com.khoa.play.jooq.tables.pojos.User;
import io.jsonwebtoken.*;

import java.util.*;

import com.khoa.play.constants.*;
import io.jsonwebtoken.impl.TextCodec;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class JwtsUtil {

    public String getJWT(User user) {
        Date now = new Date();
        long t = now.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, Constants.TOKEN_EXPIRE);
        Date expire = calendar.getTime();

        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("email", user.getEmail());
        params.put("status", user.getStatus());

        return Jwts.builder()
                .setClaims(params)
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)
                .compact();
    }

    public boolean validateJWT(String jwt) throws Exception {
        try {
            Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public String getClaims(String jwt) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(jwt);
        return  claimsJws.getSignature();
    }
}

