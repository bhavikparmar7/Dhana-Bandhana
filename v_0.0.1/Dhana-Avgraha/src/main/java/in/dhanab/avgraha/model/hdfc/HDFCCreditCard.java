package in.dhanab.avgraha.model.hdfc;

import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.CreditCardStatement;
import in.dhanab.avgraha.model.base.CreditCard;
import in.dhanab.avgraha.model.base.CreditCardStatementExtractionStrategy;

public class HDFCCreditCard extends CreditCard {

    private CreditCardStatementExtractionStrategy extractionStrategy;


    @Override
    public CreditCardStatement extractStatement(MultipartFile statementFile) {

        if (statementFile.getContentType().equals("application/pdf")) {
            extractionStrategy = new HDFCCreditCardPDFExtract();
        } else {
            return null;
        }

        return extractionStrategy.extractStatementFromFile(statementFile);
    }
}
