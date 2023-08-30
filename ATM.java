import java.util.HashMap;

public class ATM {
    static HashMap<String, Double> accounts = new HashMap<String, Double>();

    public static void main(String[] args) {

    }

    public static void openAccount(String userID, double initAmt) {
        if (accounts.containsKey(userID)) {
            throw new Error("Oops! There's already an account under that name. Please try a different one");
        } else {
            accounts.put(userID, initAmt);
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

}