package in.dhanab.avgraha.collections;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("creditcardstatement")
public class CreditCardStatement {

    private Integer last4digits;

    private LocalDate statementDate;

    private Float totalPaymentDue;

    private LocalDate paymentDueDate;

    private List<CardTransaction> cardTransactionList;

    public Integer getLast4digits() {
        return last4digits;
    }

    public void setLast4digits(Integer last4digits) {
        this.last4digits = last4digits;
    }

    public LocalDate getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(LocalDate statementDate) {
        this.statementDate = statementDate;
    }

    public Float getTotalPaymentDue() {
        return totalPaymentDue;
    }

    public void setTotalPaymentDue(Float totalPaymentDue) {
        this.totalPaymentDue = totalPaymentDue;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public List<CardTransaction> getCardTransactionList() {
        return cardTransactionList;
    }

    public void setCardTransactionList(List<CardTransaction> cardTransactionList) {
        this.cardTransactionList = cardTransactionList;
    }

    public static class CardTransaction {
        private LocalDate date;

        private String description;

        private Float amount;

        public CardTransaction(LocalDate date) {
            this.date = date;
        }

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
            return "CardTransaction{" +
                    "date=" + date +
                    ", description='" + description + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "CreditCardStatement{" +
                "last4digits=" + last4digits +
                ", statementDate=" + statementDate +
                ", totalPaymentDue=" + totalPaymentDue +
                ", paymentDueDate=" + paymentDueDate +
                ", cardTransactionList=" + cardTransactionList +
                '}';
    }
}
