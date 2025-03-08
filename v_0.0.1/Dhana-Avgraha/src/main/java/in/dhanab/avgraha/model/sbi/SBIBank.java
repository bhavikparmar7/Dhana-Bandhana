package in.dhanab.avgraha.model.sbi;

import in.dhanab.avgraha.model.base.BankFactory;
import in.dhanab.avgraha.model.base.CreditCard;
import in.dhanab.avgraha.model.base.SavingsAccount;

public class SBIBank implements BankFactory {


    @Override
    public CreditCard createCreditCard() {
        return new SBICreditCard();
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        return new SBISavingsAccount();
    }
}
