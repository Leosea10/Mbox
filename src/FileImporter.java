import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FileImporter {

    public static List<Account> importAccounts(String filePath) {
        List<Account> accountList = new ArrayList<>();
        HashSet<String> existingAccountNos = new HashSet<>();
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            System.out.println("[Info] Accounts.txt is empty or does not exist. No accounts loaded.");
            return accountList;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("[Warning] Skipping malformed account line: " + line);
                    continue;
                }

                try {
                    String accNo = parts[0].trim();
                    String accType = parts[1].trim();
                    double bal = Double.parseDouble(parts[2].trim());

                    if (existingAccountNos.contains(accNo)) {
                        System.out.println("[Warning] Duplicate account " + accNo + " skipped.");
                        continue;
                    }

                    Account acc = new Account(accNo, accType, bal);
                    accountList.add(acc);
                    existingAccountNos.add(accNo);

                } catch (NumberFormatException e) {
                    System.out.println("[Warning] Cannot parse numeric value, skip line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("[Error] Reading Accounts.txt failed: " + e.getMessage());
        }
        return accountList;
    }

    public static List<Transaction> importTransactions(String filePath) {
        List<Transaction> transList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            System.out.println("[Info] Transactions.txt is empty or does not exist. No transactions loaded.");
            return transList;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("[Warning] Skipping malformed transaction line: " + line);
                    continue;
                }

                try {
                    String accNo = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    int hour = Integer.parseInt(parts[2].trim());

                    Transaction t = new Transaction(accNo, amount, hour);
                    transList.add(t);

                } catch (NumberFormatException e) {
                    System.out.println("[Warning] Parse number failed, skip transaction line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("[Error] Reading Transactions.txt failed: " + e.getMessage());
        }
        return transList;
    }
}
