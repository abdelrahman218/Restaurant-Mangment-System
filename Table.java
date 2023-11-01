public class Table {
    static Table Tables[]=new Table[50];
    private double Revenue;
    protected Category Categ;
    protected int NoOfSeats;
    private double Cost;
    private int TotalNoOfReservations;
    Table(String Category, int NoOfSeats){
        Revenue =0;
        Cost = 0;
        TotalNoOfReservations=0;
    }
    public static Table mostReservedTable(java.util.Date StartDate, java.util.Date EndDate){
        int max=0;
        for(int i=1;i<50;i++){
            if(Tables[i].TotalNoOfReservations>Tables[max].TotalNoOfReservations)
                max=i;
            
        }
        return Tables[max];
    }
    public static Table highestRevenue(java.util.Date StartDate, java.util.Date EndDate){
         int max=0;
        for(int i=1;i<50;i++){
            if(Tables[i].Revenue>Tables[max].Revenue)
                max=i;
            
        }
        return Tables[max];
    }
    public void addToRevenue(){
        Revenue+=Cost;
    }
}
