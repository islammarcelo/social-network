public class CreditCard {
    private long creditCardNumber;
    private double balance;

    CreditCard(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        this.balance = 150;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
