package in.dhanab.avgraha.collections;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("savingsaccountstatement")
public class SavingsAccountStatement {

    private String accountNumber;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Float openingBalance;

    private Float closingBalance;

    private List<AccountTransaction> accountTransactionList;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Float getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Float openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Float getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Float closingBalance) {
        this.closingBalance = closingBalance;
    }

    public List<AccountTransaction> getAccountTransactionList() {
        return accountTransactionList;
    }

    public void setAccountTransactionList(List<AccountTransaction> accountTransactionList) {
        this.accountTransactionList = accountTransactionList;
    }

    public static class AccountTransaction {
        private LocalDate date;

        private String description;

        private Float amount;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Float getAmount() {
            return amount;
        }

        public void setAmount(Float amount) {
            this.amount = amount;
        }


        @Override
        public String toString() {
            return "AccountTransaction{" +
                    "date=" + date +
                    ", description='" + description + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SavingsAccountStatement{" +
                "accountNumber='" + accountNumber + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", openingBalance=" + openingBalance +
                ", closingBalance=" + closingBalance +
                ", accountTransactionList=" + accountTransactionList +
                '}';
    }
}
