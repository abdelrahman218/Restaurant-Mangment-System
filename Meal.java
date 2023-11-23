package System;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Scanner;

public class Meal implements Serializable {

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
            if (meal.getMeal_ID() == id) {
                return meal;
            }
        }
        return null;
    }

    public void ReadToFile() {
        try {
            FileInputStream i = new FileInputStream("Meal.dat");
            ObjectInputStream o = new ObjectInputStream(i);
            mealList = (ArrayList<Meal>) o.readObject();
            i.close();
            o.close();
        } catch (IOException e) {
            System.out.println(e);
        }catch(ClassNotFoundException e){
        System.out.println(e);
        }
    }

    public void WriteInFile() {
        try {
            FileOutputStream i = new FileOutputStream("Meal.dat");
            ObjectOutputStream o = new ObjectOutputStream(i);
            o.writeObject(mealList);
            o.close();
            i.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
