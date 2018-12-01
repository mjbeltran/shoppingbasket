package es.mbg.shoppingbasket.domain;

import java.io.Serializable;

public class PaymentOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2377065637827118160L;
	private String userMandat;
	private String bankAccountNumber;
	private double valueToPay;

	public String getUserMandat() {
		return userMandat;
	}

	public void setUserMandat(String userMandat) {
		this.userMandat = userMandat;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public double getValueToPay() {
		return valueToPay;
	}

	public void setValueToPay(double valueToPay) {
		this.valueToPay = valueToPay;
	}

}
