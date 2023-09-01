import java.util.HashMap;
import java.io.IOException;

public class ATM {
    static HashMap<String, Double> accounts = new HashMap<String, Double>();

    public static void main(String[] args) {

    }

    public static void openAccount(String userID, double initAmt) {
        try {
            if (accounts.containsKey(userID)) {
                throw new IOException();
            } else {
                accounts.put(userID, initAmt);
            }
        } catch (Exception e) {
        }

    }

    public static void closeAccount(String userID) {
        if (!accounts.containsKey(userID)) {
            throw new Error("There isn't an account associated with that username, dumba**. Sorry!");
        } else {
            if (accounts.get(userID) > 0) {
                throw new Error("There's still money in this account! Don't close it yet!");
            } else {
                accounts.remove(userID);
                System.out.println("Account with username " + userID + " was removed.");
            }
        }
    }

    public static double checkBalance(String userID) {
        if (!accounts.containsKey(userID)) {
            throw new Error("There isn't an account associated with that username, dumba**. Sorry!");
        } else {
            return accounts.get(userID);
        }
    }

    public static double depositMoney(String userID, double amount) {
        if (!accounts.containsKey(userID)) {
            throw new Error("There isn't an account associated with that username. You're broke AF");
        } else {
            double currentMoney = accounts.get(userID);
            accounts.put(userID, amount + currentMoney);
            return amount;
        }

    }

    public static double withdrawMoney(String userID, double amount) {
        if (!accounts.containsKey(userID)) {
            throw new Error("There isn't an account associated with that username. You're broke AF");
        } else {
            if (accounts.get(userID) == 0) {
                throw new Error("Sorry bro, you're broke");
            }
            double currentMoney = accounts.get(userID);
            double newMoney = currentMoney - amount;

            accounts.put(userID, newMoney);
            return amount;
        }
    }
}