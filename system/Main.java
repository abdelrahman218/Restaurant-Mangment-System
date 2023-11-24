package system;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.management.InvalidAttributeValueException;
import java.util.InputMismatchException;
import Employees.*;
import user.Guest;

public class Main {
    private static void drawMenu(String[] list){
        System.out.println("-------------------------------------------");
        for(int i=1;i<=list.length;i++){
            System.out.println(""+i+"- "+list[i-1]);
        }
        System.out.println("-------------------------------------------");
        System.out.println("Enter the Menu number you want to choose(1---->"+list.length+")");
    }
    private static int takeMenuIndex(Scanner take,int minValue,int maxValue){
        int x=0;
        boolean check=true;
        while(check){
            while(check){
                try{
                    x=take.nextInt();
                    check=false;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input (Integer is required)");
                    take.next();
                }
            }
            check=true;
            if(x>maxValue||x<minValue){
                System.out.println("Wrong Input!");
            }
            else check=false;
        }
        return x;
    }
    public static void main(String[] args) throws InvalidAttributeValueException, ParseException{
        Scanner s= new Scanner(System.in);
        new Receptionist("Abdo", "1211 Paris", "21-8-2004", "01211665660", "abdelrahman.alkot2182004@gmail.com", "A", "A");
        new Admin("John","13 NewYork","8-5-1962","01200588939","johnElbahrawy@gmail.com","John_62","Johny#487");
        drawMenu(new String[]{"Admins","Receptionist","Guest","Exit"});
        int x=takeMenuIndex(s, 1, 4);
       switch(x){
           case 1:menuAdmin(s);
           case 2:menuRec(s);
           case 3:menuGuest(s);
           case 4:System.exit(2);
        }

        s.close();
    }
    public static void menuGuest(Scanner s){
        int x;
        drawMenu(new String[]{"Sign up","Log in"});
        x=takeMenuIndex(s, 1, 2);
       switch(x){
           case 1: AddGuest(s);
           case 2:
        }
   }
   public static void AddGuest(Scanner s){
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
       new Guest(Name, Address, DateOfBirth, PhoneNum, Email, username, pass);
    }
    public static void menuRec(Scanner s) throws InvalidAttributeValueException, ParseException{
        int x;
        drawMenu(new String[]{"Log in","Exit"});
        x=takeMenuIndex(s, 1, 2);
        switch(x){
            case 1:
            loginReceptionist(s);
            break;
            case 2:
            System.exit(0);
            break;
        }
       
    }
   public static void menuAdmin(Scanner s){
    ArrayList<Admin> a=Admin.getAdmins();
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

    public static void loginReceptionist(Scanner s) throws InvalidAttributeValueException, ParseException{
        ArrayList<Receptionist> a=Receptionist.getList(); 
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
            Receptionistdetails(s, a.get(i).getName());
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
   drawMenu(new String[]{"Create Reservation","Cancel Reservation","Select Guest Category","Get Revenue","Get Number of Reservations done","Get Receptionist ID"});
   switch (s.nextInt()) {
    case 1:
     CreateReservation(s,index); 
     break; 
     case 2:
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
     System.out.println("Incompatible type Entered");
    }
}
}