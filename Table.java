package System;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;
public class Table {
    private static ArrayList<Table> Tables;
    private ArrayList<Reservation> reservations;
    private double Revenue;
    private Category Categ;
    private int NoOfSeats;
    private double Cost;
    public Table(int NoOfSeats){
        reservations=null;
        Revenue =0;
        Cost = 100;
        this.NoOfSeats=NoOfSeats;
        this.Categ=Category.Standard;
    }
    public static void AddReservation(Reservation data,int tableNum){
       Tables.get(tableNum).reservations.add(data);
       
    }
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
        Revenue+=Cost;
    } 
    public static Table getTable(int tableNum){
        return Tables.get(tableNum);
    }
    public int getNoOfSeats(){
    return NoOfSeats;
    }
    public double getCost(){
        return Cost;
    } 
    public Category getcateg(){
        return Categ;
    }
    //New function to know the number of reservations from time to time
    private int ReservationsInSpecificTime(Date StartDate, Date EndDate){
        int count=0;
        for(int i=0;i<reservations.size();i++)
            if(StartDate.before(reservations.get(i).getDate())||EndDate.after(reservations.get(i).getDate())||
            StartDate.equals(reservations.get(i).getDate())||EndDate.equals(reservations.get(i).getDate())) 
                count++;
        return count;
    }

}