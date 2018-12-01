package es.mbg.shoppingbasket.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private List<Item> listItems;

	public ShoppingCart() {
		this.listItems = new ArrayList<>();
	}

	public List<Item> getListItems() {
		return listItems;
	}

	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}

	public double getTotalCart() {
		double totalPrice = listItems.stream().mapToDouble(o -> o.getProduct().getPrice() * o.getQuantity()).sum();
		return totalPrice;
	}

	public Item getItemBytProductByName(Product prod) {

		Item item = listItems.stream().filter(x -> prod.getName().equals(x.getProduct().getName())).findAny()
				.orElse(null);

		return item;
	}

}
