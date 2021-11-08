package com.devsuperior.bds04.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);//envia o argumento para o construtor da super série
	}

}
