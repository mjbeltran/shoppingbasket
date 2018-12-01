package es.mbg.shoppingbasket.domain;

public class PaymentReturn {

	private String strReturnPayment;

	public PaymentReturn(String strReturnPayment) {
		this.strReturnPayment = strReturnPayment;

	}

	public String getStrReturnPayment() {
		return strReturnPayment;
	}

	public void setStrReturnPayment(String strReturnPayment) {
		this.strReturnPayment = strReturnPayment;
	}

}
