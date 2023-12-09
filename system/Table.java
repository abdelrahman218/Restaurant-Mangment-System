package system;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalTime;
public class Table implements Serializable {
    private static ArrayList<Table> Tables=new ArrayList<Table>();
    private static int idGenerator=0;
    private int tableNum;
    private double Revenue;
    private Category Categ;
    private int NoOfSeats;
    private double Cost;
    public Table(){
        Revenue =0;
        tableNum=Tables.size()+(++idGenerator);
        Cost = 100;
        this.NoOfSeats=4;
        this.Categ=Category.Standard;
        Tables.add(this);
    }
    public Table(int NoOfSeats){
        Revenue =0;
        tableNum=++idGenerator;
        Cost = 100;
        this.NoOfSeats=NoOfSeats;
        this.Categ=Category.Standard;
        Tables.add(this);
    }
    public Table(int NoOfSeats, Category data){
        Revenue =0;
        tableNum=++idGenerator;
        Cost = 100;
        this.NoOfSeats=NoOfSeats;
        this.Categ=data;
        Tables.add(this);
    }
    public int getTableID(){return tableNum;}
    public int getNoOfSeats(){return NoOfSeats;}
    public double getCost(){return Cost;}
    public Category getcateg(){return Categ;}
    public double getRevenue() {return Revenue;}
    public static ArrayList<Table> getlist() {return Tables;}
    public void setCateg(Category categ) {Categ = categ;}
    public void setNoOfSeats(int noOfSeats) {NoOfSeats = noOfSeats;}
    public void setCost(double cost) {Cost = cost;}
    public static Table getTable(int tableNum){return Tables.get(tableNum-1);}
    public static Table mostReservedTable(Date StartDate, Date EndDate){
        int max=0;
        for(int i=1;i<Tables.size();i++){
            if(Tables.get(i).ReservationsInSpecificTime(StartDate, EndDate)>Tables.get(max).ReservationsInSpecificTime(StartDate, EndDate)){
                max=i;
            }
        }
        return Tables.get(max);
    }  
    public static Table highestRevenue(Date StartDate,Date EndDate){
         int max=0;
        for(int i=1;i<50;i++){
            if(Tables.get(i).Revenue>Tables.get(max).Revenue)
                max=i;
            
        }
        return Tables.get(max);
    }
    public boolean isReserved(Date day,LocalTime start ,LocalTime end){
        ArrayList<Reservation> reservations=Reservation.search(this);
       for(int i=0;i<reservations.size();i++){
           if((reservations.get(i).getDate()==day)&&
                ((start.isBefore(reservations.get(i).getEndTime())&& (start.isAfter(reservations.get(i).getStartTime())))
                 ||(end.isAfter(reservations.get(i).getStartTime())&& end.isBefore(reservations.get(i).getStartTime()))
                 ||start.equals(reservations.get(i).getStartTime())
                 ||end.equals(reservations.get(i).getEndTime())))
               return true;
       }
        return false;
    }
    public static ArrayList<Table> searchByDate(Date day,LocalTime start ,LocalTime end){
        ArrayList<Table> availabletables=new ArrayList<>();
        for(int i=0;i<Tables.size();i++){
            if(!Tables.get(i).isReserved(day, start, end))
                availabletables.add(Tables.get(i));
        }    
        return availabletables;
    }
    public void addToRevenue(){
        ArrayList<Reservation> reservations=Reservation.search(this);
         for(int i=0;i<reservations.size();i++){
             this.Revenue+=reservations.get(i).getPrice();
        }
    } 
    public int ReservationsInSpecificTime(Date StartDate, Date EndDate){
        int count=0;
        ArrayList<Reservation> reservations=Reservation.search(this);
        for(int i=0;i<reservations.size();i++)
            if(StartDate.before(reservations.get(i).getDate())||EndDate.after(reservations.get(i).getDate())
             ||StartDate.equals(reservations.get(i).getDate())||EndDate.equals(reservations.get(i).getDate())) 
                count++;
        return count;
    }
    public static void saveRecords(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TablesData.dat"));
                out.writeObject(Tables);
            out.close();
            } catch(IOException e){
                System.out.println("Error happened reading the file: Receptionist archive");
            }
    }
    public static void getRecord(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("TablesData.dat"));
            Tables=(ArrayList<Table>)in.readObject();
            in.close();
        }catch(IOException e){
            System.out.println("Error happened reading the file: Receptionist archive");
        }catch(ClassNotFoundException e){
            System.out.println("Error in class Receptionist reading compatiability");
        }
    }
    @Override
    public String toString(){
        return ("Table Details:\n"+"Table Number: "+getTableID()+"\nNumber of seats: "+getNoOfSeats()+"\nCost: "+getCost()+"\nCategory: "+getcateg()+"\nRevenue: "+getRevenue());
    }
}