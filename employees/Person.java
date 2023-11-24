package system;
import java.util.ArrayList;
import java.util.Scanner;

import employees.*;
import user.Guest;
public class App {
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
        ArrayList<String> test=new ArrayList<>();
        drawMenu(test);
        ArrayList<Receptionist>Receptionists=Receptionist.getList();
        ArrayList<Admin> Admins= new ArrayList<Admin>();
        Receptionist r1=new Receptionist("Abdo", "1211 Paris", "21-8-2004", "01211665660", "abdelrahman.alkot2182004@gmail.com", "A", "A");
        Admin a1= new Admin("John","13 NewYork","8-5-1962","01200588939","johnElbahrawy@gmail.com","John_62","Johny#487");
        Admins.add(a1);
        Receptionists.add(r1);
        System.out.println("Choose your Role: ");
        System.out.println("1- Admin\t2-Receptionist\t3-Guest\t4-Exit");
        int x;
        while(true){
        x=s.nextInt();
        if(x>5&&x<0){
          System.out.println("Wrong Input!");
        }
       else break;
       }
       switch(x){
           case 1:menuAdmin(s);
           case 2:menuRec(s);
           case 3:menuGuest(s);
           case 4:
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
    public static void menuRec(Scanner s){
        System.out.println("Choose your Action: ");
        System.out.println("1-Log in");
        System.out.println("2-exit");
        switch(s.nextInt()){
            case 1:
            loginReceptionist(s);
            break;
            case 2:
            System.exit(0);
            break;
        }
       
   }
   public static void menuAdmin(Scanner s){
        System.out.println("Enter UserName :  ");
        s.next();
        System.out.println("Enter password :  ");
        s.next();

    }

   public static void loginReceptionist(Scanner s) {
        boolean check = false;
        while (!check) {
            System.out.print("Enter your username : ");
            String user = s.next();
            System.out.println();
            System.out.print("Enter you password : ");
            String pass = s.next();
            for (int i = 0; i < Receptionist.getList().size(); i++) {
                if (Receptionist.getList().get(i).getUserName().equals(user) && Receptionist.getList().get(i).getPassword().equals(pass)) {
                    check = true;
                    break;
                }
            }

            if (check == false) {
                System.out.println("login failed,Try again");
            }
        }
        System.out.println("login success");

    }

}
