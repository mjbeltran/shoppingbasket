package es.mbg.shoppingbasket.exception;

public class PaymentFailureException extends Exception {

	/**
	* 
	*/
	private static final long serialVersionUID = 4203705571432372733L;

	public PaymentFailureException(String message) {
		super(message);
	}
}
