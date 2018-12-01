package es.mbg.shoppingbasket.apimock.impl;

import es.mbg.shoppingbasket.apimock.StockApiI;
import es.mbg.shoppingbasket.model.Product;

public class StockApiImpl implements StockApiI {

	@Override
	public long getStock(Product product) {
		// Llamada sistema externo
		return 0;

	}
}
