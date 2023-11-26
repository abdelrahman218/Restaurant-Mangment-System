package user;
import java.util.ArrayList;
import employees.Person;
import java.util.Scanner;
import system.Category;
import system.Reservation;
public class Guest extends Person {
    private static ArrayList<Guest> Guests=new ArrayList<Guest>();
    Category PreferredCategory;
   public Guest(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
        super(Name,Address,DateOfBirth,PhoneNum,Email,UserName,Password);
        Guests.add(this);
    }
     public  void ViewReservation(){
         int id=0;
       for(int i=0;i<Guests.size();i++){
            id=Guests.get(i).getId();
       }
       if(id==0){
           System.out.println("No current Reservation available for this guest");
       }
       else if(id>0){
        Reservation.search(id);
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
    public static void GetCurrentGuest(){
         for(int i=Guests.size()-1;i<Guests.size();i++){
              String Name=Guests.get(i).getName();
              String Address =Guests.get(i).getAddress();
              String DateOfBirth =Guests.get(i).getDateOfBirth();
              String Email=Guests.get(i).getEmail();
              String Pass =Guests.get(i).getPassword();
              String PhoneNum=Guests.get(i).getPhoneNum();
              String UserName=Guests.get(i).getUserName();
              int ID=Guests.get(i).getId();
              Category ge=Guests.get(i).getPreferredCategory();
       }
    }
    public Category getPreferredCategory() {
        return PreferredCategory;
    }
    public void RateBooking(){
        for(int i=0;i<Guests.size();i++){
        Reservation.search(Guests.get(i).getId());
       }
        int Rating;
        Scanner s=new Scanner(System.in);
        Rating=s.nextInt();
        if(Rating>=1&&Rating<=5){
            System.out.println("Thank you for your rating");
        }else{
            System.out.println("invalid Rating please provide a rating between 1 and 5");
        }
    }
    
    
            
}
