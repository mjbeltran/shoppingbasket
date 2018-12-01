package es.mbg.shoppingbasket.apimock;

import es.mbg.shoppingbasket.model.Product;

public interface StockApiI {
	
	long getStock(Product product);
	
}
