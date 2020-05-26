package br.com.PlatformBuilders.PlatformBuildersApi.endpoint.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;



import br.com.PlatformBuilders.PlatformBuildersApi.models.Cliente;
import br.com.PlatformBuilders.PlatformBuildersApi.util.CalcularIdade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
	private Long id;
	
	private String nome;
	private String cpf;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private Integer idade;
	
	
	
	public Cliente format() {
		
		this.idade = CalcularIdade.idadeCliente(this);
		
		return Cliente.builder()
				.id(id)
				.nome(nome)
				.cpf(cpf)
				.idade(idade)
				.dataNascimento(dataNascimento)
				.build();
	}
}
