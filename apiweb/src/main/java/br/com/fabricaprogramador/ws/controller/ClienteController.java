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
import br.com.fabricaprogramador.ws.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	// End Points
	@RequestMapping(method = RequestMethod.POST, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		Cliente clienteCadastrado = clienteService.cadastrar(cliente);

		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {
		Collection<Cliente> clientes = clienteService.buscarTodos();

		return new ResponseEntity<Collection<Cliente>>(clientes, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {
		Cliente clienteAlterado = clienteService.alterar(cliente);

		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente == null)
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);

		clienteService.excluir(cliente);

		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}

}
