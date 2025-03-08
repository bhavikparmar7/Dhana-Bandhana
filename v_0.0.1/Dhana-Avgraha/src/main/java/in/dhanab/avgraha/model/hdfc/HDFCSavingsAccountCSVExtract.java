package in.dhanab.avgraha.model.hdfc;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import in.dhanab.avgraha.collections.SavingsAccountStatement;
import in.dhanab.avgraha.model.base.SavingsAccount;
import in.dhanab.avgraha.model.base.SavingsAccountStatementExtractionStrategy;

public class HDFCSavingsAccountCSVExtract implements SavingsAccountStatementExtractionStrategy {

    @Override
    public SavingsAccountStatement extractStatementFromFile(MultipartFile file) {

        SavingsAccountStatement statement = new SavingsAccountStatement();

        List<SavingsAccountStatement.AccountTransaction> transactionList = new ArrayList<>();

        try {
            Reader in = new InputStreamReader(file.getInputStream());
            Iterable<CSVRecord> records = null;

            records = CSVFormat.EXCEL.parse(in);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            for (CSVRecord record : records) {
                if (record.getRecordNumber() != 1 && record.getRecordNumber() !=2) {
                    SavingsAccountStatement.AccountTransaction transaction = new SavingsAccountStatement.AccountTransaction();
                    Float debitAmount = Float.parseFloat(record.get(3).trim());
                    Float creditAmount = Float.parseFloat(record.get(4).trim());
                    transaction.setAmount(creditAmount == 0 ? debitAmount*(-1) : creditAmount);
                    transaction.setDescription(record.get(1).trim());
                    transaction.setDate(LocalDate.parse(record.get(2).trim(), formatter));
                    transactionList.add(transaction);

                    if (record.getRecordNumber() == 3) {
                        statement.setFromDate(transaction.getDate());
                        statement.setOpeningBalance(Float.parseFloat(record.get(6)) - transaction.getAmount());
                    } else if (!records.iterator().hasNext()) {
                        statement.setToDate(transaction.getDate());
                        statement.setClosingBalance(Float.parseFloat(record.get(6)));
                    }

                }
            }



            statement.setAccountTransactionList(transactionList);
            System.out.println(statement);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
