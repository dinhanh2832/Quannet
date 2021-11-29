package interfaces;

public interface InterfaceAccount<T> {
    void addAccount(T t);

    void update(T t, String userName);

    void updateMoney(String name,double money);

    void deleteByName(String userName);

    void print();
}
