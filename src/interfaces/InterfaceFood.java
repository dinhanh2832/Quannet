package interfaces;

public interface InterfaceFood<T> {
    void addFood(T t);

    int findIndexById(int id);

    void update(T t, int id);

    void deleteById(int id);

    void print();

}
