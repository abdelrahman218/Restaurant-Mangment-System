package Employees;
//Stream Classes
import java.util.Scanner;

//Data Structures
import java.util.ArrayList;

//Self-Defined Classes
import system.Reservation;
import system.Table;

//Exeception Classes
import java.util.InputMismatchException;

public class Receptionist extends Person{
    private ArrayList<Reservation> reservations;
    private double revenue;
    public Receptionist(String name,String address,String dateOfBirth,String Phone,String Email){
        super(name,address,dateOfBirth,Phone,Email);
        reservations=new ArrayList<>();
    }
    public void createReservation(){
        Reservation r=new Reservation();
        r.setTableNum();
        r.setNumOfGuests();
        r.setDate();
        r.setStartTime();
        r.setEndTime();
        System.out.println("Reservation is made successfully.");
        System.out.println("Reservation details: ");
        System.out.println(r.toString());
        reservations.add(r);
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
        for(int i=0;i<reservations.size();i++){
            if(reservations.get(i).getReservationNumber()==reservationNum){
                reservations.remove(i);
                System.out.println("Reservation is deleted successfully");
                return;
            }
        }
        System.out.println("Reservation is not found!");
    }
    public void selectGuestPref(int guestId,Guest[] guests){
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

    }
}