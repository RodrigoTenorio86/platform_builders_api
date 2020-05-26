package br.com.PlatformBuilders.PlatformBuildersApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.PlatformBuilders.PlatformBuildersApi.endpoint.dto.ClienteDTO;
import br.com.PlatformBuilders.PlatformBuildersApi.models.Cliente;

public interface ClienteService {

	Page<Cliente> findAll(Pageable pageable);

	Cliente save(ClienteDTO clienteDTO);

	void deleteById(Long id);

	Cliente partialChange(ClienteDTO clienteDTO);

	Cliente update(Cliente cliente);
	
	
	Cliente findByCpf(String cpf);
	
	List<Cliente> findByNomeIgnoreCaseContaining(String nome);

	Optional<Cliente> findById(Long id);

	Cliente getByidCliente(Long id);

}
