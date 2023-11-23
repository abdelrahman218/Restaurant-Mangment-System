package system;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
public class Meal implements Serializable {
    private static ArrayList<Meal> Meals = new ArrayList<>();
    private int meal_ID;
    private String Name;
    private double Price;
    private int Menu_ID;
    public int getMenu_ID() {
        return Menu_ID;
    }
    public void setMenu_ID(int menu_ID) {
        Menu_ID = menu_ID;
    }
    private int noOfOrders=0;
    public Meal(int Menu_ID, int meal_ID, String Name, double Price,int noOfOrders) {
        this.Menu_ID=Menu_ID;
        this.meal_ID = meal_ID;
        this.Name = Name;
        this.Price = Price;
        this.noOfOrders = noOfOrders;
    }
    public int getMeal_ID() {
        return meal_ID;
    }
    public String getName() {
        return Name;
    }
    public double getPrice() {
        return Price;
    }
    public int getNoOfOrders() {
        return noOfOrders;
    }
    public void incrementedOrders() {noOfOrders++;}
    public void decrementOrders(){ noOfOrders--;}
    public static ArrayList<Meal> getList() {return  Meals;}
    public static Meal getMealById(int id) {
        for (int i = 0; i < Meals.size(); i++) {
            Meal meal = Meals.get(i);
            if (meal.getMeal_ID() == id) {
                return meal;
            }
        }
        return null;
    }
    public static void ReadFromFile() {
        try {
            FileInputStream i = new FileInputStream("Meal.dat");
            ObjectInputStream o = new ObjectInputStream(i);
            Meals = (ArrayList<Meal>) o.readObject();
            i.close();
            o.close();
        } catch (IOException e) {
            System.out.println(e);
        }catch(ClassNotFoundException e){
        System.out.println(e);
        }
    }
    public static void WriteInFile() {
        try {
            FileOutputStream i = new FileOutputStream("Meal.dat");
            ObjectOutputStream o = new ObjectOutputStream(i);
            o.writeObject(Meals);
            o.close();
            i.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public Meal mostOrderedMeal(Date startDate, Date endDate) {
        Meal mostOrdered = null;
        for (int i = 0; i < Meals.size(); i++) {
            Meal meal = Meals.get(i);
            if (meal.getNoOfOrders() > mostOrdered.getNoOfOrders()) {
                mostOrdered = meal;
            }
        }
        return mostOrdered;
    }
  public ArrayList<Meal> getMealsByMenuId(int menuId) {
        ArrayList<Meal> mealsInMenu = new ArrayList<>();
        for (int i = 0; i < Meals.size(); i++) {
            Meal meal = Meals.get(i);
            if (meal.getMeal_ID() == menuId) {
                mealsInMenu.add(meal);
            }
        }
        return mealsInMenu;
    }
    public int OrdersInSpecificTime(Date StartDate, Date EndDate){
        int count=0;
        ArrayList<Reservation> Reservations=Reservation.search(this);
        for(int i=0;i<Meals.size();i++)
            if(StartDate.before(Reservations.get(i).getDate())||EndDate.after(Reservations.get(i).getDate())
             ||StartDate.equals(Reservations.get(i).getDate())||EndDate.equals(Reservations.get(i).getDate())) 
                count++;
        return count;
    }
}