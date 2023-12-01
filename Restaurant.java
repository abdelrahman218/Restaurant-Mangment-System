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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class Restaurant extends Application {
    
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
    HBox root =new HBox(50,bt1,bt2,bt3);
    root.setAlignment(Pos.CENTER);
    Scene s = new Scene(root,700,200);
    bt1.setOnAction(e->{admindetails(primaryStage);});
    bt2.setOnAction(e->{receptionistdetails(primaryStage);});
    bt3.setOnAction(e->{guestdetails(primaryStage);});
    primaryStage.setScene(s);
    primaryStage.setTitle("Restaurant");
    primaryStage.show();
    } 
    private void admindetails(Stage nested){
    nested.close();
    Stage admin = new Stage();
    Button bt1=new Button("Sign in");
    Button bt3=new Button("Back");
    HBox root = new HBox(20,bt1,bt3);
    root.setAlignment(Pos.CENTER);
    Scene s = new Scene(root,700,200);
    bt1.setOnAction(e->{adminlogin(admin);});
    bt3.setOnAction(e->{nested.show();admin.close(); });
    admin.setScene(s);
    admin.setTitle("Admin");
    admin.show();
    }
    private void adminlogin(Stage nested){
    nested.close();
    Stage admin = new Stage();
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
    Scene s=new Scene(root,700,200);
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
                            adminmenu(admin);}
                          else{
                          Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Password Wrong");
                            a.show();
                          }});
    bt1.setOnAction(e->{nested.show();admin.close();});
    admin.setScene(s);
    admin.setTitle("Login");
    admin.show();
    }
    private void adminmenu(Stage nested){
    nested.close();
    Stage admin = new Stage();
    Button bt1 = new Button("Create Admin");
    Button bt2 = new Button("test2");
    Button bt3 = new Button("test3");
    Button bt4 = new Button("test4");
    HBox root =new HBox(20,bt1,bt2,bt3,bt4);
    root.setAlignment(Pos.CENTER);
    Scene s= new Scene(root,700,200);
    bt1.setOnAction(e->{admincreate(admin);});
    admin.setScene(s);
    admin.show();
    
    }
    private void admincreate(Stage nested){
    nested.close();
    Stage admin=new Stage();
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
    Scene s =new Scene(root,700,200);
    admin.setTitle("Sign up");
    admin.setScene(s);
    admin.show();
    }
    private void receptionistdetails(Stage nested){
    nested.close();
    Stage receptionist = new Stage();
    Button bt1=new Button("Sign in");
    Button bt2=new Button("Sign up");
    Button bt3=new Button("Back");
    HBox root = new HBox(20,bt1,bt2,bt3);
    root.setAlignment(Pos.CENTER);
    Scene s = new Scene(root,700,200);
    bt1.setOnAction(e->{receptionistlogin(receptionist);});
    bt2.setOnAction(e->{receptionistcreate(receptionist);});
    bt3.setOnAction(e->{nested.show();receptionist.close(); });
    receptionist.setScene(s);
    receptionist.setTitle("Receptionist");
    receptionist.show();
    }
    private void receptionistlogin(Stage nested){
    nested.close();
    Stage receptionist = new Stage();
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
    Scene s=new Scene(root,700,200);
    bt1.setOnAction(e->{nested.show();receptionist.close();});
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
    receptionist.setScene(s);
    receptionist.setTitle("Login");
    receptionist.show();
    }
    private void receptionistcreate(Stage nested){
    nested.close();
    Stage receptionist=new Stage();
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
    Scene s =new Scene(root,700,200);
    receptionist.setTitle("Sign up");
    receptionist.setScene(s);
    receptionist.show();
    }
    private void guestdetails(Stage nested){
    nested.close();
    Stage guest = new Stage();
    Button bt1=new Button("Sign in");
    Button bt2=new Button("Sign up");
    Button bt3=new Button("Back");
    HBox root = new HBox(20,bt1,bt2,bt3);
    root.setAlignment(Pos.CENTER);
    Scene s = new Scene(root,700,200);
    bt1.setOnAction(e->{guestlogin(guest);});
    bt2.setOnAction(e->{guestcreate(guest);});
    bt3.setOnAction(e->{nested.show();guest.close(); });
    guest.setScene(s);
    guest.setTitle("Guest");
    guest.show();
    }
    private void guestlogin(Stage nested){
    nested.close();
    Stage guest = new Stage();
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
    Scene s=new Scene(root,700,200);
    bt1.setOnAction(e->{nested.show();guest.close();});
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
    guest.setScene(s);
    guest.setTitle("Login");
    guest.show();
    }
    private void guestcreate(Stage nested){
    nested.close();
    Stage guest=new Stage();
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
    Scene s =new Scene(root,700,200);
    guest.setTitle("Sign up");
    guest.setScene(s);
    guest.show();
    }
      public static void main(String[] args) {
        launch(args);
    } 
}