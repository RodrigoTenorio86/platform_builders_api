package br.com.PlatformBuilders.PlatformBuildersApi.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.PlatformBuilders.PlatformBuildersApi.models.Cliente;
import br.com.PlatformBuilders.PlatformBuildersApi.service.ClienteService;
@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "/clientes")
@RestController
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault(page = 0 ,size = 2) Pageable pageable){
		Page<Cliente> cliPage = clienteService.findAll(pageable);
		return new ResponseEntity<>(cliPage, HttpStatus.OK);
	}

}
