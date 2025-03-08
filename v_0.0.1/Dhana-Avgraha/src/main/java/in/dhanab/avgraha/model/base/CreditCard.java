package in.dhanab.avgraha.model.base;

import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.CreditCardStatement;

public abstract class CreditCard {

    public abstract CreditCardStatement extractStatement(MultipartFile statementFile);
}
