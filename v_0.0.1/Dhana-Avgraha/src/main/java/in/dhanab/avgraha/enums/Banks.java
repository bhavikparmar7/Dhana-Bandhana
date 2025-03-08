package in.dhanab.avgraha.enums;

public enum Banks {

    HDFC("HDFC"),
    SC("SC"),
    SBI("SBI"),
    BOB("BOB"),
    KOTAK("KOTAK");

    private String bankName;

    Banks(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }
}
