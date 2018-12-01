package es.mbg.shoppingbasket.apimock;

import es.mbg.shoppingbasket.domain.PaymentOrder;
import es.mbg.shoppingbasket.domain.PaymentReturn;

public interface PaymentGateWayI {

	PaymentReturn makePayment(PaymentOrder payment);

}
