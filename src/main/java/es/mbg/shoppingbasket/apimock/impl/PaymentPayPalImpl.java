package es.mbg.shoppingbasket.apimock.impl;

import es.mbg.shoppingbasket.apimock.PaymentGateWayI;
import es.mbg.shoppingbasket.constants.Constants;
import es.mbg.shoppingbasket.domain.PaymentOrder;
import es.mbg.shoppingbasket.domain.PaymentReturn;

public class PaymentPayPalImpl implements PaymentGateWayI {

	@Override
	public PaymentReturn makePayment(PaymentOrder paymentOrder) {

		// llamada paypalpayment
		return new PaymentReturn(Constants.PAYMENT_SUCCESSFULL);
	}

}
