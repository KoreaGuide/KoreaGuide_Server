package com.example.koreaguide.ifs;

import com.example.koreaguide.repository.UserRepository;
import com.example.koreaguide.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private final JwtUtil jwtUtil;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        super.doFilterInternal(request, response, chain);
       Authentication authentication = getAuthentication(request);
       if(authentication==null){
           chain.doFilter(request,response);
           System.out.println("NULL");
            return;
       }
        if(authentication!=null){
            System.out.println("NOT NULL");
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }
        chain.doFilter(request,response);
    }

    private Authentication getAuthentication(HttpServletRequest request){
        String token=request.getHeader("Authorization");
        if(token==null){
            return null;
        }
        if(token.equals("Bearer no_value")){
            return null;
        }

        // TODO: jwtUtil에서 claims 얻기
        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims,null);

        return authentication;
    }


}
