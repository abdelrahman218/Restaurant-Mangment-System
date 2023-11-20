package System;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class Menu {

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
}
