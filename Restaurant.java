package restaurant;
import employees.*;
import system.*;
import user.Guest;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class Restaurant extends Application {
    
    public Scene start;    
    @Override
    public void start(Stage primaryStage) throws Exception {
    new Admin("Frank", "Paris", "19-2-2001", "01069856960", "frank.heisenberg@gmail.com", "A", "A");
    new Admin("John","Chicago","8-5-1962","01200588939","johnwhite@gmail.com","B","B");
    new Receptionist("Frank", "Paris", "19-2-2001", "01069856960", "frank.heisenberg@gmail.com", "C", "C");
    new Receptionist("John","Chicago","8-5-1962","01200588939","johnwhite@gmail.com","D","D");
    new Guest("Frank", "Paris", "19-2-2001", "01069856960", "frank.heisenberg@gmail.com", "E", "E");
    new Guest("John","Chicago","8-5-1962","01200588939","johnwhite@gmail.com","F","F");
    Button bt1=new Button("Admin");
    Button bt2=new Button("Receptionist");
    Button bt3=new Button("Guest");
    bt1.setPrefHeight(30);
    bt1.setPrefWidth(80);
    bt2.setPrefHeight(30);
    bt2.setPrefWidth(100);
    bt3.setPrefHeight(30);
    bt3.setPrefWidth(80);
    GridPane root= new GridPane();
    root.add(bt1, 0, 0);
    root.add(bt2, 1, 0);
    root.add(bt3, 2, 0);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    start = new Scene(root,1000,500);
    bt1.setOnAction(e->{admindetails(primaryStage);});
    bt2.setOnAction(e->{primaryStage.setScene(null);
        receptionistdetails(primaryStage);});
    bt3.setOnAction(e->{guestdetails(primaryStage);});
    primaryStage.setScene(start);
    primaryStage.setTitle("Restaurant");
    primaryStage.show();
    } 
    private void admindetails(Stage nested){
    
    
    Button bt1=new Button("Sign in");
    Button bt2=new Button("Back");
    bt1.setPrefHeight(30);
    bt1.setPrefWidth(80);
    bt2.setPrefHeight(30);
    bt2.setPrefWidth(80);
    GridPane root= new GridPane();
    root.add(bt1, 0, 0);
    root.add(bt2, 1, 0);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    Scene s = new Scene(root,1000,500);
    bt1.setOnAction(e->{adminlogin(nested);});
    bt2.setOnAction(e->{ nested.setScene(start); });
    nested.setScene(s);
    nested.setTitle("Admin");
    nested.show();
    }
    private void adminlogin(Stage nested){
    Button bt1=new Button("Back");
    Button bt2 = new Button("Login");
    bt1.setPrefHeight(30);
    bt1.setPrefWidth(80);
    bt2.setPrefHeight(30);
    bt2.setPrefWidth(80);    
    Label l1=new Label("UserName");
    Label l2=new Label("Password");
    l1.setPrefHeight(40);
    l1.setPrefWidth(100);
    l2.setPrefHeight(40);
    l2.setPrefWidth(100);
    PasswordField pas=new PasswordField();
    TextField name=new TextField();
    pas.setPrefHeight(20);
    pas.setPrefWidth(200);
    name.setPrefHeight(20);
    name.setPrefWidth(200);
    GridPane root = new GridPane();
    root.add(l1, 0, 0);
    root.add(name, 1, 0);
    root.add(l2, 0, 1);
    root.add(pas, 1, 1);
    root.add(bt2, 0, 2);
    root.add(bt1, 1, 2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.TOP_LEFT);
    root.setPadding(new Insets(20));
    Scene s=new Scene(root,1000,500);
    bt2.setOnAction(e->{ Admin current =null;
                         Boolean check=true;
                         String pa= pas.getText();
                         String val=name.getText();
                         for(int i=0;i<Admin.getAdmins().size();i++){
                         if(Admin.getAdmins().get(i).getUserName().equals(val)){
                          check=false;
                          current =Admin.getAdmins().get(i);
                          break;}}
                         if(check==true){
                          Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Login Failed");
                            a.show();
                         }
                         else if(current.checkpassword(pa)){
                            Alert ad=new Alert(Alert.AlertType.INFORMATION);
                            ad.setTitle("Welcome");
                            ad.setHeaderText("Login Sucessful");
                            ad.show();
                            adminmenu(nested,current);}
                          else{
                          Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Password Wrong");
                            a.show();
                          }});
    bt1.setOnAction(e->{admindetails(nested);});
    nested.setScene(s);
    nested.setTitle("Login");
    nested.show();
    }
    private void adminmenu(Stage nested,Admin current){
    Button bt1 = new Button("Create User");
    Button bt2 = new Button("Table details");
    Button bt3 = new Button("Receptionist details");
    Button bt4 = new Button("Menu details");
    Button bt5 =new Button("Guest details");
    Button bt6 = new Button("View Logs");
    bt1.setPrefHeight(30);
    bt1.setPrefWidth(120);
    bt2.setPrefHeight(30);
    bt2.setPrefWidth(120);
    bt3.setPrefHeight(30);
    bt3.setPrefWidth(130);
    bt4.setPrefHeight(30);
    bt4.setPrefWidth(120);
    bt5.setPrefHeight(30);
    bt5.setPrefWidth(120);
    bt6.setPrefHeight(30);
    bt6.setPrefWidth(120);
    GridPane root=new GridPane();
    root.add(bt6,2,0);
    root.add(bt1,0,1);
    root.add(bt2,1,1);
    root.add(bt3,2,1);
    root.add(bt4,3,1);
    root.add(bt5,4,1);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    Scene s= new Scene(root,1000,500);
    bt1.setOnAction(e->{usercreate(nested,current);});
    nested.setScene(s);
    nested.show();
    }
    private void usercreate(Stage nested,Admin current){
    Label name=new Label("Name");
    Label email=new Label("Email");
    Label address=new Label("Address");
    Label pass=new Label("Password");
    TextField na=new TextField();
    TextField em=new TextField();
    TextField add=new TextField();
    TextField pas=new TextField();
    VBox v1=new VBox(30,name,email,address,pass);
    VBox v2=new VBox(20,na,em,add,pas);
    HBox root=new HBox(10,v1,v2);
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.TOP_LEFT);
    Scene s =new Scene(root,1000,500);
    nested.setTitle("Sign up");
    nested.setScene(s);
    nested.show();
    }
    private void receptionistdetails(Stage nested){
    Button bt1=new Button("Sign in");
    Button bt2=new Button("Sign up");
    Button bt3=new Button("Back");
    HBox root = new HBox(20,bt1,bt2,bt3);
    root.setAlignment(Pos.CENTER);
    Scene s = new Scene(root,1000,500);
    bt1.setOnAction(e->{receptionistlogin(nested);});
    bt2.setOnAction(e->{receptionistcreate(nested);});
    bt3.setOnAction(e->{nested.setScene(start); });
    nested.setScene(s);
    nested.setTitle("Receptionist");
    nested.show();
    }
    private void receptionistlogin(Stage nested){
    Button bt1=new Button("Back");
    Button bt2 = new Button("Login");
    Label l1=new Label("UserName");
    Label l2=new Label("Password");
    PasswordField pas=new PasswordField();
    TextField name=new TextField();
    VBox v1 =new VBox(20,l1,l2);
    VBox v2 =new VBox(20,name,pas);
    HBox root=new HBox(20,v1,v2,bt2,bt1);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    Scene s=new Scene(root,1000,500);
    bt1.setOnAction(e->{receptionistdetails(nested);});
    bt2.setOnAction(e->{ Receptionist current =null;
                         Boolean check=true;
                         String pa= pas.getText();
                         String val=name.getText();
                         for(int i=0;i<Receptionist.getList().size();i++){
                         if(Receptionist.getList().get(i).getUserName().equals(val)){
                          check=false;
                          current =Receptionist.getList().get(i);
                          break;}}
                         if(check==true){
                          Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Login Failed");
                            a.show();
                         }
                         else if(current.checkpassword(pa)){
                            Alert ad=new Alert(Alert.AlertType.INFORMATION);
                            ad.setTitle("Welcome");
                            ad.setHeaderText("Login Sucessful");
                            ad.show();}
                          else{
                          Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Password Wrong");
                            a.show();
                          }});
    nested.setScene(s);
    nested.setTitle("Login");
    nested.show();
    }
    private void receptionistcreate(Stage nested){
    Label name=new Label("Name");
    Label email=new Label("Email");
    Label address=new Label("Address");
    Label pass=new Label("Password");
    TextField na=new TextField();
    TextField em=new TextField();
    TextField add=new TextField();
    TextField pas=new TextField();
    VBox v1=new VBox(30,name,email,address,pass);
    VBox v2=new VBox(20,na,em,add,pas);
    HBox root=new HBox(10,v1,v2);
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.TOP_LEFT);
    Scene s =new Scene(root,1000,500);
    nested.setTitle("Sign up");
    nested.setScene(s);
    nested.show();
    }
    private void guestdetails(Stage nested){
    Button bt1=new Button("Sign in");
    Button bt2=new Button("Sign up");
    Button bt3=new Button("Back");
    HBox root = new HBox(20,bt1,bt2,bt3);
    root.setAlignment(Pos.CENTER);
    Scene s = new Scene(root,1000,500);
    bt1.setOnAction(e->{guestlogin(nested);});
    bt2.setOnAction(e->{guestcreate(nested);});
    bt3.setOnAction(e->{nested.setScene(start); });
    nested.setScene(s);
    nested.setTitle("Guest");
    nested.show();
    }
    private void guestlogin(Stage nested){
    Button bt1=new Button("Back");
    Button bt2 = new Button("Login");
    Label l1=new Label("UserName");
    Label l2=new Label("Password");
    PasswordField pas=new PasswordField();
    TextField name=new TextField();
    VBox v1 =new VBox(20,l1,l2);
    VBox v2 =new VBox(20,name,pas);
    HBox root=new HBox(20,v1,v2,bt2,bt1);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    Scene s=new Scene(root,1000,500);
    bt1.setOnAction(e->{guestdetails(nested);});
    bt2.setOnAction(e->{ Guest current =null;
                         Boolean check=true;
                         String pa= pas.getText();
                         String val=name.getText();
                         for(int i=0;i<Guest.getList().size();i++){
                         if(Guest.getList().get(i).getUserName().equals(val)){
                          check=false;
                          current =Guest.getList().get(i);
                          break;}}
                         if(check==true){
                          Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Login Failed");
                            a.show();
                         }
                         else if(current.checkpassword(pa)){
                            Alert ad=new Alert(Alert.AlertType.INFORMATION);
                            ad.setTitle("Welcome");
                            ad.setHeaderText("Login Sucessful");
                            ad.show();}
                          else{
                          Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Password Wrong");
                            a.show();
                          }});
    nested.setScene(s);
    nested.setTitle("Login");
    nested.show();
    }
    private void guestcreate(Stage nested){
    Label name=new Label("Name");
    Label email=new Label("Email");
    Label address=new Label("Address");
    Label pass=new Label("Password");
    TextField na=new TextField();
    TextField em=new TextField();
    TextField add=new TextField();
    TextField pas=new TextField();
    VBox v1=new VBox(30,name,email,address,pass);
    VBox v2=new VBox(20,na,em,add,pas);
    HBox root=new HBox(10,v1,v2);
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.TOP_LEFT);
    Scene s =new Scene(root,1000,500);
    nested.setTitle("Sign up");
    nested.setScene(s);
    nested.show();
    }
      public static void main(String[] args) {
        launch(args);
    } 
}