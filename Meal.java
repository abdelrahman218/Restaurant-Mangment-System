package system;

public class Meal {

    private String Name;
    private double Price;
    private String type;
    private int noOfOrders;

    public Meal(String Name, double Price, String type) {
        this.Name = Name;
        this.Price = Price;
        this.type = type;
    }

    public String getName() {
        return Name;
    }

    protected double getPrice() {
        return Price;
    }

    protected String getType() {
        return type;
    }

    public int getNoOfOrders() {
        return noOfOrders;
    }

    public void incrementedOrders() {
        noOfOrders++;
    }
}