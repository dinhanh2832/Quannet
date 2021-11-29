package service;

import calculation.CalculationTime;
import interfaces.InterfaceAccount;
import model.Account;

import java.io.*;
import java.util.*;

public class ManagerAccount implements InterfaceAccount<Account> {
    private String userAdmin = "Admin";
    private int passAdmin = 1234;
    private static final ManagerAccount instance = new ManagerAccount();
    private Map<String, Account> listAccount = new TreeMap<>();
    private CalculationTime calculationTime = new CalculationTime();
    private String timeStart;
    private String timeOut;

    private ManagerAccount() {
        for (int i = 0; i < readDataFromFile().size(); i++) {
            listAccount.put(readDataFromFile().get(i).getUserName(), readDataFromFile().get(i));
        }
    }

    public static ManagerAccount getInstance() {
        return instance;
    }

    public Map<String, Account> getListAccount() {
        return listAccount;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public CalculationTime getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(CalculationTime calculationTime) {
        this.calculationTime = calculationTime;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public int getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(int passAdmin) {
        this.passAdmin = passAdmin;
    }

    public void setListAccount(Map<String, Account> listAccount) {
        this.listAccount = listAccount;
    }

    @Override
    public void addAccount(Account account) {
        listAccount.put(account.getUserName(), account);
        writeToFile();
    }

    @Override
    public void update(Account account, String userName) {
        findByUserName(userName).setUserName(account.getUserName());
        findByUserName(userName).setPassword(account.getPassword());
        findByUserName(userName).setIsReady(account.getIsReady());
        writeToFile();
    }

    @Override
    public void updateMoney(String name, double money) {
        double money1 = money + (findByUserName(name).getTime()/60)*10000;
        findByUserName(name).setMoney(money1);
        writeToFile();
    }
    public void deduction(String name,double money){
        findByUserName(name).setMoney(money);
        writeToFile();
    }
    public void updatePass(String name, int pass) {
        findByUserName(name).setPassword(pass);
        writeToFile();
    }

    @Override
    public void deleteByName(String userName) {
        listAccount.remove(userName);
        writeToFile();
    }

    @Override
    public void print() {
        for (Map.Entry<String, Account> account : listAccount.entrySet()) {
            System.out.println(account.getValue());
        }
    }

    public Account findByUserName(String userName) {
        for (int i = 0; i < listAccount.size(); i++) {
            if (listAccount.containsKey(userName)) {
                return listAccount.get(userName);
            }
        }
        return null;
    }

    public int findIndexByName(String userName) {
        for (int i = 0; i < getListAcc().size(); i++) {
            if (getListAcc().get(i).getUserName().equals(userName)) {
                return i;
            }
        }
        return -1;
    }

    public List<Account> getListAcc() {
        List<Account> accountList = new ArrayList<>();
        for (Map.Entry<String, Account> account : listAccount.entrySet()) {
            accountList.add(account.getValue());
        }
        return accountList;
    }

    public void writeToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("account.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ManagerAccount.getInstance().getListAcc());
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Account> readDataFromFile() {
        List<Account> accounts = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("account.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            accounts = (List<Account>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return accounts;
    }

    public void readFile() {
        for (int i = 0; i < readDataFromFile().size(); i++) {
            System.out.println(readDataFromFile().get(i));
        }
    }

    public String getTimeStart1() {
        timeStart = calculationTime.getTimeStart();
        return timeStart;
    }

    public TimerTask getTimeOut1() {
        return new TimerTask() {
            @Override
            public void run() {
                setTimeOut(calculationTime.getTimeClose());
                writeToFile();
            }
        };
    }

    public static void main(String[] args) {
//        ManagerAccount.getInstance().addAccount(new Account("abc",1));
//        ManagerAccount.getInstance().addAccount(new Account("abc1",1));
//        ManagerAccount.getInstance().addAccount(new Account("abc2",1));
//        ManagerAccount.getInstance().addAccount(new Account("abc3",1));
        ManagerAccount.getInstance().readFile();
    }
}
