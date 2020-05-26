package br.com.PlatformBuilders.PlatformBuildersApi.util;

import java.time.LocalDate;
import java.time.Period;

import br.com.PlatformBuilders.PlatformBuildersApi.endpoint.dto.ClienteDTO;

public class CalcularIdade {
	
	public static int idadeCliente(ClienteDTO cliente) {
		return Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears();
	}

}
