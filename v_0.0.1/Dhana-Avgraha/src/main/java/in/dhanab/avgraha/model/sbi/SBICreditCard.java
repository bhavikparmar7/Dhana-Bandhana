package in.dhanab.avgraha.model.sbi;

import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.CreditCardStatement;
import in.dhanab.avgraha.model.base.CreditCard;

public class SBICreditCard extends CreditCard {

    @Override
    public CreditCardStatement extractStatement(MultipartFile statementFile) {


        return null;
    }
}
