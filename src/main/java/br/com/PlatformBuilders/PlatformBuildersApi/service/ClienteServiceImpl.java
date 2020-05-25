package br.com.PlatformBuilders.PlatformBuildersApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.PlatformBuilders.PlatformBuildersApi.models.Cliente;
import br.com.PlatformBuilders.PlatformBuildersApi.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	
	private ClienteRepository clienteRepository;
	
	
	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}



	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

}
