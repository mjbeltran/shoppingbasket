package es.mbg.shoppingbasket.business;

import es.mbg.shoppingbasket.domain.ReturnTreatment;
import es.mbg.shoppingbasket.model.Product;
import es.mbg.shoppingbasket.model.User;

public interface ShoppingBasket {

	User addProductToUser(User user, Product product, int quantity);

	User removeProductToUser(User user, Product product);

	double getTotalPriceBasket(User user);

	ReturnTreatment paymentShoppingBasket(User user);
}
