package system;
//Stream Classes
import java.util.Scanner;
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
import java.util.InputMismatchException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.lang.IndexOutOfBoundsException;

//Collections Class
import java.util.Collections;

public class Reservation implements Comparable<Reservation>,Serializable{
    //Static member variables
    private static ArrayList<Reservation> history=new ArrayList<>();
    private static int idGenerator=0;

    //Object-related member variables 
    private int reservationNum;
    private int tableNum;
    private int receptionistId;
    private int guestId;
    private ArrayList<String> order;
    private int numOfGuests;
    private double price;
    private byte rating;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;

    //Constructor
    public Reservation(){
       reservationNum=++idGenerator;
       date=new Date();
       order=new ArrayList<>();
       startTime=LocalTime.MIDNIGHT;
       endTime=LocalTime.MIDNIGHT;
       history.add(this);
    }
    
    //Setters
    public void setReceptionistId(int id){receptionistId=id;}
    public void setGuestId(Scanner take){
        boolean check=true;
        while(check){
            System.out.print("Guest ID: ");
            guestId=take.nextInt();
            System.out.print("\n");
            Guest temp=Guest.getGuest(guestId);
            if(temp!=null){
                check=false;
            }
            else{
                System.out.println("Guest not found!");
            }
            
        }
    }
    public void setTableNum(Scanner take){
        boolean check=true;
        while(check){
            System.out.print("Table number: ");
            tableNum=take.nextInt();
            System.out.print("\n");
            try{
                Table.getTable(tableNum);
                check=false;
            }catch(IndexOutOfBoundsException e){
                System.out.println("Table not found!");
            }
        }
    }
    public void setNumOfGuests(Scanner take){
        boolean check=true;
        while(check){
            System.out.print("Number of Guests: ");
            try{
                numOfGuests=take.nextInt();
                check=false;
            }catch(InputMismatchException e){ 
                System.out.println("Invalid input"); 
                take.next();
            }
            System.out.print("\n");
        }
    }
    public void setDate(Scanner take){
        boolean check=true;
        while(check){
            System.out.print("Date (dd/mm/yyyy): ");
            String input=take.next();
            System.out.print("\n");
            SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
            try{
                date=sf.parse(input);
                check=false;
            }catch(ParseException e){
                System.out.println("----------Invalid Date input-------------");
            }
        }
    }
    public void setStartTime(Scanner take){
        boolean check=true;
        while(check){
            System.out.print("Start Time: ");
            String input=take.next();
            System.out.print("\n");
            try{
                startTime=LocalTime.parse(input);
                check=false;
            }catch(DateTimeParseException e){
                System.out.println("----------Invalid Time input-------------");
            }
        }
    }
    public void setEndTime(Scanner take){
        boolean check=true;
        while(check){
            System.out.print("End Time: ");
            String input=take.next();
            System.out.print("\n");
            try{
                endTime=LocalTime.parse(input);
                check=false;
            }catch(DateTimeParseException e){
                System.out.println("----------Invalid Time input-------------");
            }
        }
    }
    public void setRating(byte guestRating) throws IndexOutOfBoundsException{
        if(guestRating>=0 && guestRating<=10){
            rating=guestRating;
        }
        else{
            throw new IndexOutOfBoundsException("Rating must be (0->10)");
        }
    }
    
    //Functions
    public void takeOrder(Scanner get){
        boolean check=true;
        int menuId=-1;
        ArrayList<Menu> menues=Menu.getlist();
        for(int i=0;i<menues.size();i++){
            System.out.println(""+(i+1)+menues.get(i).Categ.toString());
        }
        System.out.println("Enter Number of the menu you want:");
        while(check){
            try{
                menuId=get.nextInt();
                check=false;
            }catch(InputMismatchException e){
                System.out.println("Invalid input(integer is required)");
                get.next();
            }
            if(menuId<0||menuId>menues.size()){
                System.out.println("Invalid input");
                check=true;
            }
        }
        check=true;
        Menu current=menues.get(menuId);
        System.out.println("Menu ELements:");
        for(int i=0;i<current.Meals.size();i++){
            System.out.println(""+(i+1)+current.Meals.get(i).getName());
        }
        System.out.println("Enter Number of the meal you want to add(Any other number to exit):");
        int i=1;
        while(i>0&&i<=current.Meals.size()){
            while(check){
                try{
                    i=get.nextInt();
                    check=false;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input(integer is required)");
                    get.next();
                }
            }
            check=true;
            order.add(current.Meals.get(i-1).getName());
            System.out.println("item is added successfully");
        }
        calculatePayment();
    }
    private void calculatePayment(){
        price+=Table.getTable(tableNum).getCost();
        for(int i=0;i<order.size();i++){
            //price+=order.get(i).getPrice();
        }
        price*=1.14; //taxes
    }
    public boolean isReserved(){
        return Table.getTable(tableNum).isReserved(date, startTime, endTime);
    }
    public boolean checkNoOfGuests(){
        return(numOfGuests<=Table.getTable(tableNum).getNoOfSeats());
    }

    //Getters
    public int getReservationNumber(){return reservationNum;}
    public Date getDate(){return date;}
    public LocalTime getStartTime(){return startTime;}
    public LocalTime getEndTime(){return endTime;}
    public double getPrice(){return price;}

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
            //if(history.get(i).tableNum==key.getTableId())
            //result.add(history.get(i));
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
    //Reading & Writing in binary files methods
    public static void getRecord(){
        try{
            FileInputStream f=new FileInputStream("Reservations archive.dat");
            ObjectInputStream in=new ObjectInputStream(f);
            int size=in.readInt();
            for(int i=0;i<size;i++){
                history.add((Reservation)in.readObject());
            }    
            in.close();
            f.close();
        }catch(IOException e){
            System.out.println("Error happened reading the file: Reservation archive");
        }catch(ClassNotFoundException e){
            System.out.println("Error in class Reservation reading compatiability");
        }
    }
    public static void saveRecords(){
        try{
            FileOutputStream f=new FileOutputStream("Reservations archive.dat");
            ObjectOutputStream out=new ObjectOutputStream(f);
            out.writeInt(history.size());
            for(int i=0;i<history.size();i++){
                out.writeObject(history.get(i));
            }    
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
        +"\nReservation No.: "+reservationNum+"\nTable No.: "+tableNum+"\nNo. of Guests: "+numOfGuests+
        "\nDate: "+date+"\nFrom: "+startTime+" To: "+endTime+"\nOrder: "+order.toString()+"\nCost: "+price+"\nGuests rating: "+rating);
    }
    @Override
    public int compareTo(Reservation right){
        if(reservationNum>right.reservationNum)return 1;
        else if(reservationNum<right.reservationNum)return -1;
        else return 0;
    }
}