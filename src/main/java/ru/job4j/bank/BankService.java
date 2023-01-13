package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public boolean deleteUser(String passport) {
        boolean result = false;
        User user = findByPassport(passport);
        if (user != null) {
            users.remove(user);
            result = true;
        }
        return result;
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (findByRequisite(passport, account.getRequisite()) == null) {
            users.get(user).add(account);
        }
    }

    public User findByPassport(String passport) {
        for (User temp : users.keySet()) {
            if (temp.getPassport().equals(passport)) {
                return temp;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> list = users.get(user);
            for (Account account : list) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {

        boolean result = false;
        Account source = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        if (source != null && source.getBalance() >= amount && dest != null) {
            dest.setBalance(dest.getBalance() + amount);
            source.setBalance(source.getBalance() - amount);
        }
        return result;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}