package employees;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import javax.management.InvalidAttributeValueException;
import system.*;
public class Admin extends Person  {
public Admin(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password){
    super(Name,Address,DateOfBirth,PhoneNum,Email,UserName, Password);
}
public void addTable(int numofseats){
 Table table=new Table(numofseats);
 ArrayList<Table>Tables=Table.getlist();
 Tables.add(table);
}
public void editTable(int tableNum,Category tableCategory,double newcost,int newnoseats){
    Table.getlist().get(tableNum).setCateg(tableCategory);
    Table.getlist().get(tableNum).setCost(newcost);
    Table.getlist().get(tableNum).setNoOfSeats(newnoseats);
}
public void removeTable(int tableNum){
    Table.getlist().remove(Table.getTable(tableNum));
}
public ArrayList<Table> searchTable(Category tableCategory){
    ArrayList<Table> Tables=new ArrayList<Table>();
    for(int i=0;i<Table.getlist().size();i++){
    if(Table.getlist().get(i).getcateg()==tableCategory){
      Tables.add(Table.getlist().get(i));
    }
    }
    return Tables;
}
public void viewTableReports( int tableNum,Date StartDate,Date EndDate){
   ArrayList<Table> Tables=Table.getlist();
System.out.println("The table Category is "+ Tables.get(tableNum).getcateg());
System.out.println("The number of times this table got reserved between time "+StartDate.toString()+" and "+EndDate.toString()+Tables.get(tableNum).ReservationsInSpecificTime(StartDate, EndDate));
System.out.println("Table with the highest revenue is "+Table.highestRevenue(StartDate, EndDate));
    System.out.println("Table that is most reserved is "+Table.mostReservedTable(StartDate, EndDate));    
System.out.println("The table most number of seats is "+Tables.get(tableNum).getNoOfSeats());
System.out.println("The table's cost"+Tables.get(tableNum).getCost());
}
public void addMenu(){
    Menu.getlist().add(new Menu());
}
public void editMenu(int menuId,MenuCategory Menucategory){Menu.getlist().get(menuId).Categ=Menucategory;}
public void removeMenu(int menuId){Menu.getlist().remove(Menu.getlist().get(menuId));}
public void searchMenu( int menuId,Date Start,Date End){
 ArrayList<Menu>Menues=Menu.getlist();
 System.out.println("This menu's category is"+Menues.get(menuId).Categ);
 System.out.println("This menu's most ordered meal is"+Menues.get(menuId).mostOrderedMeal(Start, End));
}
public ArrayList<Meal> searchMenu(int menuId){return Menu.getlist().get(menuId).getMeals();}
public void viewMenuReports(Date Start,Date End){
ArrayList<Menu>Menues=Menu.getlist();
for(int i=0;i<Menues.size();i++){
System.out.println("This menu belongs to Table : "+Menues.get(i).Categ);
//    System.out.println("This menu most ordered meal is : "+Menues.get(i).mostOrderedMeal(Start, End)+" and it's category is : "Menues.get(i).);
    
}
}
public void addUsers(String name, String Address, String DateOfBirth, String PhoneNum, String Email){
Guest guest=new Guest(name,Address,DateOfBirth,PhoneNum,Email);
ArrayList<Guest>guests=Guest.getList();
guests.add(guest);

}
public void editCategory(int UserId,String Categorys){
Guest Guests= Guest.getGuest(UserId);
if(Categorys.equalsIgnoreCase("couples")){
Guests.PreferredCategory=Category.Couples;
}
else if(Categorys.equalsIgnoreCase("standard")){
Guests.PreferredCategory=Category.Standard;
}
else if(Categorys.equalsIgnoreCase("family")){
  Guests.PreferredCategory=Category.Family;  
}
else if(Categorys.equalsIgnoreCase("private")){
Guests.PreferredCategory=Category.Private;  
}
}




public void remove(int UserId) throws Throwable{
Guest Guests= Guest.getGuest(UserId);
Guests.finalize();
}

public void viewUsers(int UserID){
Reservation r = new Reservation();
Guest guests =Guest.getGuest(UserID);
ArrayList<Reservation> G=r.search(guests);

  //  System.out.println(G.get(0).);
for(int i=0;i<G.size();i++){
    System.out.println(G.get(i));
}



}

public void viewUsers(){
ArrayList<Guest>guests=Guest.getList();
for(int i=0;i<guests.size();i++){
System.out.println("Guest whose id is"+guests.get(i).getId() +"reservation history is :" );
guests.get(i).ViewReservation();   
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
    System.out.println("The receptionist whose name is "+id+"with the highest revenue is "+max);
ArrayList<Reservation>h=Reservation.getList();
for(int j=0;j<h.size();j++){
    System.out.println("The Receptionist whose name is "+recep.get(j).getName()+"his reservation number is "+h.get(j).getReservationNumber());
}
double maxreserve=recep.get(0).getreservationsCount();
String name =recep.get(0).getName();
for(int j=0;j<h.size();j++){
  if(recep.get(j).getreservationsCount()>maxreserve){
maxreserve=recep.get(j).getreservationsCount();
name=recep.get(j).getName();
}   
}
    System.out.println("Receptionist whoese "+name +"with max number of reservation is "+maxreserve);
}

void viewReservation(int GuestId){
Reservation r = new Reservation();
Guest guests =Guest.getGuest(GuestId);
ArrayList<Reservation> G=r.search(guests);
    System.out.println("The guest whose id is :"+GuestId+" his reservation history is");
for(int i=0;i<G.size();i++){
    System.out.println("Reservation number "+G.get(i).getReservationNumber());
    System.out.println("whose date is "+G.get(i).getDate());
    System.out.println("whose receptionist is "+G.get(i).getReceptionistId());
    System.out.println("whose reservation number"+G.get(i).getReservationNumber());
    System.out.println("Table number  is "+G.get(i).getTableNum());
    System.out.println("whose price is "+G.get(i).getPrice());
    System.out.println("whose start time is "+G.get(i).getStartTime());
    System.out.println("whose end time is "+G.get(i).getEndTime());
}
    System.out.print("Choose which reservation to get is cost : ");
    Scanner s =new Scanner(System.in);
    int check=s.nextInt();
    for(int i=0;i<G.size();i++){
    if(check==G.get(i).getReservationNumber()){
        System.out.println("The reservation cost is : "+G.get(i).getPrice());
    }
    }
    double sum=0;
    double average;
  for(int i=0;i<G.size();i++){
  sum=G.get(i).getPrice();
  
  }
  average=sum/G.size();
    System.out.println("The average cost of all reservations is : "+average);
}
}

