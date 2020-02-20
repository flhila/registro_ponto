package br.com.itau.pontoeletronico.presenter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.itau.pontoeletronico.models.RegistroPonto;
import br.com.itau.pontoeletronico.models.TipoRegistro;

public class RegistroPontoPresenter {
	
	private Integer id;
	
	private UsuarioPresenter usuario;
	
	private TipoRegistro tipoRegistro;
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataHora;
	
	public RegistroPontoPresenter(RegistroPonto registroPonto) {
		this.id = registroPonto.getId();
		this.tipoRegistro = registroPonto.getTipoRegistro();
		this.usuario = new UsuarioPresenter(registroPonto.getUsuario());
		this.dataHora = registroPonto.getDataHora();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioPresenter getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPresenter usuario) {
		this.usuario = usuario;
	}

	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}
