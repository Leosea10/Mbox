public class Transaction {
    private String accountNumber;
    private double amount;
    private int hour;

    public Transaction(String accountNumber, double amount, int hour) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.hour = hour;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public int getHour() {
        return hour;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", hour=" + hour +
                '}';
    }
}
