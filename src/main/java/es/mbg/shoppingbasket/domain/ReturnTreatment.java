package es.mbg.shoppingbasket.domain;

import java.util.List;

import es.mbg.shoppingbasket.model.Item;

public class ReturnTreatment {

	private PaymentReturn paymentReturn;
	
	private List<Item> listItemNotInStock;

	public PaymentReturn getPaymentReturn() {
		return paymentReturn;
	}

	public void setPaymentReturn(PaymentReturn paymentReturn) {
		this.paymentReturn = paymentReturn;
	}

	public List<Item> getListItemNotInStock() {
		return listItemNotInStock;
	}

	public void setListItemNotInStock(List<Item> listItemNotInStock) {
		this.listItemNotInStock = listItemNotInStock;
	}
	
	
	
}
