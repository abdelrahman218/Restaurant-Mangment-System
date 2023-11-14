package Employees;
//Stream Classes
import java.util.Scanner;

//Data Structures
import java.util.ArrayList;
import java.util.Collections;

//Self-Defined Classes
import system.Reservation;
import system.Table;
import user.Guest;

//Exeception Classes
import java.util.InputMismatchException;

public class Receptionist extends Person implements Comparable<Receptionist>{
    private static ArrayList<Receptionist> receptionists=new ArrayList<>();
    private double revenue;
    public Receptionist(String name,String address,String dateOfBirth,String Phone,String Email){
        super(name,address,dateOfBirth,Phone,Email);
        receptionists.add(this);
    }
    public void createReservation(){
        boolean check=true;
        Reservation r=new Reservation();
        r.setReceptionistId(getId());
        r.setGuestId();
        while(check){
            r.setTableNum();
            r.setNumOfGuests();
            if(r.checkNoOfGuests()){
                check=false;
            }else{
                System.out.println("Invalid number of guest for current table");
            }
        }
        check=true;
        while(check){
            r.setDate();
            r.setStartTime();
            r.setEndTime();
            if(!r.isReserved()){
                check=false;
            }
            else{
                System.out.println("Table isn't available at specified Time");
            }
        }
        r.takeOrder();
        revenue+=r.getPrice();
        System.out.println("Reservation is made successfully.");
        System.out.println("Reservation details: ");
        System.out.println(r.toString());
    }
    public void cancelReservation(){
        boolean check=true;
        int reservationNum=-1;
        Scanner take=new Scanner(System.in);
        while(check){
            System.out.println("Enter Reservation Number to be deleted: ");
            try{
                reservationNum=take.nextInt();
                check=false;
                take.close();
            }catch(InputMismatchException e){
                System.out.println("Invalid Input Type [integer input is required]");
            }
        }
        ArrayList<Reservation> reservations=Reservation.search(this);
        for(int i=0;i<reservations.size();i++){
            if(reservations.get(i).getReservationNumber()==reservationNum){
                reservations.remove(i);
                System.out.println("Reservation is deleted successfully");
                return;
            }
        }
        System.out.println("Reservation is not found!");
    }
    public void selectGuestPref(){
        Scanner get=new Scanner(System.in);
        boolean check=true;
        int key=0;
        while(check){
            try{
                System.out.print("Enter the ID of the Guest: ");
                key=get.nextInt();
                check=false;
            }catch(InputMismatchException e){
                System.out.println("Invalid Input [integer is required]");
                get.next();
            }
        }
        check=true;
        Guest referenced= Guest.getGuest(key);
        if(referenced==null){
            System.out.println("Guest couldn't be found!");
        }
        else{
            while(check){
                try{
                    System.out.println("1-Standard\n2-Couples\n3-Family\n4-Private");
                    System.out.print("Enter Number of prefered category: ");
                    key=(get.nextInt()-1);
                    check=false;
                }catch(InputMismatchException e){
                    System.out.println("Invalid Input [integer is required]");
                    get.next();
                }
                if(key<0||key>3){
                    System.out.println("Invalid input [(1->4) is required]");
                    check=true;
                }
                else{
                    referenced.setPreferedCategory(key);
                }
            }
        }
        get.close();
    }
    public static Receptionist search(int id){
        Collections.sort(receptionists);
        int first=0;
        int last=receptionists.size()-1;
        int mid;
        while(first<=last){
            mid=(int)((first+last)/2);
            if(id>receptionists.get(mid).getId())
            first=mid+1;
            else if(id<receptionists.get(mid).getId())
            last=mid-1;
            else return receptionists.get(mid);
        }
        return null;    
    }
    @Override
    public int compareTo(Receptionist right){
        if(getId()>right.getId())return 1;
        else if(getId()<right.getId())return -1;
        else return 0;
    }
}