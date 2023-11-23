package System;
import java.util.Date;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Serializable;
/**
 *
 * @author Omar
 */
public class Menu implements Serializable {

    private static ArrayList<Menu> Menues = new ArrayList<>(50);
    ArrayList<Meal> Meals = new ArrayList<>(50);
    public Category Categ;

    public Menu(ArrayList<Menu> Menu) {
        this.Menues = Menu;

    }
    Menu() {
        this.Categ = Category.Standard;
    }
    public void addMeal(Meal meal) {
        Meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        Meals.remove(meal);
    }
    public static ArrayList<Menu> getlist() {
        return Menues;
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
  public void ReadFromMenuFile() {
        try {
            FileInputStream i = new FileInputStream("Menu.dat");
            ObjectInputStream o = new ObjectInputStream(i);
            Menues = (ArrayList<Menu>) o.readObject();
            i.close();
            o.close();
        } catch (IOException e) {
            System.out.println(e);
        }catch(ClassNotFoundException e){
        System.out.println(e);
        }
    }
  public void WriteInMenuFile() {
        try {
            FileOutputStream i = new FileOutputStream("Menu.dat");
            ObjectOutputStream o = new ObjectOutputStream(i);
            o.writeObject(Menues);
            o.close();
            i.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
