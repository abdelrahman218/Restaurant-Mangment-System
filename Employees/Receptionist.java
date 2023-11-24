package Employees;
//Stream Classes
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Data Structures
import java.util.ArrayList;
import java.util.Collections;

//Self-Defined Classes
import system.Meal;
import system.Reservation;
import user.Guest;
import system.Category;

//Exeception Classes
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.text.ParseException;
import javax.management.InvalidAttributeValueException;

public class Receptionist extends Person implements Comparable<Receptionist>,Serializable{
    private static ArrayList<Receptionist> receptionists=new ArrayList<>();
    private double revenue;
    private double reservationsCount;
    public Receptionist(String name,String address,String dateOfBirth,String Phone,String Email,String UserName,String Password){
        super(name,address,dateOfBirth,Phone,Email,UserName,Password);
        receptionists.add(this);
    }
    
    //Reservation Related functions
    public void createReservation(int guestId,int tableNum,int numOfGuests,String date,String start,String end,ArrayList<Meal>order)
    throws InvalidAttributeValueException,ParseException{
        Reservation r=new Reservation();
        try{
            r.setReceptionistId(getId());
            r.setTableNum(tableNum);
            r.setNumOfGuests(numOfGuests);
            try{
                r.setDate(date);
            }catch(ParseException parse){
                cancelReservation(r.getReservationNumber());
                throw parse;
            }
            r.setStartTime(start);
            r.setEndTime(end);
            r.takeOrder(order);
        }catch(InvalidAttributeValueException e){
            cancelReservation(r.getReservationNumber());
            throw e;
        }
        revenue+=r.getPrice();
        Guest.getGuest(guestId).incrementReservation();
        reservationsCount++;
    }
    public void cancelReservation(int resId) throws InvalidAttributeValueException{
        ArrayList<Reservation> reservations=Reservation.search(this);
        for(int i=0;i<reservations.size();i++){
            Reservation temp=reservations.get(i);
            if(temp.getReservationNumber()==resId){
                ArrayList<Integer> order=temp.getOrder();
                for(int j=0;j<temp.getOrder().size();j++){
                    Meal.getMealById(order.get(j)).decrementOrders();
                }
                reservations.remove(i);
                reservationsCount--;
                Guest.getGuest(reservations.get(i).getGuestId()).decrementReservation();
                return;
            }
        }
        throw new InvalidAttributeValueException("Reservation not found.");
    }
    
    //Guest Related function
    public void selectGuestPref(Guest keyGuest,Category preferred){
        keyGuest.setPreferedCategory(preferred.ordinal());
    }
    
    //Getter functions
    public  static ArrayList<Receptionist> getList() {
        return receptionists;
    }
    public double getRevenue() {
        return revenue;
    }
    public double getreservationsCount() {
        return reservationsCount;
    }
    
    //static search function
    public static Receptionist search(int id){
        Collections.sort(receptionists);
        int first=0;
        int last=receptionists.size()-1;
        int mid;
        while(first<=last){
            mid=(int)((first+last)/2);
            if(id>receptionists.get(mid).getId())
            first=mid+1;
            else if(id<receptionists.get(mid).getId())
            last=mid-1;
            else return receptionists.get(mid);
        }
        return null;    
    }
    
    //Reading & Writing in binary files methods
    public static void getRecord(){
        try{
            FileInputStream f=new FileInputStream("Receptionist archive.dat");
            ObjectInputStream in=new ObjectInputStream(f);
            int size=in.readInt();
            while(size>0){
                receptionists.add((Receptionist)in.readObject());
                size--;
            }    
            in.close();
            f.close();
        }catch(IOException e){
            System.out.println("Error happened reading the file: Receptionist archive");
        }catch(ClassNotFoundException e){
            System.out.println("Error in class Receptionist reading compatiability");
        }
    }
    public static void saveRecords(){
        try{
            FileOutputStream f=new FileOutputStream("Receptionist archive.dat");
            ObjectOutputStream out=new ObjectOutputStream(f);
            out.writeInt(receptionists.size());
            int i=0;
            while(i<receptionists.size()){
                out.writeObject(receptionists.get(i));
                i++;
            }    
            out.close();
            f.close();
        }catch(IOException e){
            System.out.println("Error happened writing in the file: Receptionist archive");
        }
    }
    
    //Overriden object functions
    @Override
    public String toString(){
        return ("Receptionist Details:\n"+"Name: "+getName()+"\nID: "+getId()+"\nDate Of Birth: "+getDateOfBirth()+"\nAddress: "+getAddress()+"\nEmail: "+getEmail()+"\nPhone Number: "+getPhoneNum()+"\nRevenue: "+revenue+"\nNumber of Reservations: "+reservationsCount);
    }
    @Override
    public int compareTo(Receptionist right){
        if(getId()>right.getId())return 1;
        else if(getId()<right.getId())return -1;
        else return 0;
    }
}