package interfaces;

public interface InterfaceComputer<T> {
    int findIndexById(int id);

    void addComputer(T t);

    void update(T t, int id);

    void deleteById(int id);

    void print();

}
