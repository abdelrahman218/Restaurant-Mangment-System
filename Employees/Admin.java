package Employees;
import java.util.ArrayList;
import java.util.Date;
import system.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import user.Guest;
public class Admin extends Person implements Comparable<Admin>{
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
public static void addTable(int numofseats){
 Table table=new Table(numofseats);
 ArrayList<Table>Tables=Table.getlist();
 Tables.add(table);
}
public static void editTable(int tableNum,Category tableCategory,double newcost,int newnoseats){
    Table.getlist().get(tableNum).setCateg(tableCategory);
    Table.getlist().get(tableNum).setCost(newcost);
    Table.getlist().get(tableNum).setNoOfSeats(newnoseats);
}
public static void removeTable(int tableNum){
    Table.getlist().remove(Table.getTable(tableNum));
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
public static String viewTableReports( int tableNum,Date StartDate,Date EndDate){
   ArrayList<Table> Tables=Table.getlist();
   String list="";
   list+="The table Category is : ";
   list+= Tables.get(tableNum).getcateg();
   list+="\n";
   list+="The number of times this table got reserved between time : "+StartDate.toString()+" and "+EndDate.toString()+Tables.get(tableNum).ReservationsInSpecificTime(StartDate, EndDate);
   list+="\n";
   list+="Table with the highest revenue is : "+Table.highestRevenue(StartDate, EndDate)+"\n";
   list+="Table that is most reserved is : "+Table.mostReservedTable(StartDate, EndDate)+"\n";    
   list+="The table most number of seats is : "+Tables.get(tableNum).getNoOfSeats()+"\n";
   list+="The table's cost : "+Tables.get(tableNum).getCost()+"\n";
return list;
}
public static void addMenu(){
    Menu.getlist().add(new Menu());
}
public static void addMenu(int x){
    switch (x){
        case 1: Menu.getlist().add(new Menu(MenuCategory.Breakfast));
        case 2: Menu.getlist().add(new Menu(MenuCategory.Lunch));
        case 3: Menu.getlist().add(new Menu(MenuCategory.Dinner));
        case 4: Menu.getlist().add(new Menu(MenuCategory.Beverages));
        case 5: Menu.getlist().add(new Menu(MenuCategory.Dessert));
    }
}
public void editMenu(int menuId,MenuCategory Menucategory){Menu.getlist().get(menuId).setCateg(Menucategory);}
public static void removeMenu(int menuId){Menu.getlist().remove(Menu.getlist().get(menuId));}
public static String searchMenu(int menuId,Date Start,Date End){
    String list="";
 list+="This menu's category is : ";
 list+=Menu.getlist().get(menuId).getCateg();
 list+="\n";
 list+="This menu's most ordered meal is : ";
 list+=Meal.getList().get(menuId).mostOrderedMeal(Start, End);
return list;
}
public static ArrayList<Meal> searchMenu(int menuId){return Menu.getlist().get(menuId).getMeals();}
public static void createMeal(int Menu_ID, int meal_ID, String Name, double Price){
Meal.getList().add(new Meal(Menu_ID, meal_ID, Name, Price));
}
public static MenuCategory viewMenuReportsCateg(int menuId){
ArrayList<Menu>Menues=Menu.getlist();
return Menues.get(menuId).getCateg();
}
public static Meal viewMenuReportsMostOrderedMeal(int menuId,Date Start,Date End){
ArrayList<Meal>Meals=Meal.getList();
return Meals.get(menuId).mostOrderedMeal(Start, End);
}
public static void addUsers(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
Guest guest=new Guest(Name,Address,DateOfBirth,PhoneNum,Email,UserName,Password);
ArrayList<Guest>guests=Guest.getList();
guests.add(guest);

}
public static void editCategory(int UserId,String Categorys){
Guest Guests= Guest.getGuest(UserId);
if(Categorys.equalsIgnoreCase("couples")){
Guests.setPreferedCategory(1);
}
else if(Categorys.equalsIgnoreCase("standard")){
Guests.setPreferedCategory(0);
}
else if(Categorys.equalsIgnoreCase("family")){
  Guests.setPreferedCategory(2); 
}
else if(Categorys.equalsIgnoreCase("private")){
 Guests.setPreferedCategory(3); 
}
}
public static void remove(int UserId) throws Throwable{
Guest.getList().remove(Guest.getGuest(UserId));
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
public static String viewReservationOfGuest(int GuestId){
Reservation r = new Reservation();
Guest guests =Guest.getGuest(GuestId);
ArrayList<Reservation> G=r.search(guests);
    String list="";
    list+="The guest whose id is :"+GuestId+" his reservation history is : \n";
for(int i=0;i<G.size();i++){
    list+="Reservation number "+G.get(i).getReservationNumber()+"\n";
    list+="whose date is "+G.get(i).getDate()+"\n";
    list+="whose receptionist is "+G.get(i).getReceptionistId()+"\n";
    list+="whose reservation number"+G.get(i).getReservationNumber()+"\n";
    list+="Table number  is "+G.get(i).getTableNum()+"\n";
    list+="whose price is "+G.get(i).getPrice()+"\n";
    list+="whose start time is "+G.get(i).getStartTime()+"\n";
    list+="whose end time is "+G.get(i).getEndTime()+"\n";
}
return list;
}
public static String viewReservationdetails(int guestId,int reservationNO){
    
    Reservation r = new Reservation();
Guest guests =Guest.getGuest(guestId);
ArrayList<Reservation> G=r.search(guests);
    String list="";
   
    for(int i=0;i<G.size();i++){
    if(reservationNO==G.get(i).getReservationNumber()){
        list+="The reservation cost is : "+G.get(i).getPrice()+"\n";
    }
    }
    double sum=0;
    double average;
    list+="Their total : ";
  for(int i=0;i<G.size();i++){
  sum=G.get(i).getPrice();
  
  }
  list+=sum+"\n";
  average=sum/G.size();
    list+="The average cost of all reservations is : "+average+"\n";
return list;
}
public static Admin search(String userName){
    for(int i=0;i<Admins.size();i++){
    if(Admins.get(i).getUserName()==userName){
        return Admins.get(i);
    }
    }
    return null;
}
}
