package service;

import interfaces.InterfaceFood;
import model.Computer;
import model.Food;

import java.util.ArrayList;
import java.util.List;

public class ManagerFood implements InterfaceFood<Food> {
    private static final ManagerFood instance = new ManagerFood();
    private List<Food> foodList = new ArrayList<>();
    private int count = 0;
    private ManagerFood(){
    }

    public static ManagerFood getInstance(){
        return instance;
    }

    @Override
    public void addFood(Food food) {
        food.setId(count);
        count++;
        foodList.add(food);
    }

    @Override
    public int findIndexById(int id) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void update(Food food, int id) {
        int index = findIndexById(id);
        foodList.set(index, food);
    }

    @Override
    public void deleteById(int id) {
        int index = findIndexById(id);
        foodList.remove(index);
    }

    @Override
    public void print() {
        for (Food food : foodList) {
            System.out.println(food);
        }
    }
}
