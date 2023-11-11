package system;
//Stream Classes
import java.util.Scanner;

//Data Structures
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.time.LocalTime;

//Self-Defined Classes
import system.Reservation;
import system.Table;
import system.Meal;

//Formater Classes
import java.text.SimpleDateFormat;

//Exeception Classes
import java.lang.IndexOutOfBoundsException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.lang.IndexOutOfBoundsException;

public class Reservation {
    private static int idGenerator=0;
    private int reservationNum;
    private Table reserved;
    private int tableNum;
    private ArrayList<Meal> order;
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
    }
    //Setters
    public void setTableNum(){
        boolean check=true;
        Scanner take=new Scanner(System.in);
        while(check){
            System.out.print("Table number: ");
            tableNum=take.nextInt();
            System.out.print("\n");
            try{
                reserved=Table.getTable(tableNum);
                check=false;
            }catch(IndexOutOfBoundsException e){
                System.out.println("Table not found!");
            }
        }
        Table.AddReservation(this, tableNum);
        take.close();
    }
    public void setNumOfGuests(){
        Scanner take=new Scanner(System.in);
        System.out.print("Number of Guests: ");
        numOfGuests=take.nextInt();
        System.out.print("\n");
        take.close();
    }
    public void setDate(){
        boolean check=true;
        Scanner take=new Scanner(System.in);
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
        take.close();
    }
    public void setStartTime(){
        boolean check=true;
        Scanner take=new Scanner(System.in);
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
        take.close();
    }
    public void setEndTime(){
        boolean check=true;
        Scanner take=new Scanner(System.in);
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
        take.close();
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
    public void takeOrder(){
        boolean check=true;
        int menuId=-1;
        ArrayList<Menu> menues=Menu.getlist();
        Scanner get=new Scanner(System.in);
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
            order.add(current.Meals.get(i-1));
            System.out.println("item is added successfully");
        }
        get.close();
        calculatePayment();
    }
    private void calculatePayment(){
        price+=reserved.getCost();
        for(int i=0;i<order.size();i++){
            price+=order.get(i).getPrice();
        }
        price*=1.14; //taxes
    }
    public boolean isReserved(){
        return reserved.isReserved(date, startTime, endTime);
    }
    public boolean checkNoOfGuests(){
        return(numOfGuests<=reserved.getNoOfSeats());
    }

    //Getters
    public int getReservationNumber(){return reservationNum;}
    public Date getDate(){return date;}
    public LocalTime getStartTime(){return startTime;}
    public LocalTime getEndTime(){return endTime;}
    public double getPrice(){return price;}

    //Overriden object functions
    @Override
    public String toString(){
        return("Reservation No.: "+reservationNum+", Table No.: "+tableNum+", No. of Guests: "+numOfGuests+
        ", Date: "+date+", From: "+startTime+" To: "+endTime+", Order: "+order.toString()+", Cost: "+price+", Guests rating: "+rating);
    }
}