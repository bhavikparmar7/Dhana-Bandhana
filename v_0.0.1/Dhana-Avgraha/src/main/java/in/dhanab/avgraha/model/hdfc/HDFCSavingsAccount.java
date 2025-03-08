package in.dhanab.avgraha.model.hdfc;

import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.SavingsAccountStatement;
import in.dhanab.avgraha.model.base.SavingsAccount;
import in.dhanab.avgraha.model.base.SavingsAccountStatementExtractionStrategy;

public class HDFCSavingsAccount extends SavingsAccount {

    private SavingsAccountStatementExtractionStrategy extractionStrategy;


    @Override
    public SavingsAccountStatement extractStatement(MultipartFile statementFile) {

        if (statementFile.getContentType().equals("text/csv")) {
            extractionStrategy = new HDFCSavingsAccountCSVExtract();
        } else {
            return null;
        }

        return extractionStrategy.extractStatementFromFile(statementFile);
    }
}
