package es.mbg.shoppingbasket.utils;

import java.util.HashMap;
import java.util.Map;

import es.mbg.shoppingbasket.model.Product;

public class StockHelper {

	public static Map<Product, Integer> iniMockApiStock() {
		Map<Product, Integer> mapStock = new HashMap<>();
		Product product = new Product("Bicicleta Orbea Master", 600.99);
		mapStock.put(product, 1);

		product = new Product("PlayStation", 300);
		mapStock.put(product, 1);

		product = new Product("Jean", 20);
		mapStock.put(product, 1);
		return mapStock;

	}

}
