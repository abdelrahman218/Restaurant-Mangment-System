package system;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.management.InvalidAttributeValueException;
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
    private static void viewData(String[] list){
        System.out.println("-------------------------------------------");
        for(int i=1;i<=list.length;i++){
            System.out.println(list[i-1]);
        }
        System.out.println("-------------------------------------------");
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
    public static void main(String[] args){
        // Guest.getRecord();
        // Table.getRecord();
        // Receptionist.getRecord();
        // Admin.getRecord();
        // Menu.ReadFromMenuFile();
        // Meal.ReadFromFile();
        Scanner s= new Scanner(System.in);
        new Receptionist("Abdo", "Cairo", "21-8-2004", "01211665660", "abdelrahman.alkot2182004@gmail.com", "A", "A");
        new Guest("Frank", "Paris", "19-2-2001", "01069856960", "frank.heisenberg@gmail.com", "A", "A");
        new Guest("John","Chicago","8-5-1962","01200588939","johnwhite@gmail.com","A","A");
        new Admin("Michel","NewYork","27-12-1981","01165863239","michel_81@gmail.com","A","A");
        new Table(4,Category.Private);
        new Table(2);
        new Table(6);
        new Meal(1,1,"Egg",3.5);
        new Meal(1,2,"Bread",1.5);
        new Meal(2,3,"spiro spathis",10);
        new Menu(MenuCategory.Breakfast);
        new Menu(MenuCategory.Beverages);
        // Guest.saveRecords();
        // Table.saveRecords();
        // Receptionist.saveRecords();
        // Admin.saveRecords();
        // Menu.WriteInMenuFile();
        // Meal.WriteInFile();
        drawMenu(new String[]{"Admins","Receptionist","Guest","Exit"});
        switch(takeMenuIndex(s, 1, 4)){
            case 1:menuAdmin(s);
            case 2:menuRec(s);
            case 3:menuGuest(s);
            case 4:System.exit(0);
        }
        s.close();
    }   
    public static void menuAdmin(Scanner s){
    boolean check=true;
    Admin.getRecord();
         while (check) {
         System.out.print("Enter your username : ");
         String user=s.next();
         int i=0;
         for(;i<Admin.getAdmins().size();i++){
         if(Admin.getAdmins().get(i).getUserName().equals(user)){
         check=false;
         break;
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
         for(int i=0;i<Admin.getAdmins().size();i++){
         if(Admin.getAdmins().get(i).checkpassword(notdata)){
            System.out.println("Welcome Mr/Mrs "+Admin.getAdmins().get(i).getName());
            Admindetails(s);
          }
         else{
            System.out.println("Incorrect Password");
             }
         }
         }
}
    public static void Admindetails(Scanner s) {
        drawMenu(new String[]{"Guest Section","Receptionist Section","Menu Section","Table Section","back to main menu"});
        switch(takeMenuIndex(s, 1, 5)){
            case 1: GuestSection(s);
            case 2: ReceptionistSection(s);
            case 3: MenuSection(s);
            case 4: TableSection(s);
            case 5: main(null);
        }
    }
    public static void GuestSection(Scanner s) {
        drawMenu(new String[]{"View guest","Edit guest", "Remove guest","back"});
        switch(takeMenuIndex(s, 1, 3)){
            case 1:{viewGuest(s);    GuestSection(s);}
            case 2: {editGuest(s);  GuestSection(s);}
            case 3: {removeGuest(s);    GuestSection(s);}
            case 4: Admindetails(s);
        }
    }
    public static void viewGuest(Scanner s) {
        System.out.print("Enter the Guest ID : ");
        try {
            Guest x=Guest.getGuest(s.nextInt());
            viewData(new String[]{"Name: "+x.getName(),"Address : "+x.getAddress(),"Date of birth : "+x.getDateOfBirth(), "Phone Number : "+x.getPhoneNum(),"Email : "+x.getEmail(),"Username : "+x.getUserName(),"Password : "+x.getPassword()});
        } catch (NullPointerException e) {
            System.out.println("Guest not found!");
        }   
    }
    public static void editGuest(Scanner s) {
        System.out.print("Enter the Guest ID : ");
        try {
            Guest x= Guest.getGuest(s.nextInt());
            drawMenu(new String[]{"Name","Address","Date of birth","Phone Number","Email","UserName","Back"});
            switch(takeMenuIndex(s, 1, 7)){
                case 1:x.setName(s.next()); break;
                case 2:x.setAddress(s.nextLine());  break;
                case 3:x.setDateOfBirth(s.nextLine());  break;
                case 4:x.setPhoneNum(s.nextLine()); break;
                case 5:x.setEmail(s.nextLine());    break;
                case 6:x.setUserName(s.nextLine()); break;
                case 7:GuestSection(s); break;
            }
        } catch (NullPointerException e) {
            System.out.println("Guest not found!");
            GuestSection(s);
        }
    }
    public static void removeGuest(Scanner s) {
        System.out.print("Enter the Guest ID : ");
        try {
             Guest.getList().remove(Guest.getGuest(s.nextInt()));           
        } catch (NullPointerException e) {
            System.out.println("Guest not found!");
            GuestSection(s);
        } 
    }
    public static void ReceptionistSection(Scanner s) {
        drawMenu(new String[]{"View receptionist","Edit receptionist", "Remove receptionist","back"});
        switch(takeMenuIndex(s, 1, 3)){
            case 1:{viewReceptionist(s);    ReceptionistSection(s);}
            case 2: {editReceptionist(s);  ReceptionistSection(s);}
            case 3: {removeReceptionist(s);    ReceptionistSection(s);}
            case 4: Admindetails(s);
        }
    }
    public static void viewReceptionist(Scanner s){
        System.out.print("Enter the Receptionist ID : ");
        try {
            Receptionist.search(s.nextInt()).toString();
        } catch (NullPointerException e) {
            System.out.println("Receptionist not found!");
        }
    }
    public static void editReceptionist(Scanner s) {
       System.out.print("Enter the Receptionist ID : ");
        try {
            Receptionist x= Receptionist.search(s.nextInt());
            drawMenu(new String[]{"Name","Address","Date of birth","Phone Number","Email","UserName","Password","Back"});
            switch(takeMenuIndex(s, 1, 8)){
                case 1:x.setName(s.nextLine());
                case 2:x.setAddress(s.nextLine());
                case 3:x.setDateOfBirth(s.nextLine());
                case 4:x.setPhoneNum(s.nextLine());
                case 5:x.setEmail(s.nextLine());
                case 6:x.setUserName(s.nextLine());
                case 7:x.setPassword(s.nextLine());
                case 8:ReceptionistSection(s);
            }
        } catch (NullPointerException e) {
            System.out.println("Guest not found!"); 
            GuestSection(s);
        } 
    }
    public static void removeReceptionist(Scanner s) {
        System.out.print("Enter the Receptionist ID : ");
        try {
             Receptionist.getList().remove(Receptionist.search(s.nextInt()));           
        } catch (NullPointerException e) {
            System.out.println("Guest not found!");
            ReceptionistSection(s);
        }
    }
    public static void MenuSection(Scanner s) {
    drawMenu(new String[]{"View Menu","Edit Menu","Add Menu","Add Meal","Back"});
    switch (takeMenuIndex(s, 1, 5)) {
       case 1:{viewMenu(s); MenuSection(s);}
       case 2:{EditMenu(s); MenuSection(s);}
       case 3:{AddMenu(s);  MenuSection(s);}
       case 4:{Addmeal(s);  MenuSection(s);}
       case 5:Admindetails(s);
    }
   }
    public static void viewMenu(Scanner s){
    System.out.print("Enter Menu Id : ");
    Admin.viewMenuReportsCateg(s.nextInt());
   } 
    public static void EditMenu(Scanner s){
   System.out.println("Enter Menu Id : ");
   int id=s.nextInt();
   System.out.println("Enter New Category : ");
   String Name=s.next();
   if(Name.equalsIgnoreCase("Breakfast")){
       MenuCategory m=MenuCategory.Breakfast;
       Admin.getAdmins().get(0).editMenu(id, m);
   }else if(Name.equalsIgnoreCase("Lunch")){
       MenuCategory m=MenuCategory.Lunch;
       Menu.getlist().add(new Menu(m));
   }else if(Name.equalsIgnoreCase("Dinner")){
       MenuCategory m=MenuCategory.Dinner;
       Admin.getAdmins().get(0).editMenu(id, m);
   }else if(Name.equalsIgnoreCase("Beverages")){
       MenuCategory m=MenuCategory.Beverages;
       Admin.getAdmins().get(0).editMenu(id, m);
   }else if(Name.equalsIgnoreCase("Dessert")){
       MenuCategory m=MenuCategory.Dessert;
       Admin.getAdmins().get(0).editMenu(id, m);
   }

   } 
    public static void AddMenu(Scanner s){
       drawMenu(new String[]{"Breakfast","Lunch","Dinner","Beverages","Dessert"});
       int x=s.nextInt();
       Admin.addMenu(x);
   }
    public static void Addmeal(Scanner s){
    System.out.println("Enter Menu id : ");
    int menu=s.nextInt();
    System.out.println("Enter Meal id : ");
    int meal=s.nextInt();
    System.out.println("Enter Meal Name : ");
    String name=s.next();
    System.out.println("Enter Price : ");
    double price = s.nextDouble();
    Meal.getList().add(new Meal(menu, meal, name, price));
   }
    public static void TableSection(Scanner s)  {
        drawMenu(new String[]{"View table","Edit table", "Remove table","back"});
        switch(takeMenuIndex(s, 1, 3)){
            case 1:{viewTable(s);    TableSection(s);}
            case 2: {editTable(s);  TableSection(s);}
            case 3: {removeTable(s);    TableSection(s);}
            case 4: Admindetails(s);
        }
    }
    public static void viewTable(Scanner s){
        System.out.print("Enter the Table Number : ");
        try {
            Table.getTable(s.nextInt()).toString();
        } catch (NullPointerException e) {
            System.out.println("Table not found!");
        }   
    }
    public static void editTable(Scanner s) {
        System.out.print("Enter the table number : ");
        try {
            Table x=Table.getTable(s.nextInt());
            drawMenu(new String[]{"Number of seats","Cost","Category","Back"});
            switch(takeMenuIndex(s, 1, 4)){
                case 1:x.setNoOfSeats(s.nextInt());
                case 2:x.setCost(s.nextDouble());
                case 3:editCategory(s,x,s.next());
                case 4:GuestSection(s);
            }
        } catch (NullPointerException e) {
            System.out.println("Guest not found!"); 
        } 
    }
    public static void editCategory(Scanner s,Table x,String categ) {
        drawMenu(new String[]{"Standard","Couples","Family","Private","Back"});
        switch(takeMenuIndex(s, 1, 4)){
            case 1:x.setCateg(Category.Standard);
            case 2:x.setCateg(Category.Couples);
            case 3:x.setCateg(Category.Family);
            case 4:x.setCateg(Category.Private);
            case 5:editTable(s);
        }
    }
    public static void removeTable(Scanner s)  {
        System.out.print("Enter the Table Number : ");
        try {
             Table.getlist().remove(Table.getTable(s.nextInt()));           
        } catch (NullPointerException e) {
            System.out.println("Table not found!"); 
            GuestSection(s);
        }
    }
    public static void menuRec(Scanner s) {
        drawMenu(new String[]{"Log in","Exit"});
        switch(takeMenuIndex(s, 1, 2)){
            case 1:
            loginReceptionist(s);
            break;
            case 2:
            System.exit(0);
            break;
        }   
    }
    public static void loginReceptionist(Scanner s) {
         boolean check=true;
         while (check) {
         System.out.print("Enter your username : ");
         String user=s.next();
         System.out.println();
         for(int i=0;i<Receptionist.getList().size();i++){
         if(Receptionist.getList().get(i).getUserName().equals(user)){
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
         for(int i=0;i<Receptionist.getList().size();i++){
         if(Receptionist.getList().get(i).checkpassword(notdata)){
            System.out.println("Welcome Mr/Mrs "+Receptionist.getList().get(i).getName());
            Receptionistdetails(s, Receptionist.getList().get(i).getName());
          }
         else{
            System.out.println("Incorrect Password");
             }
         }
         }
    }
    public static void Receptionistdetails(Scanner s,String Name) {
    Receptionist.getRecord();
    ArrayList<Receptionist> Re=Receptionist.getList();   
    int index=0;
    for(int i=0;i<Re.size();i++){
        if(Re.get(i).getName().equals(Name)){
            index=i;
        }
   }
   drawMenu(new String[]{"Create Reservation","Cancel Reservation","Select Guest Category","Get Revenue","Get Number of Reservations done","Exit"});
   switch (takeMenuIndex(s, 1, 6)) {
    case 1:
     CreateReservation(s,index); 
     break; 
     case 2:
     CancelReservation(s,index);
     break;
     case 3:
     selecguestcateg(s,index);
     break;
     case 4:
     System.out.println( "The Revenue is : "+Receptionist.getList().get(index).getRevenue());
     break;
     case 5:
     System.out.println("Number of Reservations are : "+Receptionist.getList().get(index).getreservationsCount());
     break;
     case 6:
     Receptionist.saveRecords();
     return;
    }
   }
    public static void CreateReservation(Scanner s,int ind) {
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
    }
    catch(InvalidAttributeValueException e){
        System.out.println("Reservation couldn't be made!   Reason: "+e.getMessage());
    }
    catch(ParseException e){
        System.out.println("Reservation couldn't be made!   Reason: "+e.getMessage());
    }

}
    public static void CancelReservation(Scanner s,int ind) {
   System.out.println("Enter Reservation number to be deleted : ");
   try {
     Receptionist.getList().get(ind).cancelReservation(s.nextInt());
   } catch (InvalidAttributeValueException e) {
     System.out.println("Reservation couldn't be found");
     return;
   }
  
}
    public static void selecguestcateg(Scanner s,int ind){
   System.out.println("Enter guest Name : ");
   String n=s.next();
   String[] list=new String[]{"Standard","Couples","Family","Private"};
    System.out.println("-------------------------------------------");
    for(int i=1;i<=list.length;i++){
        System.out.println(""+i+"- "+list[i-1]);
    }
    System.out.println("-------------------------------------------");
   System.out.println("Enter The Category : ");
   String categ=s.next();
   ArrayList<Guest>G=Guest.getList();
   Category c;
   for(int i=0;i<G.size();i++){
    if(n==G.get(i).getName()){
        if(categ.equalsIgnoreCase("Standard"))
            G.get(i).setPreferedCategory(0);
     
        else if(categ.equalsIgnoreCase("Private"))
           G.get(i).setPreferedCategory(3);
     
        else if(categ.equalsIgnoreCase("Family"))
           G.get(i).setPreferedCategory(2);
     
        else if(categ.equalsIgnoreCase("Couples"))
           G.get(i).setPreferedCategory(1);
        }
     }
     return;
   }   
    public static void menuGuest(Scanner s) {
        drawMenu(new String[]{"Sign up","Log in","Back"});
       switch(takeMenuIndex(s, 1, 3)){
           case 1: AddGuest(s);
           case 2: 
           case 3: 
                        main(null);
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
}