package model;

import java.io.Serializable;

public class Account implements Serializable {
    private String userName;
    private int password;
    private double unitPrice = 10000;
    private double money;
    private double time;


    private int isReady = 0;

    public Account(String userName, int passWork, double money) {
        this.userName = userName;
        this.password = passWork;
        this.money = money;
    }

    public Account(String userName, int passWork) {
        this.userName = userName;
        this.password = passWork;
    }

    public Account() {
    }

    public int getIsReady() {
        return isReady;
    }

    public void setIsReady(int isReady) {
        this.isReady = isReady;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public double getTime() {
        time = (getMoney()/getUnitPrice())*60;
        return time;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String checkIsReady(){
        if(isReady == 0){
            return "Chưa sử dụng";
        } else  if(isReady == 1){
            return "Đang sử dụng";
        }
        return null;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Tên tài khoản: " + userName +
                ", Mật khẩu: " + password +
                ", Số tiền khả dụng: " + getMoney() +
                ", Thời gian sử dụng: " + getTime() + " phút" +
                ", Trạng thái: " + checkIsReady();
    }
}
