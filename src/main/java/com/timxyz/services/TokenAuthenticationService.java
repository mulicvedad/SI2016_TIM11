package com.timxyz.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.JsonObjectBuilder;
import javax.json.JsonObject;
import javax.json.Json;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import java.io.IOException;

import static java.util.Collections.emptyList;

import com.timxyz.repositories.AccountRepository;
import com.timxyz.models.Account;

public class TokenAuthenticationService {

    private static AccountRepository accountRepository;
    private static Logger logger = Logger.getLogger(TokenAuthenticationService.class.getName());

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    //kasnije ce se staviti prava tajna
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_CORS = "Access-Control-Allow-Origin";
    static final String ALLOWED_ORIGIN = "http://localhost:4200";
    static final String FIELD_NAME_TOKEN = "jwt";
    static final String FIELD_NAME_ROLE = "role";
    static final String FIELD_NAME_USERNAME = "username";
    static final String HEADER_STRING = "Authorization";

    //ovdje cemo morati dodati uloge u odgovor da bi na klijentskoj strani znali privilegije
    public static void addAuthentication(HttpServletRequest req,
        HttpServletResponse res, String username) throws IOException{
        //ovaj dio sam dodao zbor neuspjelog Dependancy Injection (AccountRepository)
        if (accountRepository == null) {
            ServletContext servletContext = req.getServletContext();
                WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            accountRepository = webApplicationContext.getBean(AccountRepository.class);
        }
        Account account = accountRepository.findByUsername(username);
        String JWT = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();

        res.addHeader(HEADER_CORS, ALLOWED_ORIGIN);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
        	.add(FIELD_NAME_USERNAME, username)
            .add(FIELD_NAME_TOKEN, JWT)
            .add(FIELD_NAME_ROLE, account.getRole().getName());

        JsonObject responseObj = objectBuilder.build();
        logger.info(responseObj.toString());
        res.getWriter().write(responseObj.toString());
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        
        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        accountRepository = webApplicationContext.getBean(AccountRepository.class);

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String userReq = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

            Account account = accountRepository.findByUsername(userReq);

            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

	        if (account != null) {
	            grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().getName()));
	        }     

	        logger.info(grantedAuthorities.toArray()[0].toString());

	        return userReq != null ? new UsernamePasswordAuthenticationToken(userReq, null, grantedAuthorities) : null;
        }
    }
}