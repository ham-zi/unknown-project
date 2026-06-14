package com.one.unknown.configuration.filter;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.one.unknown.auth.model.vo.CustomUserDetails;
import com.one.unknown.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String uri = request.getRequestURI();
		String method = request.getMethod();
		System.out.println("1111");
		System.out.println(request.getRequestURI());
		 System.out.println("URI: " + uri + " METHOD: " + method);
		if("GET".equals(method)&&uri.startsWith("/api/boards")) return true;
		if("GET".equals(method)&&uri.startsWith("/uploads")) return true;
		if("POST".equals(method)&&uri.startsWith("/api/boards")) return true;
		if("POST".equals(method)&&uri.startsWith("/api/likes")) return true;
		return uri.equals("/api/auth/login") || uri.equals("/api/auth/refresh");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		
		String token = authorization.substring(7);
		try {			
			Claims claims = jwtUtil.jwtParse(token);
			String username = claims.getSubject();
			
			CustomUserDetails user = (CustomUserDetails)userDetailsService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (ExpiredJwtException e) {
			response.setStatus(401);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write("토큰만료");
			return;
		} catch(JwtException e) {
			response.setStatus(401);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write("토큰만료");
			return;
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}