/**
 * @author rtenorio
 */
package br.com.PlatformBuilders.PlatformBuildersApi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.PlatformBuilders.PlatformBuildersApi.endpoint.dto.ClienteDTO;
import br.com.PlatformBuilders.PlatformBuildersApi.erros.ResourceNotFoundException;
import br.com.PlatformBuilders.PlatformBuildersApi.models.Cliente;
import br.com.PlatformBuilders.PlatformBuildersApi.service.ClienteService;
@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "/v1/clientes")
@RestController
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault(page = 0 ,size = 2) Pageable pageable){
		Page<Cliente> cliPage = clienteService.findAll(pageable);
		return new ResponseEntity<>(cliPage, HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> createCliente(@RequestBody @Valid ClienteDTO clienteDTO){
		Cliente cliente = clienteService.save(clienteDTO);
		return new ResponseEntity<>(cliente, HttpStatus.CREATED);		
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
		verifyIfClientExists(id);
		clienteService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PatchMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> partialChange(@RequestBody @Valid ClienteDTO  clienteDTO){
		verifyIfClientExists(clienteDTO.getId());
	    Cliente cliente =	clienteService.partialChange(clienteDTO);
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}
	
	@PutMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> updateClient( @RequestBody @Valid Cliente cliente){
		verifyIfClientExists(cliente.getId());
		cliente = clienteService.update(cliente);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "cpf/{cpf}")
	public ResponseEntity<?> findByCpf(@PathVariable("cpf") String cpf){
		Cliente cliente = clienteService.findByCpf(cpf);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "nome/{nome}")
	public ResponseEntity<?> findByNome(@PathVariable("nome") String nome ){
		List<Cliente> clientes= clienteService.findByNomeIgnoreCaseContaining(nome);
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getByIdClient(@PathVariable("id") Long id){
		verifyIfClientExists(id);
		Cliente cliente = clienteService.getByidCliente(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	private void verifyIfClientExists(Long id) {
		Optional<Cliente> cliente = clienteService.findById(id);
		if(! cliente.isPresent() ) {
			throw new ResourceNotFoundException("Cliente not found for ID  "+ id);
		}
	}
	
	
	
	

}
