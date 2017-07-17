package br.com.fabricaprogramador.ws.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class TokenFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// cabeçalho especifico da JWT que vem na request do 'client'
		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inexistente");
		}

		// estraindo o valor do token sem o 'Bearer '
		String token = header.substring(7);

		try {
			// verifica se o token é valido
			Jwts.parser().setSigningKey("token").parseClaimsJws(token).getBody();
		} catch (SignatureException e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalido");
		}
		
		chain.doFilter(request, response);
	}

}
