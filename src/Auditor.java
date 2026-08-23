import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Auditor {

    public static Map<String, Integer> calculateRiskRating(List<Account> accounts, List<Transaction> transactions) {
        Map<String, Integer> riskMap = new HashMap<>();

        for (Account acc : accounts) {
            riskMap.put(acc.getAccountNumber(), 0);
        }

        Map<String, Integer> largeAmountCounter = new HashMap<>();
        Map<String, Integer> oddHourCounter = new HashMap<>();

        final double LARGE_THRESHOLD = 1_000_000.0;
        final double OLD_AMOUNT_THRESHOLD = 5000.0;

        for (Transaction t : transactions) {
            String accNo = t.getAccountNumber();
            if (!riskMap.containsKey(accNo)) {
                continue;
            }

            Account acc = findAccount(accounts, accNo);
            if (acc == null) {
                continue;
            }

            double amount = t.getAmount();
            int hour = t.getHour();

            // Requirement 5: Extremely large transaction logic
            if (amount >= LARGE_THRESHOLD) {
                largeAmountCounter.put(accNo, largeAmountCounter.getOrDefault(accNo, 0) + 1);
                if (largeAmountCounter.get(accNo) >= 2) {
                    riskMap.put(accNo, riskMap.get(accNo) + 5);
                }
            } else if (amount > OLD_AMOUNT_THRESHOLD) {
                riskMap.put(accNo, riskMap.get(accNo) + 5);
            }

            // Requirement 6: Odd‑hour transaction logic (<6 or >22)
            boolean isOddHour = (hour < 6 || hour > 22);
            if (isOddHour) {
                oddHourCounter.put(accNo, oddHourCounter.getOrDefault(accNo, 0) + 1);
                if (oddHourCounter.get(accNo) >= 2) {
                    riskMap.put(accNo, riskMap.get(accNo) + 5);
                }
            }

            // Requirement3: handle unknown account type (no crash)
            String type = acc.getAccountType();
            if (!"Checking".equalsIgnoreCase(type) && !"Savings".equalsIgnoreCase(type)) {
                // unknown type, program continues without crash
            }
        }
        return riskMap;
    }

    private static Account findAccount(List<Account> accounts, String accNo) {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accNo)) {
                return a;
            }
        }
        return null;
    }
}
