package br.com.PlatformBuilders.PlatformBuildersApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.PlatformBuilders.PlatformBuildersApi.models.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByCpf(String cpf);
	List<Cliente> findByNomeIgnoreCaseContaining(String nome);
	

}
