
import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 *
 * @author Omar
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          // Create some sample meals
        Meal meal1 = new Meal("Spaghetti ", 12.99, "Pasta");
        Meal meal2 = new Meal("Chicken ", 14.99, "Chicken");
        Meal meal3 = new Meal(" Pizza", 10.99, "Pizza");

        // Create a menu with the sample meals
        ArrayList<Meal> menuItems = new ArrayList<>();
        menuItems.add(meal1);
        menuItems.add(meal2);
        menuItems.add(meal3);

        Menu menu = new Menu(menuItems);

        // Add a meal to the menu
        Meal newMeal = new Meal(" Salad", 8.99, "Salad");
        menu.addMeal(newMeal);

        // Display the menu items
        System.out.println("Menu Items:");
        for (Meal meal : menuItems) {
            System.out.println("Name: " + meal.getName());
            System.out.println("Type: " + meal.getType());
            System.out.println("Price: $" + meal.getPrice());
            System.out.println();
        }

        // Find the most ordered meal within a date range
        Date startDate = new Date(); // Replace with an actual date
        Date endDate = new Date(); // Replace with an actual date
        Meal mostOrdered_In_Date_Range = menu.mostOrderedMeal(startDate, endDate);
        System.out.println("Most Ordered Meal in Date Range:");
        if (mostOrdered_In_Date_Range != null) {
            System.out.println("Name: " + mostOrdered_In_Date_Range.getName());
            System.out.println("Type: " + mostOrdered_In_Date_Range.getType());
            System.out.println("Price: $" + mostOrdered_In_Date_Range.getPrice());
        } else {
            System.out.println("No meals found in the specified date range.");
        }
    }
}
    
    