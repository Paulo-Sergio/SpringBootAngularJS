package br.com.fabricaprogramador.ws.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JPARepository<Cliente, Integer> {
	
	
}
