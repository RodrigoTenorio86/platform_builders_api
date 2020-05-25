package br.com.PlatformBuilders.PlatformBuildersApi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.PlatformBuilders.PlatformBuildersApi.models.Cliente;

public interface ClienteService {

	Page<Cliente> findAll(Pageable pageable);

}
