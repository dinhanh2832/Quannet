package menu;

import App.LogIn;
import calculation.CalculationTime;
import model.Account;
import model.Computer;
import service.ManagerAccount;
import service.ManagerComputer;

import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class ManagerMenuUser extends ManagerMenu {
    private final LogIn logIn = LogIn.getInstance();
    private final ManagerAccount managerAccount = ManagerAccount.getInstance();
    private final ManagerComputer managerComputer = ManagerComputer.getInstance();
    private final CalculationTime calculationTime = new CalculationTime();
    private String str;
    private String userName;
    private String strTimeNow;
    private String strTimeOut;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void showMenuUser() {
        System.out.println("--------------------------");
        System.out.println("Menu");
        System.out.println("1. Chọn đồ: ");
        System.out.println("2. Xem thông tin tài khoản: ");
        System.out.println("3. Đổi mật khẩu: ");
        System.out.println("4. Đăng xuất");
        System.out.println("--------------------------");
        System.out.println("Enter your choice: ");
    }
    public void showMenuAdmin(){
        System.out.println("-------------------------");
        System.out.println("Menu");
        System.out.println("1. Tạo tài khoản: ");
        System.out.println("2. Đổi pass người dùng: ");
        System.out.println("3. Nạp tiền tài khoản: ");
        System.out.println("4. Xem thông tin máy đang sử dụng: ");
        System.out.println("5. Xem thông tin tất cả các máy: ");
        System.out.println("6. Đăng xuất tài khoản ra khỏi máy: ");
        System.out.println("7. Bật máy: ");
        System.out.println("8. Xem doanh thu: ");
        System.out.println("9. In hóa đơn: ");
        System.out.println("10. Thêm máy: ");
        System.out.println("11. Xóa máy: ");
        System.out.println("12. Xem thông tin tài khoản: ");
        System.out.println("13. Xem số tài khoản đang hoạt động: ");
        System.out.println("14. Xóa tài khoản: ");
        System.out.println("15. Xem tất cả tài khoản: ");
        System.out.println("16. Đăng xuất: ");
        System.out.println("--------------------------");
        System.out.println("Enter your choice: ");
    }

    public void Input() {
        Scanner sc = new Scanner(System.in);
        int Id = 0;
        int pass = -1;
        while (!logIn.checkLogIn(userName, pass)) {
            try {
                System.out.println("Chọn máy: ");
                Id = sc.nextInt();
                System.out.println("Nhập tài khoản: ");
                sc.nextLine();
                userName = sc.nextLine();
                System.out.println("Nhập mật khẩu: ");
                pass = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
            }
        }
        logIn.setId(Id);
        logIn.setUserName(userName);
        logIn.setPassWork(pass);
        str = logIn.checkLogInComputer();
        if (str.equals("Đăng nhập thành công")) {
            logIn.logInComputer();
            strTimeNow = calculationTime.getTimeStart();
        }
        System.out.println(str);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        showMenu();
        while (choice == -1) {
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
            }
        }
        switch (choice) {
            case 1 -> {
                String string = "";
                Input();
                string = str;
                while (!string.equals("Đăng nhập thành công")) {
                    switch (string) {
                        case "Máy đang sử dụng!" -> {
                            System.out.println("Vui lòng chọn máy khác!");
                            Input();
                            string = str;
                        }
                        case "Tài khoản đang sử dụng!" -> {
                            System.out.println("Vui lòng chọn Tài khoản khác!");
                            Input();
                            string = str;
                        }
                        case "Máy đang tắt" -> {
                            System.out.println("Chọn máy đã bật!");
                            Input();
                            string = str;
                        }
                    }

                }
                if(managerAccount.findByUserName(userName).getIsReady()== 1){
                    int choice2 = -1;
                    while (true) {
                        showMenuUser();
                        while (choice2 == -1) {
                            try {
                                choice2 = sc.nextInt();
                            } catch (Exception e) {
                                sc.nextLine();
                            }
                        }
                        switch (choice2) {
                            case 1 -> {
                            }
                            case 2 ->{
                                strTimeOut = calculationTime.getTimeClose();
                                double time = calculationTime.getUserTime(strTimeNow,strTimeOut);
                                double timeAcc =  managerAccount.findByUserName(userName).getTime();
                                int index = managerAccount.findIndexByName(userName);
                                double timeBack = timeAcc - time;
                                double money2 = (timeBack/60)*10000;
                                managerAccount.deduction(userName,money2);
                                System.out.println(managerAccount.readDataFromFile().get(index));
                            }

                            case 3 -> {
                                int index = managerAccount.findIndexByName(userName);
                                System.out.println("Nhập mật khẩu cũ: ");
                                int passOld = sc.nextInt();
                                System.out.println("Nhập mật khẩu mới: ");
                                int passNew = sc.nextInt();
                                if(passOld == managerAccount.readDataFromFile().get(index).getPassword()){
                                    managerAccount.updatePass(userName,passNew);
                                } else System.out.println("Mật khẩu không khớp!");
                            }
                            case 4 -> {
                                logIn.logOutComputer();
                                System.exit(0);
                            }
                            default -> System.out.println("No choice!");
                        }
                        choice2 = -1;
                    }
                }else System.out.println("Đã đăng xuất!");

            }
            case 2 -> {
                String useNameAdm = "";
                int passAdm = 0;
                int Id = -1;
                while (!(managerAccount.getUserAdmin().equals(useNameAdm) && managerAccount.getPassAdmin() == passAdm)){
                    try {
                        System.out.println("Chọn máy: ");
                        Id = sc.nextInt();
                        System.out.println("Nhập tài khoản: ");
                        sc.nextLine();
                        useNameAdm = sc.nextLine();
                        System.out.println("Nhập mật khẩu");
                        passAdm = sc.nextInt();
                    } catch (Exception e){
                        sc.nextLine();
                    }
                }
                logIn.logInAdm(Id);
                System.out.println("Đăng nhập thành công!");
                int choice3 = -2;
                while (true) {
                    showMenuAdmin();
                    while (choice3 == -2) {
                        try {
                            choice3 = sc.nextInt();
                        } catch (Exception e) {
                            sc.nextLine();
                        }
                    }
                    switch (choice3) {
                        case 1 -> {
                            System.out.println("Nhập tài khoản: ");
                            sc.nextLine();
                            String useName = sc.nextLine();
                            System.out.println("Nhập mật khẩu: ");
                            int pass = sc.nextInt();
                            managerAccount.addAccount(new Account(useName, pass));
                        }
                        case 2 -> {
                            System.out.println("Nhập tên Tài khoản cần đổi: ");
                            sc.nextLine();
                            String userName1 = sc.nextLine();
                            int index = managerAccount.findIndexByName(userName1);
                            System.out.println(managerAccount.readDataFromFile().get(index));
                            System.out.println("Nhập mật khẩu mới: ");
                            int passNew1 = sc.nextInt();
                            managerAccount.updatePass(userName1, passNew1);
                        }
                        case 3 -> {
                            System.out.println("Nhập tài khoản muốn nạp: ");
                            sc.nextLine();
                            String name = sc.nextLine();
                            System.out.println("Nhập vào số tiền: ");
                            double money = sc.nextDouble();
                            managerAccount.updateMoney(name, money);
                        }
                        case 4 -> {
                            List<Computer> list = managerComputer.readDataFromFile();
                            for (Computer computer : list) {
                                if (computer.getIsReady() == 1) {
                                    System.out.println(computer);
                                }
                            }
                        }
                        case 5 -> managerComputer.readFile();
                        case 6 -> {
                            System.out.println("Nhập máy muốn đăng xuất: ");
                            int Id1 = sc.nextInt();
                            logIn.setId(Id1);
                            for (int i = 0; i < managerComputer.readDataFromFile().size(); i++) {
                                if(Id1 == managerComputer.readDataFromFile().get(i).getId()){
                                    logIn.setUserName(managerComputer.readDataFromFile().get(i).getAcc());
                                }
                            }

                            logIn.logOutComputer();
                        }
                        case 7 -> {
                            System.out.println("Nhập máy muốn bật: ");
                            int Id2 = sc.nextInt();
                            managerComputer.turnOn(Id2);
                        }
                        case 10 -> managerComputer.addComputer(new Computer());
                        case 11 -> {
                            System.out.println("Nhập máy muốn xóa: ");
                            int Id3 = sc.nextInt();
                            managerComputer.deleteById(Id3);
                        }
                        case 12 -> {
                            System.out.println("Nhập tên tài khoản muốn xem: ");
                            sc.nextLine();
                            String name = sc.nextLine();
                            int index = managerAccount.findIndexByName(name);
                            System.out.println(managerAccount.readDataFromFile().get(index));
                        }
                        case 13 -> {
                            List<Account> list = managerAccount.readDataFromFile();
                            for (Account account : list) {
                                if (account.getIsReady() == 1) {
                                    System.out.println(account);
                                }
                            }
                        }
                        case 14 -> {
                            System.out.println("Nhập tài khoản muốn xóa: ");
                            sc.nextLine();
                            String name = sc.nextLine();
                            managerAccount.deleteByName(name);
                        }
                        case 15 -> managerAccount.readFile();
                        case 16 -> System.exit(0);
                        default -> System.out.println("No choice!");
                    }
                    choice3 = -2;
                }
            }
            case 0 -> System.exit(0);
            default -> System.out.println("No choice!");
        }
    }

}
