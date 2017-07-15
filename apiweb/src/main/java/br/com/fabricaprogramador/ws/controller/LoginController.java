package br.com.fabricaprogramador.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricaprogramador.ws.model.Usuario;
import br.com.fabricaprogramador.ws.service.UsuarioService;

@RestController
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> autenticar(@RequestBody Usuario usuario) {
		Usuario usuAutenticado = this.usuarioService.buscarPorNome(usuario.getNome());
		return new ResponseEntity<Usuario>(usuAutenticado, HttpStatus.OK);
	}
}
