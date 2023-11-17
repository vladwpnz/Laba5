public class BankTest {
    public static void main(String[] args) {
        try {
            Bank bank = new Bank();

            BankAccount account1 = bank.createAccount("Вася", 1000);
            BankAccount account2 = bank.createAccount("Петя", 500);

            System.out.println(account1.getAccountSummary());
            System.out.println(account2.getAccountSummary());

            bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), 200);

            System.out.println("Після передачі:");
            System.out.println(account1.getAccountSummary());
            System.out.println(account2.getAccountSummary());

        } catch (NegativeAmountException | InsufficientFundsException | AccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}
