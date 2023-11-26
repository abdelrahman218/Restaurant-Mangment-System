package system;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.management.InvalidAttributeValueException;
import java.util.InputMismatchException;
import Employees.*;
import user.Guest;
public class Main {
    public static void main(String[] args){
        begin();
        Scanner s= new Scanner(System.in);
        drawMenu(new String[]{"Admins","Receptionist","Guest","Exit"});
        switch(takeMenuIndex(s, 1, 4)){
            case 1:menuAdmin(s); break;
            case 2:menuRec(s);  break;
            case 3:menuGuest(s);    break;
            case 4:break;
        }   
        s.close();
        exit(0);
    }
    //Menu Design
    private static void drawMenu(String[] list){
        System.out.println("-------------------------------------------");
        for(int i=1;i<=list.length;i++){
            System.out.println(""+i+"- "+list[i-1]);
        }
        System.out.println("-------------------------------------------");
        System.out.println("Enter the Menu number you want to choose(1---->"+list.length+")");
    }
    private static void viewData(String[] list){
        System.out.println("-------------------------------------------");
        for(int i=1;i<=list.length;i++){
            System.out.println(list[i-1]);
        }
        System.out.println("-------------------------------------------");
    }
    
    //Menu Functionality
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
    
    //Saving and Retrieving Files
    private static void begin(){
        Guest.getRecord();
        Table.getRecord();
        Receptionist.getRecord();
        Admin.getRecord();
        Menu.ReadFromMenuFile();
        Meal.ReadFromFile();
    }
    private static void exit(int exitCode){
        Guest.saveRecords();
        Table.saveRecords();
        Receptionist.saveRecords();
        Admin.saveRecords();
        Menu.WriteInMenuFile();
        Meal.WriteInFile();
        System.exit(exitCode);
    }
 
    //Admin Part
    private static void menuAdmin(Scanner s){
        drawMenu(new String[]{"Sign up", "Log in", "Back"});
    switch (takeMenuIndex(s, 1, 3)) {
        case 1: AddAdmin(s);    menuAdmin(s);
        case 2: LoginAdmin(s);  menuAdmin(s);
        case 3: main(null);
    }
    }
    private static void AddAdmin(Scanner s){
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
        new Admin(Name, Address, DateOfBirth, PhoneNum, Email, username, pass);
    }
    private static void LoginAdmin(Scanner s){
        boolean check=true;
        Admin current=null;
        while (check) {
            System.out.print("Enter your username : ");
            String user=s.next();
            int i=0;
            for(;i<Admin.getAdmins().size();i++){
                if(Admin.getAdmins().get(i).getUserName().equals(user)){
                    check=false;
                    current = Admin.getAdmins().get(i);
                    break;
                }
            }
            if(check==true){
                System.out.println("UserName Not Found,Try again");
            }
        }
        while(true){
            System.out.println("Enter password :  ");
            String notdata=s.next();
                if(current.checkpassword(notdata)){
                    System.out.println("Welcome Mr/Mrs "+current.getName());
                    Admindetails(s,current);
                }
                else{
                    System.out.println("Incorrect Password");
                }
        }
    }
    private static void Admindetails(Scanner s,Admin current) {
        drawMenu(new String[]{"Admin Section","Guest Section","Receptionist Section","Menu Section","Table Section","back to main menu"});
        switch(takeMenuIndex(s, 1, 6)){
            case 1: AdminSection(s,current);    Admindetails(s, current);
            case 2: GuestSection(s,current);    Admindetails(s, current);
            case 3: ReceptionistSection(s,current); Admindetails(s, current);
            case 4: MenuSection(s,current); Admindetails(s, current);
            case 5: TableSection(s,current);    Admindetails(s, current);
            case 6: main(null);
        }
    }
    private static void AdminSection(Scanner s,Admin current){
        drawMenu(new String[]{"View Admin","Edit Admin", "Remove Admin","Back"});
        switch(takeMenuIndex(s, 1, 4)){
            case 1: viewAdmin(s,current);    AdminSection(s, current);
            case 2: editAdmin(s,current);  AdminSection(s,current);
            case 3: removeAdmin(s,current);    menuAdmin(s);
            case 4: Admindetails(s,current);
        }
    }
    private static void viewAdmin(Scanner s, Admin current) {
            viewData(new String[]{"Name: "+current.getName(),"Address : "+current.getAddress(),
            "Date of birth : "+current.getDateOfBirth(), "Phone Number : "+current.getPhoneNum(),
            "Email : "+current.getEmail(),"Username : "+current.getUserName()});
    }
    private static void editAdmin(Scanner s, Admin current) {
        drawMenu(new String[]{"Name","Address","Date of birth","Phone Number","Email","UserName","Back"});
            switch(takeMenuIndex(s, 1, 8)){
                case 1:current.setName(s.next()); AdminSection(s, current);
                case 2:current.setAddress(s.next());  AdminSection(s,current);
                case 3:current.setDateOfBirth(s.next());  AdminSection(s,current);
                case 4:current.setPhoneNum(s.next()); AdminSection(s,current);
                case 5:current.setEmail(s.next());    AdminSection(s,current);
                case 6:current.setUserName(s.next()); AdminSection(s,current);
                case 7:current.setPassword(s.next()); AdminSection(s,current);
                case 8:AdminSection(s,current);
            }
    }
    private static void removeAdmin(Scanner s, Admin current) {
        Admin.getAdmins().remove(current);
        System.out.println("Admin removed sucessfully!");
    }
    private static void GuestSection(Scanner s,Admin current) {
        drawMenu(new String[]{"View guest","Edit guest", "Remove guest","back"});
        switch(takeMenuIndex(s, 1, 4)){
            case 1: viewGuest(s);    GuestSection(s,current);
            case 2: editGuest(s,current);  GuestSection(s,current);
            case 3: removeGuest(s,current);    GuestSection(s,current);
            case 4: Admindetails(s,current);
        }
    }
    private static void viewGuest(Scanner s) {
        System.out.print("Enter the Guest ID : ");
        try {
            Guest x=Guest.getGuest(s.nextInt());
            viewData(new String[]{"Name: "+x.getName(),"Address : "+x.getAddress(),"Date of birth : "+x.getDateOfBirth(), "Phone Number : "+x.getPhoneNum(),"Email : "+x.getEmail(),"Username : "+x.getUserName(),"Password : "+x.getPassword()});
        } catch (NullPointerException e) {
            System.out.println("Guest not found!");
        }   
    }
    private static void editGuest(Scanner s,Admin current) {
        System.out.print("Enter the Guest ID : ");
        try {
            Guest x= Guest.getGuest(s.nextInt());
            drawMenu(new String[]{"Name","Address","Date of birth","Phone Number","Email","UserName","Back"});
            switch(takeMenuIndex(s, 1, 8)){
                case 1:x.setName(s.next()); GuestSection(s,current);
                case 2:x.setAddress(s.next());  GuestSection(s,current);
                case 3:x.setDateOfBirth(s.next());  GuestSection(s,current);
                case 4:x.setPhoneNum(s.next()); GuestSection(s,current);
                case 5:x.setEmail(s.next());    GuestSection(s,current);
                case 6:x.setUserName(s.next()); GuestSection(s,current);
                case 7:x.setPassword(s.next()); GuestSection(s,current);
                case 8:GuestSection(s,current);
            }
        } catch (NullPointerException e) {
            System.out.println("Guest not found!");
            GuestSection(s,current);
        }
    }
    private static void removeGuest(Scanner s,Admin current) {
        System.out.print("Enter the Guest ID : ");
        try {
             Guest.getList().remove(Guest.getGuest(s.nextInt()));           
        } catch (NullPointerException e) {
            System.out.println("Guest not found!");
            GuestSection(s,current);
        } 
    }
    private static void ReceptionistSection(Scanner s,Admin current) {
        drawMenu(new String[]{"View receptionist","Edit receptionist", "Remove receptionist","back"});
        switch(takeMenuIndex(s, 1, 4)){
            case 1:{viewReceptionist(s);    ReceptionistSection(s,current);}
            case 2: {editReceptionist(s,current);  ReceptionistSection(s,current);}
            case 3: {removeReceptionist(s,current);    ReceptionistSection(s,current);}
            case 4: Admindetails(s,current);
        }
    }
    private static void viewReceptionist(Scanner s){
        System.out.print("Enter the Receptionist ID : ");
        try {
            System.out.println(Receptionist.search(s.nextInt()).toString());
        } catch (NullPointerException e) {
            System.out.println("Receptionist not found!");
        }
    }
    private static void editReceptionist(Scanner s,Admin current) {
       System.out.print("Enter the Receptionist ID : ");
        try {
            Receptionist x= Receptionist.search(s.nextInt());
            drawMenu(new String[]{"Name","Address","Date of birth","Phone Number","Email","UserName","Password","Back"});
            switch(takeMenuIndex(s, 1, 8)){
                case 1:x.setName(s.next()); ReceptionistSection(s,current);
                case 2:x.setAddress(s.next());  ReceptionistSection(s,current);
                case 3:x.setDateOfBirth(s.next());  ReceptionistSection(s,current);
                case 4:x.setPhoneNum(s.next()); ReceptionistSection(s,current);
                case 5:x.setEmail(s.next());    ReceptionistSection(s,current);
                case 6:x.setUserName(s.next()); ReceptionistSection(s,current);
                case 7:x.setPassword(s.next()); ReceptionistSection(s,current);
                case 8:ReceptionistSection(s,current);
            }
        } catch (NullPointerException e) {
            System.out.println("Guest not found!"); 
            GuestSection(s,current);
        } 
    }
    private static void removeReceptionist(Scanner s,Admin current) {
        System.out.print("Enter the Receptionist ID : ");
        try {
             Receptionist.getList().remove(Receptionist.search(s.nextInt()));
             System.out.println("Receptionist removed sucessfully!");           
        } catch (NullPointerException e) {
            System.out.println("Receptionist not found!");
            ReceptionistSection(s,current);
        }
    }
    private static void MenuSection(Scanner s,Admin current) {
    drawMenu(new String[]{"View Menu","Edit Menu","Add Menu","Add Meal","Back"});
    switch (takeMenuIndex(s, 1, 5)) {
       case 1:{viewMenu(s); MenuSection(s,current);}
       case 2:{EditMenu(s,current); MenuSection(s,current);}
       case 3:{AddMenu(s,current);  MenuSection(s,current);}
       case 4:{Addmeal(s);  MenuSection(s,current);}
       case 5:Admindetails(s,current);
    }
   }
    private static void viewMenu(Scanner s){
    System.out.print("Enter Menu Id : ");
    int x=s.nextInt();
    while(x<1||x>Menu.getlist().size()){
        System.out.println("Invalid Input! (Enter an Integer between 1 -> "+Menu.getlist().size()+" )");
        x=s.nextInt();
    }
    System.out.println(Menu.getlist().get(x).toString());
   } 
    private static void EditMenu(Scanner s,Admin current){
   System.out.println("Enter Menu Id : ");
   int id=s.nextInt();
   while(id<1||id>Menu.getlist().size()){
        System.out.println("Invalid Input! (Enter an Integer between 1 -> "+Menu.getlist().size()+" )");
        id=s.nextInt();
    }
    Menu x=Menu.getlist().get(id);
   drawMenu(new String[]{"Breakfast","Lunch","Dinner","Beverages","Dessert","Back"});
        switch(takeMenuIndex(s, 1, 6)){
            case 1:x.setCateg(MenuCategory.Breakfast);
            case 2:x.setCateg(MenuCategory.Lunch);
            case 3:x.setCateg(MenuCategory.Dinner);
            case 4:x.setCateg(MenuCategory.Beverages);
            case 5:x.setCateg(MenuCategory.Dessert);
            case 6:editTable(s,current);
        }
   } 
    private static void AddMenu(Scanner s,Admin current){
       drawMenu(new String[]{"Breakfast","Lunch","Dinner","Beverages","Dessert","Back"});
       int x=takeMenuIndex(s, 1, 6);
       if (x==6)
         MenuSection(s,current);
       Admin.addMenu();
   }
    private static void Addmeal(Scanner s){
    System.out.println("Enter Menu id : ");
    int menu=s.nextInt();
    System.out.println("Enter Meal id : ");
    int meal=s.nextInt();
    System.out.println("Enter Meal Name : ");
    String name=s.next();
    System.out.println("Enter Price : ");
    double price = s.nextDouble();
    new Meal(menu, meal, name, price);
   }
    private static void TableSection(Scanner s,Admin current)  {
        drawMenu(new String[]{"View table","Edit table", "Remove table","back"});
        switch(takeMenuIndex(s, 1, 3)){
            case 1:{viewTable(s);    TableSection(s,current);}
            case 2: {editTable(s,current);  TableSection(s,current);}
            case 3: {removeTable(s,current);    TableSection(s,current);}
            case 4: Admindetails(s,current);
        }
    }
    private static void viewTable(Scanner s){
        System.out.print("Enter the Table Number : ");
        try {
            System.out.println(Table.getTable(s.nextInt()).toString());
        } catch (NullPointerException e) {
            System.out.println("Table not found!");
        }   
    }
    private static void editTable(Scanner s,Admin current) {
        System.out.print("Enter the table number : ");
        try {
            Table x=Table.getTable(s.nextInt());
            drawMenu(new String[]{"Number of seats","Cost","Category","Back"});
            switch(takeMenuIndex(s, 1, 4)){
                case 1:x.setNoOfSeats(s.nextInt()); TableSection(s,current);
                case 2:x.setCost(s.nextDouble());   TableSection(s,current);
                case 3:editCategory(s,x,s.next(),current);  TableSection(s,current);
                case 4:TableSection(s,current);
            }
        } catch (NullPointerException e) {
            System.out.println("Table not found!"); 
        } 
    }
    private static void editCategory(Scanner s,Table x,String categ,Admin current) {
        drawMenu(new String[]{"Standard","Couples","Family","Private","Back"});
        switch(takeMenuIndex(s, 1, 4)){
            case 1:x.setCateg(Category.Standard);
            case 2:x.setCateg(Category.Couples);
            case 3:x.setCateg(Category.Family);
            case 4:x.setCateg(Category.Private);
            case 5:editTable(s,current);
        }
    }
    private static void removeTable(Scanner s,Admin current)  {
        System.out.print("Enter the Table Number : ");
        try {
             Table.getlist().remove(Table.getTable(s.nextInt()));           
        } catch (NullPointerException e) {
            System.out.println("Table not found!"); 
            GuestSection(s,current);
        }
    }

    //Receptionist Part
    private static void menuRec(Scanner s) {
        drawMenu(new String[]{"Log in","Back"});
        switch(takeMenuIndex(s, 1, 2)){
            case 1: loginReceptionist(s);   menuRec(s);
            case 2: main(null);
        }   
    }
    private static void loginReceptionist(Scanner s) {
        boolean check=true;
        Receptionist current=null;
        while (check) {
         System.out.print("Enter your username : ");
         String user=s.next();
         System.out.println();
         for(int i=0;i<Receptionist.getList().size();i++){
         if(Receptionist.getList().get(i).getUserName().equals(user)){
         current=Receptionist.getList().get(i);
         check=false;
         }
         }
         if(check==true){
         System.out.println("UserName Not Found,Try again");
         }
         }
         while(true){
         System.out.println("Enter password :  ");
         String notdata=s.next();
         if(current.checkpassword(notdata)){
            System.out.println("Welcome Mr/Mrs "+current.getName());
            Receptionistdetails(s, current);
            break;
          }
         else{
            System.out.println("Incorrect Password");
            }
        }
    }
    private static void Receptionistdetails(Scanner s,Receptionist current) {
        drawMenu(new String[]{"Create Reservation","Cancel Reservation","Select Guest Category","Get Revenue","Get Number of Reservations done","Exit"});
        switch (takeMenuIndex(s, 1, 6)) {
            case 1: CreateReservation(s,current);   Receptionistdetails(s,current); 
            case 2: CancelReservation(s,current);   Receptionistdetails(s, current);
            case 3: selecguestcateg(s,current); Receptionistdetails(s, current);
            case 4: System.out.println( "The Revenue is : "+current.getRevenue());  Receptionistdetails(s, current);
            case 5: System.out.println("Number of Reservations are : "+current.getreservationsCount()); Receptionistdetails(s,current);
            case 6: main(null);
        }
    }
    private static void CreateReservation(Scanner s,Receptionist current) {
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
    System.out.println("Meal Number: ");
   while(true){
    int Mealno=takeMenuIndex(s, 1, M.size());
    Me.add(M.get(Mealno-1));
    System.out.println("Anything Else?");
    String ans=s.next();
    if(ans.equalsIgnoreCase("no")){
        break;
    }else if(ans.equalsIgnoreCase("yes")){
        System.out.println("Meal Number: ");
    }
   }
    current.createReservation(Gid, Tid, NoGuests, Date, Start, End, Me);
    }
    catch(InputMismatchException e){
     System.out.println("Incompatible type Entered and Reservation couldn't be made");
    }
    catch(InvalidAttributeValueException e){
        System.out.println("Reservation couldn't be made!   Reason: "+e.getMessage());
    }
    catch(ParseException e){
        System.out.println("Reservation couldn't be made!   Reason: "+e.getMessage());
    }

}
    private static void CancelReservation(Scanner s,Receptionist current) {
   System.out.println("Enter Reservation number to be deleted : ");
   try {
     current.cancelReservation(s.nextInt());
   } catch (InvalidAttributeValueException e) {
     System.out.println("Reservation couldn't be found");
     return;
   }
  
}
    private static void selecguestcateg(Scanner s, Receptionist current){
        Guest x=null;
       while(x==null){
            try{
            System.out.print("Enter guest ID : ");
            int n=s.nextInt();
            x=Guest.getList().get(n);
            }catch(NullPointerException e){
                System.out.println("Guest not Found!");
            }catch(InputMismatchException e){
                System.out.println("Invalid Input! (Enter an Integer)");
            }
        }
    drawMenu(new String[]{"Standard","Couples","Family","Private","Back"});
        switch(takeMenuIndex(s, 1, 4)){
            case 1:x.setPreferedCategory(0);    Receptionistdetails(s, current);
            case 2:x.setPreferedCategory(1);    Receptionistdetails(s, current);
            case 3:x.setPreferedCategory(2);    Receptionistdetails(s, current);
            case 4:x.setPreferedCategory(3);    Receptionistdetails(s, current);
            case 5:Receptionistdetails(s, current);
        }
    }
    
    //Guest Part
    private static void menuGuest(Scanner s){
            drawMenu(new String[]{"Sign up", "Log in", "Back"});
            switch (takeMenuIndex(s, 1, 3)) {
                case 1:AddGuest(s); menuGuest(s);
                case 2: loginGuest(s); menuGuest(s);
                case 3: main(null);
        }
    }
    private static void AddGuest(Scanner s){
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
    private static void loginGuest(Scanner s){
    boolean check = false;
    Guest current=null;
    while (!check) {
        System.out.print("Enter your username : ");
        String user = s.next();
        System.out.println();
        System.out.print("Enter your password : ");
        String pass = s.next();
        for (int i = 0; i < Guest.getList().size(); i++) {
            if (Guest.getList().get(i).getUserName().equals(user) && Guest.getList().get(i).getPassword().equals(pass)) {
                check = true;
                current=Guest.getList().get(i);
                break;
            }
        }
        if (!check) {
            System.out.println("Login failed. Try again");
        }
    }
    System.out.println("Login success");
    Guestdetails(s,current);
    }
    private static void Guestdetails(Scanner s,Guest current){
        drawMenu(new String[]{"View Your Reservation's History","Rate Your Booking","back to main menu"});
        switch(takeMenuIndex(s, 1, 3)){
            case 1: View_Your_Reservation_History(s,current);   Guestdetails(s, current);
            case 2: RateBooking(s,current); Guestdetails(s, current);
            case 3: main(null);
        }
    }
    private static void View_Your_Reservation_History(Scanner s,Guest currentGuest) {
    String history = currentGuest.ViewReservation();
    System.out.println("Your Reservation History: " + history);
    }
    private static void RateBooking(Scanner s,Guest current){
        boolean check=true;
        ArrayList<Reservation> guestReservations=Reservation.search(current);
        if(guestReservations.size()==0){
            System.out.println("No reservations done!");
            return;
        }
        String[] resDates=new String[guestReservations.size()];
        for(int i=0;i<guestReservations.size();i++){
            resDates[i]=guestReservations.get(i).getDate().toString();
        }
        drawMenu(resDates);
        while(check){
            try{
                int x=s.nextInt();
                while(x<1||x>guestReservations.size()){
                    System.out.println("Invalid input (Choose Number between 1 -> )"+guestReservations.size());
                    x=s.nextInt();
                }
                System.out.println("Enter the Rating : ");
                guestReservations.get(x).setRating(s.nextByte());
                check=false;
            }catch(InvalidAttributeValueException e){
                System.out.println(e.getMessage());
                s.next();
            }catch(InputMismatchException IE){
                System.out.println("Invalid input (Integer is required)");
                s.next();
            }
        }
    }
}