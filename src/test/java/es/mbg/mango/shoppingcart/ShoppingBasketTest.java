package es.mbg.mango.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import es.mbg.shoppingbasket.apimock.PaymentGateWayI;
import es.mbg.shoppingbasket.apimock.StockApiI;
import es.mbg.shoppingbasket.apimock.impl.PaymentMockImpl;
import es.mbg.shoppingbasket.apimock.impl.PaymentPayPalImpl;
import es.mbg.shoppingbasket.apimock.impl.StockApiImpl;
import es.mbg.shoppingbasket.apimock.impl.StockApiMockImpl;
import es.mbg.shoppingbasket.business.ShoppingBasketImpl;
import es.mbg.shoppingbasket.constants.Constants;
import es.mbg.shoppingbasket.domain.PaymentOrder;
import es.mbg.shoppingbasket.domain.PaymentReturn;
import es.mbg.shoppingbasket.domain.ReturnTreatment;
import es.mbg.shoppingbasket.exception.PaymentFailureException;
import es.mbg.shoppingbasket.model.Item;
import es.mbg.shoppingbasket.model.Product;
import es.mbg.shoppingbasket.model.User;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketTest {

	private ShoppingBasketImpl shoppingBasket;
	private StockApiI stockApiMock;
	private PaymentGateWayI paymentGateWayMock;
	private User user;

	@Before
	public void setUp() {

		stockApiMock = Mockito.mock(StockApiImpl.class);
		paymentGateWayMock = Mockito.mock(PaymentPayPalImpl.class);
		shoppingBasket = new ShoppingBasketImpl();
		shoppingBasket.setStockApi(stockApiMock);
		shoppingBasket.setPaymentGateWay(paymentGateWayMock);

		user = new User("Test");
		Product product = new Product("Bicicleta Orbea Master", 600.99);
		user = shoppingBasket.addProductToUser(user, product, 1);
		product = new Product("PlayStation", 300);
		user = shoppingBasket.addProductToUser(user, product, 1);
		product = new Product("Jean", 20);
		user = shoppingBasket.addProductToUser(user, product, 1);
		// shoppingBasket = new ShoppingBasketImpl();
	}

	@Test
	public void testPaymentShoppingBasket() {

		ReturnTreatment returnPayment = shoppingBasket.paymentShoppingBasket(user);
		assertNotNull(returnPayment);
	}

	@Test
	public void testPaymentShoppingBasketReturnStock() {

		when(stockApiMock.getStock(new Product("Bicicleta Orbea Master", 0.99))).thenReturn(1L);
		ReturnTreatment returnPayment = shoppingBasket.paymentShoppingBasket(user);
		assertNotNull(returnPayment);
	}

	@Test
	public void testBeforeTreatment() {
		assertTrue(user.getShoppingCart().getListItems().size() > 0);
	}

	@Test
	public void testTotalPrice() {
		assertEquals(920.99, shoppingBasket.getTotalPriceBasket(user), 0.01);
	}

	@Test
	public void testVerifyStock() {
		this.user.setBankAccountNumber("168728378127391239123");

		PaymentReturn returnPay = new PaymentReturn(Constants.PAYMENT_SUCCESSFULL);
		when(stockApiMock.getStock(any(Product.class))).thenReturn(1L);
		when(paymentGateWayMock.makePayment(any(PaymentOrder.class))).thenReturn(returnPay);
		assertEquals(Constants.PAYMENT_SUCCESSFULL,
				shoppingBasket.paymentShoppingBasket(user).getPaymentReturn().getStrReturnPayment());
	}

	@Test(expected = PaymentFailureException.class)
	public void testNotCreditCard() throws Exception {
		shoppingBasket.setStockApi(new StockApiMockImpl());
		shoppingBasket.setPaymentGateWay(new PaymentMockImpl());
		assertEquals(Constants.PAYMENT_SUCCESSFULL,
				shoppingBasket.paymentShoppingBasket(user).getPaymentReturn().getStrReturnPayment());
	}

	@Test
	public void testMoreItemsThanInStock() {

		Product product = new Product("Portatil Vaio", 1500);
		Item item = new Item(product, 3);
		user.getShoppingCart().getListItems().add(item);
		assertTrue(shoppingBasket.paymentShoppingBasket(user).getListItemNotInStock().contains(item));
	}

	@Test
	public void testFiveJeansMore() {
		shoppingBasket.setStockApi(new StockApiMockImpl());
		shoppingBasket.setPaymentGateWay(new PaymentMockImpl());
		for (Item item : user.getShoppingCart().getListItems()) {
			if (item.getProduct().getName().equals("Jean")) {
				item.setQuantity(6);
			}
		}
		assertEquals(5, shoppingBasket.paymentShoppingBasket(user).getListItemNotInStock().get(0).getQuantity());
	}

	@Test
	public void testRemoveProductToUser() {

		Product product = new Product("Jean", 20);
		shoppingBasket.removeProductToUser(user, product);
		assertEquals(0, user.getShoppingCart().getItemBytProductByName(product).getQuantity());
	}

}
