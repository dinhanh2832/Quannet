package service;

import interfaces.InterfaceComputer;
import model.Computer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerComputer implements InterfaceComputer<Computer> {
    private static final ManagerComputer instance = new ManagerComputer();
    private final List<Computer> computerList = new ArrayList<>();

    private ManagerComputer() {
        computerList.addAll(readDataFromFile());
    }

    public static ManagerComputer getInstance() {
        return instance;
    }

    public List<Computer> getComputerList() {
        return computerList;
    }

    @Override
    public int findIndexById(int id) {
        for (int i = 0; i < computerList.size(); i++) {
            if (computerList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addComputer(Computer computer) {
        computer.setId(computerList.size());
        computerList.add(computer);
        writeToFile();
    }

    @Override
    public void update(Computer computer, int id) {
        int index = findIndexById(id);
        computerList.set(index, computer);
        writeToFile();
    }

    @Override
    public void deleteById(int id) {
        int index = findIndexById(id);
        computerList.remove(index);
        for (int i = 0; i < computerList.size(); i++) {
            computerList.get(i).setId(i);
        }
        writeToFile();
    }

    @Override
    public void print() {
        for (Computer computer : computerList) {
            System.out.println(computer);
        }
    }
    public void turnOn(int Id){
        int index = findIndexById(Id);
        computerList.get(index).setStatus(1);
        writeToFile();
    }
    public void writeToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("computer.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.computerList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Computer> readDataFromFile() {
        List<Computer> computers = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("computer.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            computers = (List<Computer>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return computers;
    }

    public void readFile() {
        for (int i = 0; i < readDataFromFile().size(); i++) {
            System.out.println(readDataFromFile().get(i));
        }
    }
}
