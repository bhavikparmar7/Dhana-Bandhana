package in.dhanab.avgraha.model.hdfc;

import in.dhanab.avgraha.model.base.BankFactory;
import in.dhanab.avgraha.model.base.CreditCard;
import in.dhanab.avgraha.model.base.SavingsAccount;

public class HDFCBank implements BankFactory {

    @Override
    public CreditCard createCreditCard() {
        return new HDFCCreditCard();
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        return new HDFCSavingsAccount();
    }
}
