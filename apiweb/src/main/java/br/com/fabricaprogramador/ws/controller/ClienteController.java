package br.com.fabricaprogramador.ws.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricaprogramador.ws.model.Cliente;

@RestController
public class ClienteController {

	private Map<Integer, Cliente> clientes = new HashMap<>();
	private Integer proximoId = 0;

	// Negocios
	private Cliente cadastrar(Cliente cliente) {
		// settar ID
		this.proximoId++;
		cliente.setId(this.proximoId);

		this.clientes.put(cliente.getId(), cliente);

		return cliente;
	}

	private Collection<Cliente> buscarTodos() {
		return this.clientes.values();
	}

	private void excluir(Cliente cliente) {
		this.clientes.remove(cliente.getId());
	}

	private Cliente buscarPorId(Integer id) {
		return this.clientes.get(id);
	}

	private Cliente alterar(Cliente cliente) {
		this.clientes.put(cliente.getId(), cliente);
		return cliente;
	}

	// End Points
	@RequestMapping(method = RequestMethod.POST, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		Cliente clienteCadastrado = this.cadastrar(cliente);

		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {
		Collection<Cliente> clientes = this.buscarTodos();

		return new ResponseEntity<Collection<Cliente>>(clientes, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {
		Cliente clienteAlterado = this.alterar(cliente);

		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
		Cliente cliente = this.buscarPorId(id);
		if (cliente == null)
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);

		this.excluir(cliente);

		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}

}
