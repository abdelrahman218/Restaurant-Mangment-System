package Employees;
import system.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Receptionist extends Person{
    private ArrayList<Reservation> reservations;
    private double revenue;
    public Receptionist(String name,String address,String dateOfBirth,long Phone,String Email){
        super(name,address,dateOfBirth,Phone,Email);
    }
    public void createReservation(){
        boolean check=true;
        int reservationNum;
        int tableNum;
        int numOfGuests;
        double price;
        String input;
        Date Date;
        LocalTime startTime;
        LocalTime endTime;
        Scanner take=new Scanner(System.in);
        System.out.print("Reservation number: ");
        reservationNum=take.nextInt();
        System.out.print("\n");
        System.out.print("Table number: ");
        tableNum=take.nextInt();
        System.out.print("\n");
        System.out.print("Number of Guests: ");
        numOfGuests=take.nextInt();
        System.out.print("\n");
        System.out.print("Price: ");
        price=take.nextDouble();
        System.out.print("\n");
        while(check){
        System.out.print("Date (dd/mm/yyyy): ");
        input=take.next();
        System.out.print("\n");
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date=sdf.parse(input);
            check=false;
        }
        catch(ParseException e){
            System.out.println("----------Invalid Date input-------------");
        }}
        System.out.print("End Date: ");
        //endDate=take.nextInt();
        System.out.print("\n");

    }
}
