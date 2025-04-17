package me.dionclei.ecommerce_order_service.exceptions;

public class InventoryException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InventoryException(String message) {
		super(message);
	}

}
