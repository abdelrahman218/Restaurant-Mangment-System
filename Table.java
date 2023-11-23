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
    private static ArrayList<Table> Tables;
    private static int idGenerator=1;
    private int tableNum;
    private double Revenue;
    private Category Categ;
    private int NoOfSeats;
    private double Cost;
    public Table(int NoOfSeats){
        Revenue =0;
        tableNum=idGenerator++;
        Cost = 100;
        this.NoOfSeats=NoOfSeats;
        this.Categ=Category.Standard;
    }
    public static Table getTable(int tableNum){return Tables.get(tableNum);}
    public int getTableID(){return tableNum;}
    public int getNoOfSeats(){return NoOfSeats;}
    public double getCost(){return Cost;}
    public Category getcateg(){return Categ;}
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
        ArrayList<Reservation> reservations=getReservations();
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
    public void addToRevenue(){
        ArrayList<Reservation> reservations=getReservations();
         for(int i=0;i<reservations.size();i++){
             this.Revenue+=reservations.get(i).getPrice();
        }
    } 
    
    private int ReservationsInSpecificTime(Date StartDate, Date EndDate){
        int count=0;
        ArrayList<Reservation> reservations=getReservations();
        for(int i=0;i<reservations.size();i++)
            if(StartDate.before(reservations.get(i).getDate())||EndDate.after(reservations.get(i).getDate())
             ||StartDate.equals(reservations.get(i).getDate())||EndDate.equals(reservations.get(i).getDate())) 
                count++;
        return count;
    }
    // check update with Abdo
    private ArrayList<Reservation> getReservations(){
        ArrayList<Reservation> reservations=new ArrayList<>();
        for(int i=0;i<Reservation.getReservations().size();i++){
            if(Reservation.getReservations().get(i).getTableNumber()==tableNum){
                reservations.add(Reservation.getReservations().get(i));
            }
        }
        return reservations;
    }
    public static void saveInFile(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TablesData.dat"));
                out.writeObject(Table.Tables);
            out.close();
            } catch (IOException e) {
            System.out.println(e);
            }
    }
    public static void readFromFile(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("TablesData.dat"));
            Table.Tables=(ArrayList<Table>)in.readObject();
            in.close();
        }catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}