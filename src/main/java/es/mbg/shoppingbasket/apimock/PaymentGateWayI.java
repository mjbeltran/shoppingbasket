package es.mbg.shoppingbasket.apimock;

import es.mbg.shoppingbasket.domain.PaymentOrder;
import es.mbg.shoppingbasket.domain.PaymentReturn;
import es.mbg.shoppingbasket.exception.PaymentFailureException;

public interface PaymentGateWayI {

	PaymentReturn makePayment(PaymentOrder payment) throws PaymentFailureException;

}
