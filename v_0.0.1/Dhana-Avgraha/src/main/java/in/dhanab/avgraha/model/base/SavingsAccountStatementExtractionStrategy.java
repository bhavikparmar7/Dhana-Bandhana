package in.dhanab.avgraha.model.base;

import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.SavingsAccountStatement;

public interface SavingsAccountStatementExtractionStrategy {

    SavingsAccountStatement extractStatementFromFile(MultipartFile file);
}
