package br.com.fabricaprogramador.ws.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricaprogramador.ws.model.Usuario;
import br.com.fabricaprogramador.ws.service.UsuarioService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	// tem que retornar o token (JWT Json Web Token)
	@RequestMapping(method = RequestMethod.POST, value = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {
		if (usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("Nome e senha são obrigatorios!");
		}

		// consulta no banco
		Usuario usuAutenticado = this.usuarioService.buscarPorNome(usuario.getNome());

		if (usuAutenticado == null) {
			throw new ServletException("Usuario não encontrado!");
		}

		if (!usuAutenticado.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Senha incorreta!");
		}

		// TOKEN {token: Bearer fgsdugjojo43jg3j8g934g2wgj8w9gj}
		String token=  Jwts.builder()
				.setSubject(usuAutenticado.getNome())
				.signWith(SignatureAlgorithm.HS512, "banana")
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.compact();

		return new LoginResponse(token);
	}

	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;
		}
	}
}