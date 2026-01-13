package com.springapp.bookmarks_manager.Configuration;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springapp.bookmarks_manager.Service.JwtService;
import com.springapp.bookmarks_manager.Service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter  extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(header!=null && header.startsWith("Bearer ")){
            token = header.substring(7);
            username = jwtService.extractUserName(token);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){ /// not earlier authenticated
            
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

            if(jwtService.validateToken(token, userDetails)){

                UsernamePasswordAuthenticationToken authToken
                    =  new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);


            }
        }
        filterChain.doFilter(request, response);
        
    }

}
