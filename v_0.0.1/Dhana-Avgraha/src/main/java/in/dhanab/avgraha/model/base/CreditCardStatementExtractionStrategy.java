package in.dhanab.avgraha.model.base;

import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.CreditCardStatement;

public interface CreditCardStatementExtractionStrategy {

    CreditCardStatement extractStatementFromFile(MultipartFile statementFile);
}
