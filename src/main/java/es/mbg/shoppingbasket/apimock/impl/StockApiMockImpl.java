package es.mbg.shoppingbasket.apimock.impl;

import java.util.Map;

import es.mbg.shoppingbasket.apimock.StockApiI;
import es.mbg.shoppingbasket.model.Product;
import es.mbg.shoppingbasket.utils.StockHelper;

public class StockApiMockImpl implements StockApiI {

	private Map<Product, Integer> stocks;

	@Override
	public long getStock(Product product) {
		if (stocks == null) {
			stocks = StockHelper.iniMockApiStock();
		}
		if (stocks.containsKey(product)) {
			return stocks.get(product).intValue();
		} else {
			return 0;
		}
	}

}
