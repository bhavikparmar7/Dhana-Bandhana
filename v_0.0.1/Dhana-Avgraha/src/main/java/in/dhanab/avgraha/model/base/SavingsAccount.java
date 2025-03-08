package in.dhanab.avgraha.model.base;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.CreditCardStatement;
import in.dhanab.avgraha.collections.SavingsAccountStatement;

public abstract class SavingsAccount {

    public abstract SavingsAccountStatement extractStatement(MultipartFile statementFile);

}
