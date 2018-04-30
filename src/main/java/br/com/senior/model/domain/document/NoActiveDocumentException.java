package br.com.senior.model.domain.document;

public class NoActiveDocumentException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoActiveDocumentException() {
		this("Não existe uma venda ativa.");
	}
	
	public NoActiveDocumentException(String message) {
		super(message);
	}

}
