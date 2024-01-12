package system;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
public class Menu implements Serializable {
    private static int idGenerator=0;
    private static ArrayList<Menu> Menues = new ArrayList<Menu>();
    private MenuCategory Categ;
    private int Menu_ID=++idGenerator;
    public Menu(MenuCategory data) {
        this.Categ = data;
        Menues.add(this);
    }
    public static ArrayList<Menu> getlist() {
        return Menues;
    }
    public static void ReadFromMenuFile() {
        try {
            FileInputStream i = new FileInputStream("Menu.dat");
            ObjectInputStream o = new ObjectInputStream(i);
            Menues = (ArrayList<Menu>) o.readObject();
            i.close();
            o.close();
            idGenerator=Menues.get(Menues.size()-1).getMenu_ID();
        } catch (IOException e) {
            System.out.println(e);
        }catch(ClassNotFoundException e){
        System.out.println(e);
        }
    }
    public static void WriteInMenuFile() {
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

    public int getMenu_ID() {
        return Menu_ID;
    }
    
    public MenuCategory getCateg() {
        return Categ;
    }
    public void setCateg(MenuCategory categ) {
        Categ = categ;
    }
    @Override
    public String toString(){
        return Categ.toString();
    }
}