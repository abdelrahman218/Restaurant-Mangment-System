package system;
import java.util.ArrayList;
import java.util.Scanner;
import Employees.*;
import user.Guest;

public class Main {
    private static void drawMenu(ArrayList<String> list){
        System.out.println("-------------------------------------------");
        for(int i=1;i<=list.size();i++){
            System.out.println(""+i+"- "+list.get(i-1));
        }
        System.out.println("-------------------------------------------");
        System.out.println("Enter the Menu number you want to choose(1---->"+list.size()+")");
    }
    public static void main(String[] args){
        Scanner s= new Scanner(System.in);
        ArrayList<Admin> Admins= new ArrayList<Admin>();
        Admin a1= new Admin("John","13 NewYork","8-5-1962","01200588939","johnElbahrawy@gmail.com","John_62","Johny#487");
        Admins.add(a1);
        ArrayList<String> menuList= new ArrayList<>();
        menuList.add("Admin");
        menuList.add("Receptionist");
        menuList.add("Guest");
        menuList.add("Exit");
        drawMenu(menuList);
        int x;
        while(true){
        x=s.nextInt();
        if(x>5&&x<0){
          System.out.println("Wrong Input!");
        }
       else break;
       }
       switch(x){
           case 1:menuAdmin(s,Admins);
           case 2:menuRec(s);
           case 3:menuGuest(s);
           case 4:System.exit(2); 
        }
        s.close();
    }
    public static void menuGuest(Scanner s){
        ArrayList<Guest> Guests = new ArrayList<Guest>();
        System.out.println("Choose your Action: ");
        ArrayList<String> menuList= new ArrayList<>();
        menuList.add("Sign Up");
        menuList.add("Log In");
        menuList.add("back");
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
    public static void menuRec(Scanner s){
        System.out.println("Choose your Action: ");
        System.out.println("1- Sign up\t2-Log in");
        int x;
        
       while(true){
       x=s.nextInt();
       if(x<4&&x>0){
          System.out.println("Wrong Input!");
       }
       else break;
       }
       switch(x){
           case 1:
           case 2:
       }
       s.close();
   }
   public static void menuAdmin(Scanner s,ArrayList<Admin> a){   
        Admin currentAdmin=null;
        System.out.println("Enter UserName :  ");
        while(currentAdmin==null){
            String data=s.next();
            for(int i=0;i<a.size();i++){
                if(a.get(i).getUserName()==data){
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
}