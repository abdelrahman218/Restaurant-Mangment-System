package user;
import java.util.Scanner;
import java.util.ArrayList;
import employees.Person;
import system.Category;
import system.Reservation;
public class Guest extends Person {
    private static ArrayList<Guest> Guests=new ArrayList<Guest>();
    Category PreferredCategory;
   public Guest(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
        super(Name,Address,DateOfBirth,PhoneNum,Email,UserName,Password);
        Guests.add(this);
    }
    void ViewReservation(){
       for (int  i=0;i<Reservation.getList().size();i++){
           System.out.println(Reservation.getList().get( i));
                 
        }
    } 
    
    void RateBooking(int CurrentReservation){
        for (int i=CurrentReservation;i<Reservation.getList().size();i++){
            int[] Rate=new int[50];
            Scanner x=new Scanner(System.in);
            System.out.println("Please enter your rating for your current resrvation: ");
            Rate[i]=x.nextInt();
        }
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
    @Override
    public String toString() {
        return "Guest{" + "history=" + history + ", PreferredCategory=" + PreferredCategory + '}';
    }
}
