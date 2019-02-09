package es.mbg.shoppingbasket.apimock.impl;

import org.apache.commons.lang3.StringUtils;

import es.mbg.shoppingbasket.apimock.PaymentGateWayI;
import es.mbg.shoppingbasket.constants.Constants;
import es.mbg.shoppingbasket.domain.PaymentOrder;
import es.mbg.shoppingbasket.domain.PaymentReturn;
import es.mbg.shoppingbasket.exception.PaymentFailureException;

public class PaymentMockImpl implements PaymentGateWayI {

	@Override
	public PaymentReturn makePayment(PaymentOrder paymentOrder) throws PaymentFailureException {
		if (StringUtils.isNotBlank(paymentOrder.getBankAccountNumber())) {
			return new PaymentReturn(Constants.PAYMENT_SUCCESSFULL);
		} else {
			throw new PaymentFailureException(Constants.USER_FAIL_CREDIT_CARD);
		}

	}

}
