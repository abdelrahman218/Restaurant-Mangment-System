package employees;
//Stream Classes
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//Data Structures
import java.util.ArrayList;
import java.util.Collections;

//Self-Defined Classes
import system.Meal;
import system.Reservation;
import user.Guest;
//Exeception Classes
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.text.ParseException;
import javax.management.InvalidAttributeValueException;

public class Receptionist extends Person implements Comparable<Receptionist>{
    private static ArrayList<Receptionist> receptionists=new ArrayList<>();
    private double revenue;
    private static int idGenerator=0;
    private double reservationsCount;
    public Receptionist(String name,String address,String dateOfBirth,String Phone,String Email,String UserName,String Password){
        super(name,address,dateOfBirth,Phone,Email,UserName,Password);
        receptionists.add(this);
        Id=++idGenerator;
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
    public static void saveRecords(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TablesData.dat"));
                out.writeObject(receptionists);
            out.close();
            } catch (IOException e) {
            System.out.println(e);
            }
    }
    public static void getRecord(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("TablesData.dat"));
            receptionists=(ArrayList<Receptionist>)in.readObject();
            in.close();
        }catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
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