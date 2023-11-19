/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;

public class Admin  {
private String user= "";
private String password="";


public Admin(String username, String pass){
    user= username;
    password =pass;
}
public String getUsername(){

return user;
}
public void setUsername(String username){
user=username;
}

public void setPassword(String pass){
password=pass;
}
public boolean checkPassword(String pass){

return (pass==password);
}
public void addTable(int numofseats){

 Table table=new Table(numofseats);
 ArrayList<Table>Tables=Table.getList();
 Tables.add(table);
}
public void editTable(int tableNum,Category tableCategory,double newcost,int newnoseats){
    ArrayList<Table> Tables=Table.getList();
    Tables.get(tableNum).Categ=tableCategory;
    Tables.get(tableNum).Cost=newcost;
    Tables.get(tableNum).NoOfSeats=newnoseats;
}
public void removeTable(int tableNum) throws Throwable{
    ArrayList<Table> Tables=Table.getList();
    Tables.get(tableNum).finalize();
    
}

public String search(Category tableCategory){
    ArrayList<Table> Tables=Table.getList();
    int count =0;
    int []no=new int[10];
    for(int i=0;i<Tables.size();i++){
if(Tables.get(i).Categ==tableCategory){
count++;
no[i]=i;
}
else{
continue;
}
    }
    return Arrays.toString(no);
}

public void viewTableReports( int tableNum){
   ArrayList<Table> Tables=Table.getList();
System.out.println("The table Category is "+ Tables.get(tableNum).Categ);
System.out.println("The table most number of seats is "+Tables.get(tableNum).NoOfSeats);
System.out.println("The table's cost"+Tables.get(tableNum).Cost);



}
public void addMenu(){
Menu menu=new Menu();
ArrayList<Menu>Menues=Menu.getlist();
Menues.add(menu);
}
public void editMenu(int menuId,Category Menucategory,Meal fmeal,Meal tmeal){
ArrayList<Menu>Menues=Menu.getlist();
Menues.get(menuId).Categ=Menucategory;
for(int i=0;i<Menues.get(menuId).Meals.size();i++){
if(Menues.get(menuId).Meals.get(i)==fmeal){
Menues.get(menuId).Meals.set(i, tmeal);
}
}
    
    
}
public void removeMenu(int menuId){
   ArrayList<Menu>Menues=Menu.getlist();
   Menues.get(menuId).finalize();

}

public void search( int menuId,Date Start,Date End){
 ArrayList<Menu>Menues=Menu.getlist();
 System.out.println("This menu's category is"+Menues.get(menuId).Categ);
 System.out.println("This menu's most ordered meal is"+Menues.get(menuId).mostOrderedMeal(Start, End));
}
public void viewMenuReports(int menuId){
ArrayList<Menu>Menues=Menu.getlist();
System.out.println("This menu belongs to Table : "+Menues.get(menuId).Categ);
for(int i=0;i<Menues.get(menuId).Meals.size();i++){
System.out.println("This menu belongs to Table : "+Menues.get(menuId).Meals.get(i));
}

}
public void addUsers(String name, String Address, String DateOfBirth, String PhoneNum, String Email){
Guest guest=new Guest(name,Address,DateOfBirth,PhoneNum,Email);
ArrayList<Guest>guests=Guest.getList();
guests.add(guest);

}
public void edit(int UserId,String Categorys){
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
    System.out.println("The Receptionist whose name is "+recep.get(j).getName()+"his reservation number is "+h.get(j).getNo_of_reservation());
}
double maxreserve=recep.get(0).getReservationcount();
String name =recep.get(0).getName();
for(int j=0;j<h.size();j++){
  if(recep.get(j).getReservationcount()>maxreserve){
maxreserve=recep.get(j).getReservationcount();
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
    System.out.println("Reservation number "+i+1);
    System.out.println("whose date is "+G.get(i).getDate());
    System.out.println("whose receptionist is "+G.get(i).getReceptionistId());
    System.out.println("whose reservation number"+G.get(i).getReservationNumber());
    System.out.println("Table number  is "+G.get(i).getTableNum());
    System.out.println("whose price is "+G.get(i).getPrice());
    System.out.println("whose start time is "+G.get(i).getStartTime());
    System.out.println("whose end time is "+G.get(i).getEndTime());
}
}

void rating(byte rate,int guestid){

ArrayList<Reservation> G=Reservation.getList();
for(int i=0;0<G.size();i++){
if(G.get(i).getGuestId()==guestid){
G.get(i).setRating(rate);
}
else{
continue;
}
}
}
}



