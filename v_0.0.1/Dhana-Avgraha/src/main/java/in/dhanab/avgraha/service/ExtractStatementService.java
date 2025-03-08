package in.dhanab.avgraha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.CreditCardStatement;
import in.dhanab.avgraha.collections.SavingsAccountStatement;
import in.dhanab.avgraha.enums.Banks;
import in.dhanab.avgraha.enums.Products;
import in.dhanab.avgraha.model.base.BankFactory;
import in.dhanab.avgraha.model.base.CreditCard;
import in.dhanab.avgraha.model.base.SavingsAccount;
import in.dhanab.avgraha.model.hdfc.HDFCBank;
import in.dhanab.avgraha.repository.CreditCardStatementRepo;
import in.dhanab.avgraha.repository.SavingsAccountStatementRepo;

@Service
public class ExtractStatementService {

    @Autowired
    private CreditCardStatementRepo creditCardStatementRepo;

    @Autowired
    private SavingsAccountStatementRepo savingsAccountStatementRepo;

    public void uploadAndExtractStatement(MultipartFile file, Banks bankName, Products productName) {
        BankFactory bankFactory = null;
        CreditCard creditCard = null;
        SavingsAccount savingsAccount = null;

        if (bankName.equals(Banks.HDFC)) {
            bankFactory = new HDFCBank();
        } else if (bankName.equals(Banks.SC)) {
            bankFactory = new HDFCBank();
        } else if (bankName.equals(Banks.SBI)) {
            bankFactory = new HDFCBank();
        } else if (bankName.equals(Banks.BOB)) {
            bankFactory = new HDFCBank();
        } else if (bankName.equals(Banks.KOTAK)) {
            bankFactory = new HDFCBank();
        }

        if (productName.equals(Products.CreditCard)) {
            creditCard = bankFactory.createCreditCard();
            CreditCardStatement statement = creditCard.extractStatement(file);
            creditCardStatementRepo.save(statement);
        } else if (productName.equals(Products.SavingsAccount)) {
            savingsAccount = bankFactory.createSavingsAccount();
            SavingsAccountStatement statement = savingsAccount.extractStatement(file);
            savingsAccountStatementRepo.save(statement);
        }


    }
}
