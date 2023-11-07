package System;

import java.util.ArrayList;
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
    public static Table mostReservedTable(java.util.Date StartDate, java.util.Date EndDate){
        int max=0;
        for(int i=1;i<50;i++){
            if(Tables.get(i).reservations.size()>Tables.get(max).reservations.size())
                max=i;
            
        }
        return Tables.get(max);
    }
    public static Table highestRevenue(java.util.Date StartDate, java.util.Date EndDate){
         int max=0;
        for(int i=1;i<50;i++){
            if(Tables.get(i).Revenue>Tables.get(max).Revenue)
                max=i;
            
        }
        return Tables.get(max);
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
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}