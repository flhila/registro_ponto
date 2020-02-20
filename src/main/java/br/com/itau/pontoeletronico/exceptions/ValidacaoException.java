package br.com.itau.pontoeletronico.exceptions;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String atributo;
	  
	  public String getAtributo() {
	    return atributo;
	  }
	  
	  public ValidacaoException(String atributo, String message) {
	    super(message);
	    this.atributo = atributo;
	  }

}
