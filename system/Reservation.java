package system;
//Stream Classes
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Data Structures
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;

//Self-Defined Classes
import system.Reservation;
import system.Table;
import system.Meal;
import user.Guest;
import Employees.Receptionist;

//Formater Classes
import java.text.SimpleDateFormat;

//Exeception Classes
import java.lang.IndexOutOfBoundsException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import javax.management.InvalidAttributeValueException;
import java.lang.NullPointerException;

//Collections Class
import java.util.Collections;

public class Reservation implements Comparable<Reservation>,Serializable{
    //Static array list which have all reservations
    private static ArrayList<Reservation> history=new ArrayList<>();
    private static int idgenerator=0;
    //Object-related member variables 
    private int reservationNum;
    private int tableNum;
    private int receptionistId;
    private int guestId;
    private ArrayList<Integer> order;
    private int numOfGuests;
    private double price;
    private byte rating;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;

    //Constructor
    public Reservation(){
       reservationNum=++idgenerator;
       date=new Date();
       order=new ArrayList<>();
       startTime=LocalTime.MIDNIGHT;
       endTime=LocalTime.MIDNIGHT;
       history.add(this);
    }
    
    //Setters
    public void setReceptionistId(int id)throws InvalidAttributeValueException{
        Receptionist temp=Receptionist.search(id);
        if(temp!=null){
            receptionistId=id;
        }
        else{
            throw new InvalidAttributeValueException("Receptionist not found!");
        }
    }
    public void setGuestId(int id)throws InvalidAttributeValueException{
        Guest temp=Guest.getGuest(id);
        if(temp!=null){
            guestId=id;
        }
        else{
            throw new InvalidAttributeValueException("Guest not found!");
        }
    }
    public void setTableNum(int tableNumber) throws InvalidAttributeValueException{
        try{
            Table.getTable(tableNumber);
            tableNum=tableNumber;
        }catch(IndexOutOfBoundsException e){
            throw new InvalidAttributeValueException("Table not found!");
        }
    }
    public void setNumOfGuests(int num) throws InvalidAttributeValueException{
        Table reserved=Table.getTable(tableNum);
        if(num>reserved.getNoOfSeats()){
            throw new InvalidAttributeValueException("Guests can't be more than "+reserved.getNoOfSeats());
        }
        numOfGuests=num;
    }
    public void setDate(String input) throws ParseException{
        SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
        try{
            date=sf.parse(input);
        }catch(ParseException e){
            throw e;
        }
    }
    public void setStartTime(String input) throws DateTimeParseException{
        try{
            startTime=LocalTime.parse(input);
        }catch(DateTimeParseException e){
            throw e;
        }  
    }
    public void setEndTime(String input) throws DateTimeParseException,InvalidAttributeValueException{
        try{
            endTime=LocalTime.parse(input);    
        }catch(DateTimeParseException e){
            throw e;
        }
        if(endTime.compareTo(startTime)<0){
            throw new InvalidAttributeValueException("----------Invalid Time input-------------");
        }else if(isReserved()){
            throw new InvalidAttributeValueException("Table is reserved in the specified time.");
        }  
    }
    public void setRating(byte guestRating) throws InvalidAttributeValueException{
        if(guestRating>=0 && guestRating<=10){
            rating=guestRating;
        }
        else{
            throw new InvalidAttributeValueException("Rating must be (0->10)");
        }
    }
   
    //Functions
    public void takeOrder(ArrayList<Meal> orderOfMeals) throws NullPointerException{
        for(int i=0;i<orderOfMeals.size();i++){
            if(orderOfMeals.get(i)!=null){
                order.add(Integer.valueOf(orderOfMeals.get(i).getMeal_ID()));
                orderOfMeals.get(i).incrementedOrders();
            }
            else{
                throw new NullPointerException("Meal pointer can't be equal null");
            }
        }
        calculatePayment();
    }
    private void calculatePayment(){
        price+=Table.getTable(tableNum).getCost();
        for(int i=0;i<order.size();i++){
            price+=Meal.getMealById(order.get(i)).getPrice();
        }
        price*=1.14; //taxes
    }
    private boolean isReserved(){
        return Table.getTable(tableNum).isReserved(date, startTime, endTime);
    }
    private String orderNames(){
        String orderList="[ ";
        for(int i=0;i<order.size();i++){
            orderList+=(Meal.getMealById(order.get(i).intValue()).getName());
            if(i!=order.size()-1){
                orderList+=" , ";
            }
        }
        orderList+=" ]";
        return orderList;
    }
    
    //Getters
    public int getReservationNumber(){return reservationNum;}
    public Date getDate(){return date;}
    public LocalTime getStartTime(){return startTime;}
    public LocalTime getEndTime(){return endTime;}
    public ArrayList<Integer> getOrder(){return order;}
    public double getPrice(){return price;}
    public  static ArrayList<Reservation> getList() { return history; }
    public int getTableNum() { return tableNum; }
    public int getReceptionistId() { return receptionistId; }
    public int getGuestId() { return guestId; }
    public int getNumOfGuests() { return numOfGuests; }

    //static search functions
    public static Reservation search(int id){
        Collections.sort(history);
        int first=0;
        int last=history.size()-1;
        int mid;
        while(first<=last){
            mid=(int)((first+last)/2);
            if(id>history.get(mid).getReservationNumber())
            first=mid+1;
            else if(id<history.get(mid).getReservationNumber())
            last=mid-1;
            else return history.get(mid);
        }
        return null;    
    }
    public static ArrayList<Reservation>search(Table key){
        ArrayList<Reservation> result=new ArrayList<>();
        for(int i=0;i<history.size();i++){
            if(history.get(i).tableNum==key.getTableID())
            result.add(history.get(i));
        }
        return result;
    }
    public static ArrayList<Reservation>search(Receptionist key){
        ArrayList<Reservation> result=new ArrayList<>();
        for(int i=0;i<history.size();i++){
            if(history.get(i).receptionistId==key.getId())
            result.add(history.get(i));
        }
        return result;
    }
    public static ArrayList<Reservation>search(Guest key){
        ArrayList<Reservation> result=new ArrayList<>();
        for(int i=0;i<history.size();i++){
            if(history.get(i).guestId==key.getId())
            result.add(history.get(i));
        }
        return result;
    }
    public static ArrayList<Reservation>search(Meal key){
        ArrayList<Reservation> result=new ArrayList<>();
        for(int i=0;i<history.size();i++){
            for(int j=0;j<history.get(i).order.size();j++){
                if(history.get(i).order.get(j)==key.getMeal_ID()){
                    result.add(history.get(i));
                }
            }
        }
     return result;
    }
    //Reading & Writing in binary files methods
    public static void getRecord(){
        try{
            FileInputStream f=new FileInputStream("Reservations archive.dat");
            ObjectInputStream in=new ObjectInputStream(f);
            history=(ArrayList<Reservation>)in.readObject(); 
            in.close();
            f.close();
        }catch(IOException e){
            System.out.println("Error happened reading the file: Reservation archive");
        }catch(ClassNotFoundException e){
            System.out.println("Error in class Reservation reading compatiability");
        }
        idgenerator=history.get(history.size()-1).getReservationNumber();
    }
    public static void saveRecords(){
        try{
            FileOutputStream f=new FileOutputStream("Reservations archive.dat");
            ObjectOutputStream out=new ObjectOutputStream(f);
            out.writeObject(history);   
            out.close();
            f.close();
        }catch(IOException e){
            System.out.println("Error happened writing in the file: Reservation archive");
        }
    }

    //Overriden object functions
    @Override
    public String toString(){
        return("Receptionist Name: "+Receptionist.search(receptionistId).getName()+"\nGuest Name: "+Guest.getGuest(guestId).getName()
        +"\nReservation No.: "+getReservationNumber()+"\nTable No.: "+getTableNum()+"\nNo. of Guests: "+getNumOfGuests()+
        "\nDate: "+getDate()+"\nFrom: "+getStartTime()+" To: "+getEndTime()+"\nOrder: "+orderNames()+"\nCost: "+getPrice()+"\nGuests rating: "+rating);
    }
    @Override
    public int compareTo(Reservation right){
        if(reservationNum>right.reservationNum)return 1;
        else if(reservationNum<right.reservationNum)return -1;
        else return 0;
    }
}