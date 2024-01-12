package employees;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import system.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.Guest;
public class Admin extends Person implements Comparable<Admin>,Serializable{
private static ArrayList<Admin>Admins=new ArrayList<>();
@Override
public int compareTo(Admin right){
     return 0;
    }
public static ArrayList<Admin> getAdmins(){
    return Admins;
}
public static void saveRecords(){
    try {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Admin archive.dat"));
            out.writeObject(Admins);
        out.close();
        } catch (IOException e) {
        System.out.println(e);
        }
}
public static void getRecord(){
    try {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Admin archive.dat"));
        Admins=(ArrayList<Admin>)in.readObject();
        in.close();
    }catch (ClassNotFoundException e) {
        System.out.println(e);
    } catch (IOException e) {
        System.out.println(e);
    }
}
public Admin(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
    super(Name,Address,DateOfBirth,PhoneNum,Email,UserName, Password);
    Admins.add(this);
}
public void addTable(int numofseats,String categ){
    try{
        FileWriter f=new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
    if(categ.equals("Standard")){
        new Table(numofseats,Category.Standard);
        p.write("Admin: "+getName()+"\n Created Table\n NO Of Seats: "+numofseats+"\n Category: "+categ+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
    }
    else if(categ.equals("Couples")){
        new Table(numofseats,Category.Couples);
        p.write("Admin: "+getName()+"\n Created Table\n NO Of Seats: "+numofseats+"\n Category: "+categ+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
    }
    else if(categ.equals("Family")){
        new Table(numofseats,Category.Family); 
        p.write("Admin: "+getName()+"\n Created Table\n NO Of Seats: "+numofseats+"\n Category: "+categ+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
    }
    else if(categ.equals("Private")){
        new Table(numofseats,Category.Private);
        p.write("Admin: "+getName()+"\n Created Table\n NO Of Seats: "+numofseats+"\n Category: "+categ+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
    }
    p.close();
    f.close();
    }
    catch(FileNotFoundException ex){
    } catch (IOException ex) {
        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void editTable(int tableNum,Category tableCategory,double newcost,int newnoseats){
    try{
        FileWriter f=new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
        p.write("Admin: "+getName()+"\n Changed Table ID : "+tableNum+"\n Category from : "+Table.getlist().get(tableNum).getcateg()+" to: "+tableCategory+"\n Cost from: "+Table.getlist().get(tableNum).getCost()+" to: "+newcost+"\n No of Seats from: "+Table.getlist().get(tableNum).getNoOfSeats()+"to: "+newnoseats+"\n");
    p.write("---------------------------------------------------------------------------------------------\n");
    Table.getlist().get(tableNum).setCateg(tableCategory);
    Table.getlist().get(tableNum).setCost(newcost);
    Table.getlist().get(tableNum).setNoOfSeats(newnoseats);
    p.close();
    f.close();
    }
    catch(FileNotFoundException ex){
    
    } catch (IOException ex) {
        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void removeTable(int tableNum){
    
    try {
        FileWriter f = new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
        p.write("Admin: "+getName()+"\n Removed Table Number: "+tableNum+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
        p.close();
        f.close();
        Table.getlist().remove(Table.getTable(tableNum));
    } catch (IOException ex) {
            }
        
}
public static ArrayList<Table> searchTable(Category tableCategory){
    ArrayList<Table> Tables=new ArrayList<Table>();
    for(int i=0;i<Table.getlist().size();i++){
    if(Table.getlist().get(i).getcateg()==tableCategory){
      Tables.add(Table.getlist().get(i));
    }
    }
    return Tables;
}
public void addMenu(int x){
    try{
        FileWriter f = new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
    switch (x){
        case 1: new Menu(MenuCategory.Breakfast);
        p.write("Admin: "+getName()+"\n Admin Created Menu \n Category: Breakfast \n"); 
        p.write("---------------------------------------------------------------------------------------------\n");
        break;
        case 2: new Menu(MenuCategory.Lunch);
        p.write("Admin: "+getName()+"\n Admin Created Menu \n Category: Lunch \n");
        p.write("---------------------------------------------------------------------------------------------\n");
        break;
        case 3: new Menu(MenuCategory.Dinner);
        p.write("Admin: "+getName()+"\n Admin Created Menu \n Category: Dinner \n");
        p.write("---------------------------------------------------------------------------------------------\n");
        break;
        case 4: new Menu(MenuCategory.Beverages);
        p.write("Admin: "+getName()+"\n Admin Created Menu \n Category: Beverages \n");
        p.write("---------------------------------------------------------------------------------------------\n");
        break;
        case 5: new Menu(MenuCategory.Dessert);
        p.write("Admin: "+getName()+"\n Admin Created Menu \n Category: Dessert \n");
        p.write("---------------------------------------------------------------------------------------------\n");
        break;
        }
        f.close();
        p.close();
    }
    catch (IOException ex) {
        System.out.println("menuuu");}
}
public void editMenu(int menuId,MenuCategory Menucategory){
    try{
        FileWriter f = new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
       for(int i=0;i<Menu.getlist().size();i++){
        if(Menu.getlist().get(i).getMenu_ID()==menuId){
          Menu.getlist().get(i).setCateg(Menucategory);
          p.write("Admin: "+getName()+"\n Changed Menu id: "+menuId+"\n Category from: "+Menu.getlist().get(i).getCateg()+" to:"+Menucategory+"\n");
          p.write("---------------------------------------------------------------------------------------------\n");
        }
        
       }
        
    f.close();
    p.close();
    }
    catch(IOException ex){
    }
}
public void removeMenu(int menuId){
    try{
        FileWriter f = new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
        p.write("Admin: "+getName()+"\n Removed Menu Id: "+menuId+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
       for(int i=0;i<Menu.getlist().size();i++){
        if(Menu.getlist().get(i).getMenu_ID()==menuId){
           Menu.getlist().remove(Menu.getlist().get(i));}
       }
        f.close();
        p.close();
       
    }
    catch(IOException ex){
    }
}

public static ArrayList<Meal> searchMenu(int menuId){return Menu.getlist().get(menuId).getMeals();}
public void createMeal(int Menu_ID, int meal_ID, String Name, double Price){
Meal.getList().add(new Meal(Menu_ID, meal_ID, Name, Price));
}
public static MenuCategory viewMenuReportsCateg(int menuId){
ArrayList<Menu>Menues=Menu.getlist();
return Menues.get(menuId).getCateg();
}
public void addUsers(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
    try{
        FileWriter f = new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
        p.write("Admin: "+getName()+"\n Created User \n User Details \n Name: "+Name+"\n Address: "+Address+"\n Date of Birth: "+DateOfBirth+"\n PhoneNum: "+PhoneNum+"\n Email: "+Email+"\n UserName: "+UserName+"\n Password: "+Password+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
        new Guest(Name,Address,DateOfBirth,PhoneNum,Email,UserName,Password);
        f.close();
        p.close();
    }
    catch(IOException ex){
    }
}
public void editCategory(int UserId,String Categorys){
if(Categorys.equalsIgnoreCase("couples")){
    Guest.getList().get(UserId).setPreferedCategory(1);
}
else if(Categorys.equalsIgnoreCase("standard")){
Guest.getList().get(UserId).setPreferedCategory(0);
}
else if(Categorys.equalsIgnoreCase("family")){
  Guest.getList().get(UserId).setPreferedCategory(2); 
}
else if(Categorys.equalsIgnoreCase("private")){
 Guest.getList().get(UserId).setPreferedCategory(3); 
}
}
public void removeGuest(int UserId) throws Throwable{
    try{
        FileWriter f = new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
        p.write("Admin: "+getName()+"\n Removed Guest Id:"+UserId+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
        Guest.getList().remove(UserId);
        f.close();
        p.close();
    }
    catch(IOException ex){
    }

}
public void addReceptionist(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
    try{
        FileWriter f = new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
        p.write("Admin: "+getName()+"\n Created Receptionist \n Receptionist Details \n Name: "+Name+"\n Address: "+Address+"\n Date of Birth: "+DateOfBirth+"\n PhoneNum: "+PhoneNum+"\n Email: "+Email+"\n UserName: "+UserName+"\n Password: "+Password+"\n");
       p.write("---------------------------------------------------------------------------------------------\n");
        new Receptionist(Name,Address,DateOfBirth,PhoneNum,Email,UserName,Password);
        f.close();
        p.close();
    }
    catch(IOException ex){
    }
}
public void removeReceptionist(int UserId) throws Throwable{
    try{
        FileWriter f = new FileWriter("Adminedit.txt",true);
        PrintWriter p=new PrintWriter(f);
        p.write("Admin: "+getName()+"\n Removed Receptionist Id:"+UserId+"\n");
        p.write("---------------------------------------------------------------------------------------------\n");
        Receptionist.getList().remove(UserId);
        f.close();
        p.close();
    }
    catch(IOException ex){
    }
}
public static String viewUsers(int UserID){
Reservation r = new Reservation();
Guest guests =Guest.getGuest(UserID);
ArrayList<Reservation> G=r.search(guests);
String list="";
for(int i=0;i<G.size();i++){
    list+="Reservation price is : ";
    list+=G.get(i).getPrice();
    list+="\n";
    list+="Start time is : ";
    list+=G.get(i).getStartTime();
    list+="\n";
    list+="End time is : ";
    list+=G.get(i).getEndTime();
    list+="\n";
}
return list;
}
public static String viewUsers(){
ArrayList<Guest>guests=Guest.getList();
String list="";
for(int i=0;i<guests.size();i++){
list+="Guest whose ID is"+guests.get(i).getId() +"reservation history is : "+ guests.get(i).ViewReservation()+"\n";  
}
ArrayList<Receptionist>recep=Receptionist.getList();
double max=recep.get(0).getRevenue();
String id=recep.get(0).getName();
for(int i=1;i<recep.size();i++){
if(recep.get(i).getRevenue()>max){
max=recep.get(i).getRevenue();
id=recep.get(i).getName();
}
}
    list+="The receptionist whose name is "+id+"with the highest revenue is "+max+"\n";
ArrayList<Reservation>h=Reservation.getList();
for(int j=0;j<h.size();j++){
    list+="The Receptionist whose name is "+recep.get(j).getName()+"his reservation number is "+h.get(j).getReservationNumber()+"\n";
}
double maxreserve=recep.get(0).getreservationsCount();
String name =recep.get(0).getName();
for(int j=0;j<h.size();j++){
  if(recep.get(j).getreservationsCount()>maxreserve){
maxreserve=recep.get(j).getreservationsCount();
name=recep.get(j).getName();
}   
}
    list+="Receptionist whoese "+name +"with max number of reservation is "+maxreserve+"\n";
    return list;
}


@Override
    public String toString(){
        return ("Admin Details:\n"+"Name: "+getName()+"\nID: "+getId()+"\nDate Of Birth: "+getDateOfBirth()+"\nAddress: "+getAddress()+"\nEmail: "+getEmail()+"\nPhone Number: "+getPhoneNum());
    }
}
