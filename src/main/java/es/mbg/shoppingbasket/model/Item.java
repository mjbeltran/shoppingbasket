package es.mbg.shoppingbasket.model;

public class Item {

	public Product product;
	public long quantity;

	public Item(Product product, long quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (!this.getProduct().equals(other.getProduct())) {
			return false;
		}
		if (this.getQuantity() != other.getQuantity()) {
			return false;
		}

		return true;
	}

}
