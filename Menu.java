/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class Menu {

    private ArrayList<Meal> Menu;

    public Menu(ArrayList<Meal> Menu) {
        this.Menu = Menu;
    }

    public void addMeal(Meal meal) {
        Menu.add(meal);
    }

    public void removeMeal(Meal meal) {
        Menu.remove(meal);
    }
public static ArrayList<Meal> getlist(){
    return Menu;
}
    public Meal mostOrderedMeal(Date startDate, Date endDate) {
        Meal mostOrdered = null;
        for (int i = 0; i < Menu.size(); i++) {
            Meal meal = Menu.get(i);
            if (meal.getNoOfOrders() > mostOrdered.getNoOfOrders()) {
                mostOrdered = meal;
            }
        }
        
        return mostOrdered;
        
    }
}
