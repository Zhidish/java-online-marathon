package com.softserve.itacademy.web_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class WebAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
    @Autowired
    public WebAuthenticationFilter(WebAuthenticationManager webAuthenticationManager) {
        setAuthenticationManager(webAuthenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getParameter("username"),
                request.getParameter("password"));
        return getAuthenticationManager().authenticate(authentication);

    }
}
