package br.com.itau.pontoeletronico.models;

public enum TipoRegistro {
	
	ENTRADA("Entrada"),
	SAIDA("Saída");

	private String tipo;
	
	private TipoRegistro(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}
