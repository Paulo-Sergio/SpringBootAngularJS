package br.com.fabricaprogramador.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricaprogramador.ws.model.Cliente;
import br.com.fabricaprogramador.ws.model.Estado;
import br.com.fabricaprogramador.ws.service.EstadoService;

@RestController
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	// End Points
	@RequestMapping(method = RequestMethod.POST, value = "/estados", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estado> cadastrarEstado(@RequestBody Estado estado) {
		Estado estadoCadastrado = this.estadoService.cadastrar(estado);
		return new ResponseEntity<Estado>(estadoCadastrado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Estado>> buscarTodos() {
		Collection<Estado> estados = this.estadoService.buscarTodos();
		return new ResponseEntity<Collection<Estado>>(estados, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/estados/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estado> buscarEstadoPorId(@PathVariable Integer id) {
		Estado estado = this.estadoService.buscarPorId(id);
		return new ResponseEntity<Estado>(estado, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/estados", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estado> alterarEstado(@RequestBody Estado estado) {
		Estado estadoAlterado = this.estadoService.alterar(estado);
		return new ResponseEntity<Estado>(estadoAlterado, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/estados/{id}")
	public ResponseEntity<Estado> excluirEstado(@PathVariable Integer id) {
		Estado estado = this.estadoService.buscarPorId(id);
		if(estado == null)
			return new ResponseEntity<Estado>(HttpStatus.NOT_FOUND);
		
		this.estadoService.excluir(estado);
		
		return new ResponseEntity<Estado>(HttpStatus.OK);
	}
}
