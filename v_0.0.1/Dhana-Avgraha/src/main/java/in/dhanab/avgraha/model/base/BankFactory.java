package in.dhanab.avgraha.model.base;

public interface BankFactory {

    CreditCard createCreditCard();

    SavingsAccount createSavingsAccount();
}
