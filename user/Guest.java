package user;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Employees.Person;
import system.Category;
import system.Reservation;
public class Guest extends Person {
    private static ArrayList<Guest> Guests=new ArrayList<Guest>();
    Category PreferredCategory;
    int numOfReservations=0;
   public Guest(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
        super(Name,Address,DateOfBirth,PhoneNum,Email,UserName,Password);
        Guests.add(this);
    }
    public String ViewReservation(){
        String list="";
        ArrayList<Reservation>R=Reservation.getList();
       for (int  i=0;i<Reservation.getList().size();i++){
           list+=R.get(i)+"\n";
        }
        return list;
    }
    public void incrementReservation(){numOfReservations++;}
    public void decrementReservation(){numOfReservations--;}
    public void setPreferedCategory(int key){
        switch(key){
            case 0:
                PreferredCategory=Category.Standard;
                break;
            case 1:
                PreferredCategory=Category.Couples;
                break;
            case 2:
                PreferredCategory=Category.Family;
                break;
            case 3:
                PreferredCategory=Category.Private;
                break;        

        }
    }
    public static Guest getGuest(int key){
        for(int i=0;i<Guests.size();i++){
            if(key==Guests.get(i).getId()){
                return Guests.get(i);
            }
        }
        return null;
    }
    public static ArrayList<Guest> getList(){
     return Guests;
    } 
    public static void saveRecords(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GuestsData.dat"));
                out.writeObject(Guests);
            out.close();
            } catch (IOException e) {
            System.out.println(e);
            }
    }
    public static void getRecord(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("GuestsData.dat"));
            Guests=(ArrayList<Guest>)in.readObject();
            in.close();
        }catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    } 
    @Override
    public String toString() {
        String history=ViewReservation();
        return "Guest{history= " + history + ", PreferredCategory=" + PreferredCategory + '}';
    }
}