package system;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.InvalidAttributeValueException;

import java.util.Date;
import java.util.InputMismatchException;
import employees.*;
import user.Guest;
public class App {
    private static void drawMenu(String[] list){
        System.out.println("-------------------------------------------");
        for(int i=1;i<=list.length;i++){
            System.out.println(""+i+"- "+list[i-1]);
        }
        System.out.println("-------------------------------------------");
        System.out.println("Enter the Menu number you want to choose(1---->"+list.length+")");
    }
    public static void main(String[] args) throws InvalidAttributeValueException, ParseException{
        Scanner s= new Scanner(System.in);
        ArrayList<String> test=new ArrayList<>();
        new Receptionist("Abdo", "1211 Paris", "21-8-2004", "01211665660", "abdelrahman.alkot2182004@gmail.com", "A", "A");
        new Admin("John","13 NewYork","8-5-1962","01200588939","johnElbahrawy@gmail.com","John_62","Johny#487");
        System.out.println("Choose your Role: ");
        System.out.println("----------------------------------");
        System.out.println("1- Admin");
        System.out.println("2-Receptionist");
        System.out.println("3-Guest");
        System.out.println("4-Exit");
        System.out.println("----------------------------------");
        int x;
        while(true){
        x=s.nextInt();
        if(x>5&&x<0){
          System.out.println("Wrong Input!");
        }
       else break;
       }
       switch(x){
           case 1:menuAdmin(s,Admin.getAdmins());
           case 2:menuRec(s,Receptionist.getList());
           case 3:menuGuest(s);
           case 4:System.exit(2);
        }

        s.close();
    }
    public static void menuGuest(Scanner s){
        ArrayList<Guest> Guests = new ArrayList<Guest>();
        System.out.println("Choose your Action: ");
        System.out.println("1- Sign up\t2-Log in");
        int x;
       while(true){
       x=s.nextInt();
       if(x>3&&x<0){
          System.out.println("Wrong Input!");
       }
       else break;
       }
       switch(x){
           case 1: Guests.add(AddGuest(s));
           case 2:
       }
   }
   public static Guest AddGuest(Scanner s){
       String username,pass ,Name,Address,DateOfBirth,PhoneNum,Email;
       System.out.println("Enter UserName: ");
       username=s.next();
       System.out.println("Enter Password: ");
       pass=s.next();
       System.out.println("Enter Name: ");
       Name=s.next();
       System.out.println("Enter Address: ");
       Address=s.next();
       System.out.println("Enter Date of birth: ");
       DateOfBirth=s.next();
       System.out.println("Enter Phone Number: ");
       PhoneNum=s.next();
       System.out.println("Enter Email: ");
       Email=s.next();
       s.close();
       Guest Data=new Guest(Name, Address, DateOfBirth, PhoneNum, Email, username, pass);
       return Data;
    }
    public static void menuRec(Scanner s,ArrayList<Receptionist>a) throws InvalidAttributeValueException, ParseException{
        System.out.println("Choose your Action: ");
        System.out.println("1-Log in");
        System.out.println("2-exit");
        switch(s.nextInt()){
            case 1:
            loginReceptionist(s,a);
            break;
            case 2:
            System.exit(0);
            break;
        }
       
    }
   public static void menuAdmin(Scanner s,ArrayList<Admin> a){
    Admin currentAdmin=null;
    System.out.println("Enter UserName :  ");
    while(currentAdmin==null){
        String data=s.next();
        for(int i=0;i<a.size();i++){
            if(a.get(i).getUserName().equals(data)){
                currentAdmin=a.get(i);
            }

        }   
        System.out.println("Admin not found");    
    }
    while(true){   
        System.out.println("Enter password :  ");
        String notdata=s.next();
        if(currentAdmin.checkpassword(notdata)){
            System.out.println("Welcome Mr/Mrs "+currentAdmin.getName());
            break;
        }
        else{
            System.out.println("Incorrect Password");
        }
    }
}

    public static void loginReceptionist(Scanner s,ArrayList<Receptionist> a) throws InvalidAttributeValueException, ParseException{
         boolean check=true;
         while (check) {
         System.out.print("Enter your username : ");
         String user=s.next();
         System.out.println();
         for(int i=0;i<a.size();i++){
         if(a.get(i).getUserName().equals(user)){
         check=false;
         }
         }
         if(check==false){
         System.out.println("UserName Found");
         }
         else{
         System.out.println("UserName Not Found,Try again");
        }
         }
         while(true){
         System.out.println("Enter password :  ");
         String notdata=s.next();
         for(int i=0;i<a.size();i++){
         if(a.get(i).checkpassword(notdata)){
            System.out.println("Welcome Mr/Mrs "+a.get(i).getName());
            Receptionistdetails(s, a.get(i).getName());;
          }
         else{
            System.out.println("Incorrect Password");
             }
         }
         }
    }

   public static void Receptionistdetails(Scanner s,String Name) throws InvalidAttributeValueException, ParseException{
    ArrayList<Receptionist> Re=Receptionist.getList();     
    int index=0;
    for(int i=0;i<Re.size();i++){
        if(Re.get(i).getName().equals(Name)){
            index=i;
        }
   }
    
   System.out.println("Choose your Action: ");
   System.out.println("1-Create Reservation");
   System.out.println("2-Cancel Reservation");
   System.out.println("3-Select Guest Category");
   System.out.println("4-Get Revenue");
   System.out.println("5-Get Number of Reservations done");
   System.out.println("6-Get Receptionist ID");
   System.out.println("");
   switch (s.nextInt()) {
    case 1:
     CreateReservation(s,index); 
     break; 
     case 2:
     CancelReservation(s,index);
     break;
     case 3:
     selecguestcateg(s,index);
     break;
    }
   }
   public static void CreateReservation(Scanner s,int ind) throws InvalidAttributeValueException, ParseException{
    try{
   System.out.println("Enter Guest ID : ");
   int Gid=s.nextInt();
   System.out.println("Enter Table Number : ");
   int Tid=s.nextInt();
   System.out.println("Enter Number of Guests : ");
   int NoGuests=s.nextInt();
   System.out.println("Enter Date : ");
   String Date=s.next();
   System.out.println("Enter Start time : ");
   String Start=s.next();
   System.out.println("Enter End time : ");
   String End=s.next();   
   ArrayList<Meal>M=Meal.getList();
   System.out.println("Choose your meal : ");
   for(int i=0;i<M.size();i++){
    System.out.println((i+1)+"-"+M.get(i).getName());
   }
   ArrayList<Meal>Me=new ArrayList<>();
   while(true){
    int Mealno=s.nextInt();
    Me.add(M.get(Mealno));
    System.out.println("Anything Else?");
    String ans=s.next();
    if(ans.equalsIgnoreCase("no")){
     break;
    }
   }
    Receptionist.getList().get(ind).createReservation(Gid, Tid, NoGuests, Date, Start, End, Me);;
    }
    catch(InputMismatchException e){
     System.out.println("Incompatible type Entered and Reservation couldn't be made");
     return;
    }
}
   public static void CancelReservation(Scanner s,int ind) throws InvalidAttributeValueException{
   System.out.println("Enter Reservation number to be deleted : ");
   try {
     Receptionist.getList().get(ind).cancelReservation(s.nextInt());
   } catch (InvalidAttributeValueException e) {
     System.out.println("Reservation couldn't be made ");
     return;
   }
  
}
   public static void selecguestcateg(Scanner s,int ind){
   System.out.println("Enter guest Name : ");
   String n=s.next();
   System.out.println("Enter The Category : ");
   drawMenu(new String[]{"Standard","Couples","Family","Private"});
   String categ=s.next();
   ArrayList<Guest>G=Guest.getList();
   for(int i=0;i<G.size();i++){
    if(n==G.get(i).getName()){
        if(categ.equalsIgnoreCase("Standard")){
       // Receptionist.getList().get(ind).selectGuestPref(G.get(i), Category.Standard);
     } 
     }
     }


   }
   



}
