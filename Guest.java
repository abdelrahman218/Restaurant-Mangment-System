package user;
import java.util.ArrayList;
import employees.Person;
import java.util.Scanner;
import javax.management.InvalidAttributeValueException;
import system.Category;
import system.Reservation;
public class Guest extends Person {
    private static ArrayList<Guest> Guests=new ArrayList<Guest>();
    Category PreferredCategory;
   public Guest(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
        super(Name,Address,DateOfBirth,PhoneNum,Email,UserName,Password);
        Guests.add(this);
    }
     public  String ViewReservation(){
    
        String list = Reservation.search(this).toString();
               return list;
      
    }
    
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
    public static ArrayList getList(){
     return Guests;

    }   

    @Override
    public String toString() {
        return "Guest{" + "PreferredCategory=" + PreferredCategory + '}';
    }

    public static ArrayList<Guest> getGuests() {
        return Guests;
    }
    public Category getPreferredCategory() {
        return PreferredCategory;
    }

public void RateBooking(byte Rating) throws InvalidAttributeValueException{
    Reservation.search(this).getLast().setRating(Rating);
    
}    
    
            
}
