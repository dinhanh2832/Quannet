package model;

public class Food {
    private int Id;
    private String name;
    private double price;
    private int quantity;

    public Food(int Id,String name, double price, int quantity) {
        this.Id = Id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Food() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Số: " + Id +
                ", Tên: " + name +
                ", Giá: " + price +
                ", Số lượng: " + quantity ;
    }
}
