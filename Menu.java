package system;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
public class Menu implements Serializable {
    private static ArrayList<Menu> Menues = new ArrayList<Menu>();
    public MenuCategory Categ;
    private int Menu_ID;
    Menu(MenuCategory data) {
        this.Categ = data;
    }
    Menu(){
        this.Categ=MenuCategory.Breakfast;
    }
    public static ArrayList<Menu> getlist() {
        return Menues;
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
    public ArrayList<Meal> getMeals(){
    ArrayList<Meal> Meals=new ArrayList<Meal>();
        for(int i=0;i<Meal.getList().size();i++){
            if(Meal.getList().get(i).getMenu_ID()==Menu_ID){
                Meals.add(Meal.getList().get(i));
            }
        }
        return Meals;
    }
}