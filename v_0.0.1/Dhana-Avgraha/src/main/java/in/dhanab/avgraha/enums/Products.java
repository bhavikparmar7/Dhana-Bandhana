package in.dhanab.avgraha.enums;

public enum Products {

    CreditCard("Credit Card"),
    SavingsAccount("Savings Account");

    private String productName;

    Products(String productName) {
        this.productName = productName;
    }

    public String getProduct() {
        return productName;
    }
}

