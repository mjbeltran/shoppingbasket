package es.mbg.shoppingbasket.model;

public class User {

	private String name;
	private ShoppingCart shoppingCart;
	private String bankAccountNumber;
	private String mandat;

	public User(String name) {
		this.name = name;
		this.shoppingCart = new ShoppingCart();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getMandat() {
		return mandat;
	}

	public void setMandat(String mandat) {
		this.mandat = mandat;
	}

}
