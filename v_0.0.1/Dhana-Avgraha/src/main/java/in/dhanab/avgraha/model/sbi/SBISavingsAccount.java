package in.dhanab.avgraha.model.sbi;

import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.SavingsAccountStatement;
import in.dhanab.avgraha.model.base.SavingsAccount;

public class SBISavingsAccount extends SavingsAccount {

    @Override
    public SavingsAccountStatement extractStatement(MultipartFile statementFile) {
        return null;
    }
}
