package App;

import model.Account;
import model.Computer;
import service.ManagerAccount;
import service.ManagerComputer;

public class LogIn {
    private static final LogIn instance = new LogIn();
    private final ManagerAccount managerAccount = ManagerAccount.getInstance();
    private final ManagerComputer managerComputer = ManagerComputer.getInstance();
    private int Id;
    private String userName;
    private int passWork;

    public LogIn(int id, String userName, int passWork) {
        this.Id = id;
        this.userName = userName;
        this.passWork = passWork;
    }
    public static LogIn getInstance(){
        return instance;
    }
    public LogIn(){
//        stringList.getStringList().addAll(readDataFromFile());
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPassWork() {
        return passWork;
    }

    public void setPassWork(int passWork) {
        this.passWork = passWork;
    }

    public boolean checkLogIn(String userName, int passWork){
        for (int i = 0; i < managerAccount.getListAcc().size(); i++) {
            if(managerAccount.getListAcc().get(i).getUserName().equals(userName)){
                if (managerAccount.getListAcc().get(i).getPassword() == passWork){
                    return true;
                }
            }
        }
        return false;
    }
    public String checkLogInComputer(){
        int indexCom = managerComputer.findIndexById(Id);
        int indexAcc = managerAccount.findIndexByName(userName);
        Computer computer = managerComputer.getComputerList().get(indexCom);
        Account account = managerAccount.getListAcc().get(indexAcc);
        if(computer.getStatus() == 1){
            if(computer.getIsReady() == 0 && account.getIsReady() == 0 ){
                if(checkLogIn(userName,passWork)){
                    return "Đăng nhập thành công";
                }
            } else if(computer.getIsReady() == 1){
                return "Máy đang sử dụng!";

            } else if(account.getIsReady() == 1){
                return "Tài khoản đang sử dụng!";
            }
        } else {
            return  "Máy đang tắt";
        }
        managerComputer.writeToFile();
        managerAccount.writeToFile();
        return null;
    }
    public void logInComputer(){
        int indexCom = managerComputer.findIndexById(Id);
        int indexAcc = managerAccount.findIndexByName(userName);
        Computer computer = managerComputer.getComputerList().get(indexCom);
        Account account = managerAccount.getListAcc().get(indexAcc);
        if(checkLogInComputer().equals("Đăng nhập thành công")){
            computer.setIsReady(1);
            account.setIsReady(1);
            computer.setAcc(userName);
        }
        managerComputer.writeToFile();
        managerAccount.writeToFile();
    }
    public void logOutComputer(){
        int indexCom = managerComputer.findIndexById(Id);
        int indexAcc = managerAccount.findIndexByName(userName);
        Computer computer = managerComputer.getComputerList().get(indexCom);
        Account account = managerAccount.getListAcc().get(indexAcc);
        computer.setIsReady(0);
        account.setIsReady(0);
        computer.setAcc("trống");
        managerComputer.writeToFile();
        managerAccount.writeToFile();
    }
    public void logInAdm(int Id){
        int indexCom = managerComputer.findIndexById(Id);
        Computer computer = managerComputer.getComputerList().get(indexCom);
        computer.setIsReady(1);
        managerComputer.writeToFile();
    }
}
