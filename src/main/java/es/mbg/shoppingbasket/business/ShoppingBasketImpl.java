package es.mbg.shoppingbasket.business;

import java.util.ArrayList;
import java.util.List;

import es.mbg.shoppingbasket.apimock.PaymentGateWayI;
import es.mbg.shoppingbasket.apimock.StockApiI;
import es.mbg.shoppingbasket.constants.Constants;
import es.mbg.shoppingbasket.domain.PaymentOrder;
import es.mbg.shoppingbasket.domain.PaymentReturn;
import es.mbg.shoppingbasket.domain.ReturnTreatment;
import es.mbg.shoppingbasket.exception.PaymentFailureException;
import es.mbg.shoppingbasket.model.Item;
import es.mbg.shoppingbasket.model.Product;
import es.mbg.shoppingbasket.model.User;

/**
 * 
 * @author Manuel
 *
 */
public class ShoppingBasketImpl implements ShoppingBasket {

	private StockApiI stockApi;
	private PaymentGateWayI paymentGateWay;

	@Override
	public ReturnTreatment paymentShoppingBasket(User user) {

		ReturnTreatment returnTreatment = new ReturnTreatment();

		List<Item> listNotInStock = new ArrayList<>();
		for (Item item : user.getShoppingCart().getListItems()) {
			if (stockApi.getStock(item.getProduct()) < item.getQuantity()) {
				Item itemAux = new Item(item.getProduct(), item.getQuantity() - stockApi.getStock(item.getProduct()));
				listNotInStock.add(itemAux);
			}
		}
		if (listNotInStock.isEmpty()) {
			returnTreatment.setPaymentReturn(preparePayment(user));
		} else {
			returnTreatment.setListItemNotInStock(listNotInStock);
		}
		return returnTreatment;

	}

	private PaymentReturn preparePayment(User user) {
		PaymentReturn returnPayment = null ;
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setBankAccountNumber(user.getBankAccountNumber());
		paymentOrder.setUserMandat(user.getMandat());
		paymentOrder.setValueToPay(user.getShoppingCart().getTotalCart());

		try {
			returnPayment = paymentGateWay.makePayment(paymentOrder);
		} catch (PaymentFailureException e) {
			returnPayment = new PaymentReturn(Constants.USER_FAIL_CREDIT_CARD);
		}
		return returnPayment;
	}

	@Override
	public User addProductToUser(User user, Product product, int quantity) {
		Item item = new Item(product, quantity);
		user.getShoppingCart().getListItems().add(item);
		return user;
	}

	@Override
	public User removeProductToUser(User user, Product product) {

		Item itemToDelete = user.getShoppingCart().getItemBytProductByName(product);
		if (itemToDelete != null && itemToDelete.getQuantity() > 0) {
			user.getShoppingCart().getItemBytProductByName(product)
					.setQuantity(itemToDelete.getQuantity() - Constants.SUBTRACT_NUM_PRODUCT_1);
		}
		return user;
	}

	@Override
	public double getTotalPriceBasket(User user) {

		return user.getShoppingCart().getTotalCart();
	}

	public StockApiI getStockApi() {
		return stockApi;
	}

	public void setStockApi(StockApiI stockApi) {
		this.stockApi = stockApi;
	}

	public PaymentGateWayI getPaymentGateWay() {
		return paymentGateWay;
	}

	public void setPaymentGateWay(PaymentGateWayI paymentGateWay) {
		this.paymentGateWay = paymentGateWay;
	}
}
