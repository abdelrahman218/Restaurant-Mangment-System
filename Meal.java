package System;

import java.util.ArrayList;
public class Meal {

   private static ArrayList<Meal> mealList = new ArrayList<>();
    private int meal_ID;
    private String Name;
    private double Price;
    private String type;
    private int noOfOrders;

    public Meal(String menu_ID, int meal_ID, String Name, double Price, String type, int noOfOrders) {
        this.meal_ID = meal_ID;
        this.Name = Name;
        this.Price = Price;
        this.type = type;
        this.noOfOrders = noOfOrders;
    }

    public int getMeal_ID() {
        return meal_ID;
    }
   
    protected String getName() {
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

    public static ArrayList<Meal> getMealList() {
        return mealList;
    }

    public static Meal getMealById(int id) {
        for (int i = 0; i < mealList.size(); i++) {
            Meal meal = mealList.get(i);
            if (meal.getMeal_ID()==id) {
                return meal;
            }
        }
        return null; 
    }
}
