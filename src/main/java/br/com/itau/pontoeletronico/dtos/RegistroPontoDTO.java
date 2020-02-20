package br.com.itau.pontoeletronico.dtos;

import javax.validation.constraints.NotNull;

import br.com.itau.pontoeletronico.models.TipoRegistro;

public class RegistroPontoDTO {
	
	@NotNull
	private Integer usuarioId;
	
	@NotNull
	private TipoRegistro tipoRegistro;

	public Integer getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

}
