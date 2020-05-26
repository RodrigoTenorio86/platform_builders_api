package br.com.PlatformBuilders.PlatformBuildersApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.PlatformBuilders.PlatformBuildersApi.endpoint.dto.ClienteDTO;
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



	@Override
	public Cliente save(ClienteDTO clienteDTO) {
		Cliente cliente = clienteDTO.format();		
		return clienteRepository.save(cliente);
	}



	@Override
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);		
	}



	@Override
	public Cliente partialChange(ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.getOne(clienteDTO.getId());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setNome(clienteDTO.getNome());
		cliente.setIdade(clienteDTO.getIdade());
		return cliente;
	}



	@Override
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}



	@Override
	public Cliente findByCpf(String cpf) {
		Cliente cliente = clienteRepository.findByCpf(cpf);
		return cliente;
	}



	@Override
	public List<Cliente> findByNome(String nome) {
		List<Cliente> clientes = clienteRepository.findByNome(nome);
		return clientes;
	}



	@Override
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}



	@Override
	public Cliente getByidCliente(Long id) {
		return clienteRepository.getOne(id);
	}

}
