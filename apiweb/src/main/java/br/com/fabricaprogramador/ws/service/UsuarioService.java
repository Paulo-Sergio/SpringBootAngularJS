package br.com.fabricaprogramador.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabricaprogramador.ws.model.Usuario;
import br.com.fabricaprogramador.ws.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario cadastrar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
	public Usuario buscarPorNome(String nome){
		return this.usuarioRepository.buscarPorNome(nome);
	}

	
}
