import java.util.HashMap;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ATM {
    static HashMap<String, Double> accounts;

    public ATM() {
        accounts = new HashMap<String, Double>();
    }

    public void openAccount(String userID, double initAmt) throws IOException {
        if (accounts.containsKey(userID)) {
            throw new IOException("There's already an account w/that email, sorry.");
        } else if (initAmt < 0) {
            throw new IOException("can't start an account with a negative balance dude");
        } else {
            accounts.put(userID, initAmt);
        }
    }

    public void closeAccount(String userID) throws IOException {
        if (!accounts.containsKey(userID)) {
            throw new IOException("There isn't an account associated with that username, dumba**. Sorry!");
        }
        if (accounts.get(userID) > 0) {
            throw new IOException("There's still money in this account! Don't close it yet!");
        } else {
            accounts.remove(userID);
            System.out.println("Account with username " + userID + " was removed.");
        }
    }

    public double checkBalance(String userID) throws IOException {
        if (!accounts.containsKey(userID)) {
            throw new IOException("There isn't an account associated with that username, dumba**. Sorry!");
        } else {
            return accounts.get(userID);
        }
    }

    public double depositMoney(String userID, double amount) throws IOException {
        if (!accounts.containsKey(userID)) {
            throw new IOException("There isn't an account associated with that username. You're broke AF");
        }
        if (amount < 0) {
            throw new IOException(
                    "You can't deposit a negative amount of money. Please try withdrawing money instead?");
        } else {
            double currentMoney = accounts.get(userID);
            accounts.put(userID, amount + currentMoney);
            return amount;
        }

    }

    public double withdrawMoney(String userID, double amount) throws IOException {
        if (!accounts.containsKey(userID)) {
            throw new IOException("There isn't an account associated with that username. You're broke AF");
        }
        if (accounts.get(userID) == 0) {
            throw new IOException("Sorry bro, you're broke");
        }
        double currentMoney = accounts.get(userID);
        double newMoney = currentMoney - amount;
        if (newMoney < 0) {
            throw new IOException("You're trying to withdraw more money than what is in your account.");
        }
        accounts.put(userID, newMoney);
        return amount;
    }

    public boolean transferMoney(String user1, String user2, double amount) throws IOException {
        try {
            withdrawMoney(user1, amount);
            depositMoney(user2, amount);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void audit() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("AccountAudit.txt");
        for (HashMap.Entry<String, Double> entry : accounts.entrySet()) {
            writer.print("Email is: " + entry.getKey());
            writer.print("  Balance is: " + entry.getValue());
            writer.print("\n");
        }
        writer.close();
    }
}