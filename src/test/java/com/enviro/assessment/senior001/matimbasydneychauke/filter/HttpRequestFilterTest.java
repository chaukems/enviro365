package com.enviro.assessment.senior001.matimbasydneychauke.filter;

import com.enviro.assessment.senior001.matimbasydneychauke.config.JwtTokenUtil;
import com.enviro.assessment.senior001.matimbasydneychauke.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RunWith(value = MockitoJUnitRunner.class)
public class HttpRequestFilterTest {

    @InjectMocks
    HttpRequestFilter jwtRequestFilter;

    @Mock
    private MyUserDetailsService jwtUserDetailsService;

    @Mock
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @Mock
    JwtTokenUtil jwtTokenUtil;

    @Mock
    SecurityContextHolder securityContextHolder;

    HttpServletRequest request;
    HttpServletResponse response;
    FilterChain chain;

    @Before
    public void before() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        chain = mock(FilterChain.class);
    }

    @Test
    public void doFilterInternalNullToken() throws IOException, ServletException {
        when(request.getHeader(AUTHORIZATION)).thenReturn(null);
        jwtRequestFilter.doFilterInternal(request, response, chain);
        verifyNoInteractions(securityContextHolder, jwtUserDetailsService, usernamePasswordAuthenticationToken);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void doFilterInternalExceptionToken() throws IOException, ServletException {
        when(request.getHeader(AUTHORIZATION)).thenReturn("123");
        jwtRequestFilter.doFilterInternal(request, response, chain);
        verifyNoInteractions(securityContextHolder, jwtUserDetailsService, usernamePasswordAuthenticationToken);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void doFilterInternalAuthenticationNotNull() throws ServletException, IOException {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
        when(request.getHeader(AUTHORIZATION)).thenReturn(token);

        SecurityContext context = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        jwtRequestFilter.doFilterInternal(request, response, chain);
        verifyNoInteractions(securityContextHolder, jwtUserDetailsService, usernamePasswordAuthenticationToken);
        verify(chain).doFilter(request, response);
        verify(context, never()).setAuthentication(any(Authentication.class));
    }

    @Test
    public void doFilterInternalAuthenticationNullUser() throws ServletException, IOException {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
        when(request.getHeader(AUTHORIZATION)).thenReturn(token);

        SecurityContext context = mock(SecurityContext.class);

        jwtRequestFilter.doFilterInternal(request, response, chain);
        verifyNoInteractions(usernamePasswordAuthenticationToken);
        verify(context, never()).setAuthentication(any(Authentication.class));
        verify(chain).doFilter(request, response);
    }

    @Test
    public void doFilterInternalAuthenticationSuccess() throws ServletException, IOException {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
        when(request.getHeader(AUTHORIZATION)).thenReturn(token);

        SecurityContext context = mock(SecurityContext.class);
        UserDetails userDetails = new User("ess-signflow", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", Collections.emptyList());

        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);

        jwtRequestFilter.doFilterInternal(request, response, chain);
        verify(chain).doFilter(request, response);
    }

    /*
    @Test
    public void doFilterInternalAuthenticationSuccess() throws ServletException, IOException {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
        when(request.getHeader(AUTHORIZATION)).thenReturn(token);
        when(request.getRemoteAddr()).thenReturn("localhost");

        SecurityContext context = mock(SecurityContext.class);
        when(securityAppContext.getContext()).thenReturn(context);
        when(context.getAuthentication()).thenReturn(null);

        User u = new User("username", "pass", "salt", "role");
        when(userService.validateUser("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg", request.getRemoteAddr())).thenReturn(u);

        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);
        when(usernamePasswordAuthenticationTokenFactory.create(u)).thenReturn((UsernamePasswordAuthenticationToken) authentication);

        jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);

        verify(context).setAuthentication(authentication);
        verify(chain).doFilter(request, response);
    }
     */
}
