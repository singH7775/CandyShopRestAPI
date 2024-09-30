package com.CandyShop.CandyShop.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(Class<?> entity, Long id) {
		super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
	}

}
