package system;
import java.util.Date;
import java.time.LocalTime;
public class Reservation {
    private int reservationNum;
    private int tableNum;
    private int numOfGuests;
    private double price;
    private byte rating;
    private Date Date;
    private LocalTime startTime;
    private LocalTime endTime;
    public Reservation(int reservationNum,int tableNum,int numOfGuests,double price,Date Date,LocalTime startTime,LocalTime endTime){
        this.reservationNum=reservationNum;
        this.tableNum=tableNum;
        this.numOfGuests=numOfGuests;
        this.price=price;
        this.Date= Date;
        this.startTime=startTime;
        this.endTime=endTime;
    }
}
