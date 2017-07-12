package br.com.fabricaprogramador.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabricaprogramador.ws.model.Estado;
import br.com.fabricaprogramador.ws.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	// negocios
	public Estado cadastrar(Estado estado) {
		return this.estadoRepository.save(estado);
	}

	public Collection<Estado> buscarTodos() {
		return this.estadoRepository.findAll();
	}

	public void excluir(Estado estado) {
		this.estadoRepository.delete(estado);
	}

	public Estado buscarPorId(Integer id) {
		return this.estadoRepository.findOne(id);
	}
	
	public Estado alterar(Estado estado){
		return this.estadoRepository.save(estado);
	}

}
