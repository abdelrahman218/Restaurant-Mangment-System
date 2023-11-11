/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package person;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class Guest extends PersonClass { 
    ArrayList<Reservation> history;
Category PreferredCategory;
   public Guest( String Name, String Address, String DateOfBirth, int PhoneNum, String Email) {
        super(Name, Address, DateOfBirth, PhoneNum, Email);
        this.history= new ArrayList();
        
        this.PreferredCategory = PreferredCategory;
    }
    void ViewReservation(){
       for (int  i=0;i<history.size();i++){
           System.out.println(history.get( i));
                 
           }
       } 
    
    void RateBooking(int CurrentReservation){
        for (int i=CurrentReservation;i<history.size();i++){
        int[] Rate=new int[50];
        Scanner x=new Scanner(System.in);
        System.out.println("Please enter your rating for your current resrvation: ");
        Rate[i]=x.nextInt();
}
}
       
    @Override
    public String toString() {
        return "Guest{" + "history=" + history + ", PreferredCategory=" + PreferredCategory + '}';
    }
}
   
