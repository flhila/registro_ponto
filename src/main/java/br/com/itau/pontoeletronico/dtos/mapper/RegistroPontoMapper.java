package br.com.itau.pontoeletronico.dtos.mapper;

import java.time.LocalDateTime;

import br.com.itau.pontoeletronico.dtos.RegistroPontoDTO;
import br.com.itau.pontoeletronico.models.RegistroPonto;

public class RegistroPontoMapper {

	public static RegistroPonto from(RegistroPontoDTO registroPontoDto) {
		RegistroPonto registroPonto = new RegistroPonto();
		registroPonto.setTipoRegistro(registroPontoDto.getTipoRegistro());
		registroPonto.setDataHora(LocalDateTime.now());
		return registroPonto;
	}

}
