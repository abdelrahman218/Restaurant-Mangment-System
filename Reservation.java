package system;
import java.util.Date;
import java.time.LocalTime;
public class Reservation {
    private int reservationNum;
    private int tableNum;
    private int numOfGuests;
    private double price;
    private byte rating;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
    public Reservation(int reservationNum,int tableNum,int numOfGuests,double price,Date Date,LocalTime startTime,LocalTime endTime){
        this.reservationNum=reservationNum;
        this.tableNum=tableNum;
        this.numOfGuests=numOfGuests;
        this.price=price;
        this.date= Date;
        this.startTime=startTime;
        this.endTime=endTime;
    }
    public int getReservationNumber(){return reservationNum;}
    public Date getDate(){return date;}
    @Override
    public String toString(){
        return("Reservation No.: "+reservationNum+", Table No.: "+tableNum+", No. of Guests: "+numOfGuests+
        ", Cost: "+price+", Date: "+date+", From: "+startTime+" To: "+endTime+", Guests rating: "+rating);
    }
}
