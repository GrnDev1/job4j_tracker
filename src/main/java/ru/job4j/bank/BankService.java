package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает модель для банковской системы
 *
 * @author Roman
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение задания осуществляется в коллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод должен добавить пользователя в систему
     * В методе должна быть проверка, что такого пользователя еще нет в системе.
     * Если он есть, то новый пользователь не добавляется
     * @param user пользователь, которого добавляют в систему
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод удаляет пользователя из системы
     * В методе должна быть проверка, что паспорт пользователя есть в системе.
     * @param passport уникально идентифицирует пользователя, которого удаляют.
     * @return возращает состояние удаления(true / false)
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод добавляет новый счет к пользователю
     * В методе должна быть проверка, что паспорт пользователя есть в системе.
     * А также проверка, что такого счета у пользователя еще нет.
     * @param passport уникально идентифицирует пользователя, которому создают новый счет
     * @param account  новый счет пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта
     * @param passport уникально идентифицирует пользователя, которого ищут в системе
     * @return возвращает найденного пользователя. Если ничего не найдено - метод должен вернуть null
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод ищет счет пользователя по реквизитам
     * @param passport уникально идентифицирует пользователя
     * @param requisite реквизиты пользователя
     * @return возвращает аккаунт пользователя.
     * Если пользователь с данными реквизитами не найден, то метод должен вернуть null
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(s -> s.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод предназначен для перечисления денег с одного счёта на другой счёт
     * @param srcPassport паспорт пользователя, у которого переводят деньги
     * @param srcRequisite реквизиты пользователя, у которого переводят деньги
     * @param destPassport паспорт пользователя, которому переводят деньги
     * @param destRequisite реквизиты пользователя, которому переводят деньги
     * @param amount сумма перечисления
     * @return возвращает результат состояния операции(true / false). Если счёт не найден
     * или не хватает денег на счёте srcAccount (с которого переводят), то метод должен вернуть false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {

        boolean result = false;
        Account source = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        if (source != null && source.getBalance() >= amount && dest != null) {
            dest.setBalance(dest.getBalance() + amount);
            source.setBalance(source.getBalance() - amount);
            result = true;
        }
        return result;
    }

    /**
     * Метод предназначен для поиска счетов пользователя
     * @param user пользователь, у которого ищёт все счета
     * @return возвращает список счетов пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}