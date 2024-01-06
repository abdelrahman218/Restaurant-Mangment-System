package restaurant;
import employees.*;
import system.*;
import user.Guest;
import java.util.ArrayList;
import system.Menu;
import system.MenuCategory;
import java.lang.IndexOutOfBoundsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;
import javax.management.InvalidAttributeValueException;
public class Restaurant extends Application {
    public Scene start;    
    @Override
    public void start(Stage primaryStage) {
    
    Button bt1=new Button();
    Button bt2=new Button();
    Button bt3=new Button();
    bt1.setPrefHeight(30);
    bt1.setPrefWidth(80);
    bt2.setPrefHeight(30);
    bt2.setPrefWidth(100);
    bt3.setPrefHeight(30);
    bt3.setPrefWidth(80);
    GridPane root= new GridPane();
    bt1.getStyleClass().add("admin-button");
    bt2.getStyleClass().add("receptionist-button");
    bt3.getStyleClass().add("guest-button");
    root.add(bt1, 0, 0);
    root.add(bt2, 1, 0);
    root.add(bt3, 2, 0);
    root.setHgap(30);
    root.setVgap(30);
    root.setAlignment(Pos.CENTER);
    Image im = new Image("file:D:\\start.jpg");
    BackgroundImage bgi = new BackgroundImage(
    im,
     BackgroundRepeat.NO_REPEAT,
    BackgroundRepeat.NO_REPEAT,
     BackgroundPosition.CENTER,
        new BackgroundSize(100,100,true,true,true,true));
    Background bg=new Background(bgi);
    root.setBackground(bg);
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    root.setPrefWidth(primaryScreenBounds.getWidth());
    root.setPrefHeight(primaryScreenBounds.getHeight());
    primaryStage.setX(primaryScreenBounds.getMinX());
    primaryStage.setY(primaryScreenBounds.getMinY());
    primaryStage.setWidth(primaryScreenBounds.getWidth());
    primaryStage.setHeight(primaryScreenBounds.getHeight());
    start = new Scene(root, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    start.getStylesheets().add("/restaurant/styles.css");  
    bt1.setOnAction(e->{adminlogin(primaryStage);});
    bt2.setOnAction(e->{recepLogin(primaryStage);});
    bt3.setOnAction(e->{guestlogin(primaryStage);});
    primaryStage.setScene(start);
//    primaryStage.setMaximized(true);
    primaryStage.setTitle("Restaurant");
//    primaryStage.setMaximized(true);
    primaryStage.show();
    } 
    private void adminlogin(Stage nested){
    Button bt1=new Button("Back");
    Button bt2 = new Button("Login");
    bt1.setPrefHeight(30);
    bt1.setPrefWidth(80);
    bt2.setPrefHeight(30);
    bt2.setPrefWidth(80);    
    bt1.getStyleClass().add("custom-button");
    bt2.getStyleClass().add("custom-button");
    Label l1=new Label("UserName");
    Label l2=new Label("Password");
    l1.setPrefHeight(40);
    l1.setPrefWidth(100);
    l2.setPrefHeight(40);
    l2.setPrefWidth(100);
    PasswordField pas=new PasswordField();
    TextField name=new TextField();
    name.getStyleClass().add("custom-textfield");
    pas.getStyleClass().add("custom-passwordfield");
    name.setPromptText("UserName");
    pas.setPromptText("Password");
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
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    Scene s=new Scene(root);
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
     root.setStyle("-fx-background-image: url('file:C://Users//EHKR//Documents//NetBeansProjects//media//fod.png');   "
             + "-fx-background-size: cover;\n" +
"    -fx-background-repeat: no-repeat;");
    bt1.setOnAction(e->{nested.setScene(start);;});
    s.getStylesheets().add(getClass().getResource("/restaurant/styles.css").toExternalForm());
    nested.setScene(s);
    nested.setTitle("Login");
    nested.setMaximized(true);
    nested.show();
    }
    private void adminmenu(Stage nested,Admin current){
    Button create = new Button("Create User");
    Button Tab = new Button("Table details");
    Button Recep = new Button("Receptionist details");
    Button Menues = new Button("Menu details");
    Button Guests =new Button("Guest details");
    Button logs = new Button("View Logs");
    Button signOut = new Button("Sign out");
    create.setPrefHeight(30);
    create.setPrefWidth(120);
    Tab.setPrefHeight(30);
    Tab.setPrefWidth(120);
    Recep.setPrefHeight(30);
    Recep.setPrefWidth(130);
    Menues.setPrefHeight(30);
    Menues.setPrefWidth(120);
    Guests.setPrefHeight(30);
    Guests.setPrefWidth(120);
    logs.setPrefHeight(30);
    logs.setPrefWidth(120);
    signOut.setPrefHeight(30);
    signOut.setPrefWidth(120);
    GridPane root=new GridPane();
    root.add(logs,2,0);
    root.add(create,0,1);
    root.add(Tab,1,1);
    root.add(Recep,2,1);
    root.add(Menues,3,1);
    root.add(Guests,4,1);
    root.add(signOut,2,2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
     root.setStyle("-fx-background-image: url('file:D:\\start.jpg');   "
             + "-fx-background-size: cover;\n" +
"    -fx-background-repeat: no-repeat;");
    Scene s= new Scene(root);
    create.setOnAction(e->{usercreate(nested,current);});
    Tab.setOnAction(e->{tb_details(nested,current);});
    Recep.setOnAction(e->{Recep_details(nested,current);});
    Guests.setOnAction(e->{Guest_details(nested,current);});
    Menues.setOnAction(e->{menudetails(nested,current);});
    signOut.setOnAction(e->{adminlogin(nested);});
    nested.setTitle("Admin Menu");
    nested.setScene(s);
    nested.show();
    }
    private void Recep_details(Stage nested,Admin current){
    ComboBox data=new ComboBox();
    ObservableList<String> items = FXCollections.observableArrayList();
    for(int i=0;i<Receptionist.getList().size();i++){
    String it="";
    it+=(i+1);
    items.add(it);
    }
    data.setItems(items);
    Label recepNo = new Label("Receptionist ID");
    ListView<String> viewData = new ListView();
    Button back=new Button("Back");
    Button remove=new Button("Remove");
    viewData.setPrefWidth(150);
    viewData.setPrefHeight(250);
    GridPane root= new GridPane();
    root.add(recepNo,0,0);
    root.add(data,1,0);
    root.add(viewData,0,1);
    root.add(back,1,2);
    root.add(remove,0,2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    data.setOnAction(e->{String get=(String)data.getValue();
                        int id =Integer.parseInt(get);
                        ObservableList<String> itemss = FXCollections.observableArrayList();
                        itemss.add(Receptionist.getList().get(id-1).toString());
                        viewData.setItems(itemss);});
    back.setOnAction(e->{adminmenu(nested,current);});
    remove.setOnAction(e->{try{
                           String get=(String)data.getValue();
                        int id =Integer.parseInt(get);
                        int number=id-1;
                        current.removeReceptionist(number); 
                        Recep_details(nested,current);}
                        catch(Throwable ee){
                        }});
     root.setStyle("-fx-background-image: url('file:C://fod.png');   "
             + "-fx-background-size: cover;\n" +
"    -fx-background-repeat: no-repeat;");
    Scene s= new Scene(root);
    nested.setScene(s);
    nested.setTitle("View Receptionist");
    nested.show();
    
     
    }
    private void Guest_details(Stage nested,Admin current){
    ComboBox data=new ComboBox();
    ObservableList<String> items = FXCollections.observableArrayList();
    for(int i=0;i<Guest.getList().size();i++){
    String it="";
    it+=(i+1);
    items.add(it);
    }
    data.setItems(items);
    Label recepNo = new Label("Guest ID");
    ListView<String> viewData = new ListView();
    Button back=new Button("Back");
    Button remove=new Button("Remove");
    viewData.setPrefWidth(150);
    viewData.setPrefHeight(250);
    GridPane root= new GridPane();
    root.add(recepNo,0,0);
    root.add(data,1,0);
    root.add(viewData,1,1);
    root.add(back,0,2);
    root.add(remove,1,2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
     root.setStyle("-fx-background-image: url('file:C://fod.png');   "
             + "-fx-background-size: cover;\n" +
"    -fx-background-repeat: no-repeat;");
    data.setOnAction(e->{String get=(String)data.getValue();
                        int id =Integer.parseInt(get);
                        ObservableList<String> itemsss = FXCollections.observableArrayList();
                        itemsss.add(Guest.getList().get(id-1).toString());
                        viewData.setItems(itemsss);});
    back.setOnAction(e->{adminmenu(nested,current);});
    remove.setOnAction(e->{try{
                           String gett=(String)data.getValue();
                        int idd =Integer.parseInt(gett);
                        int number=idd-1;
                        current.removeGuest(number); 
                        Guest_details(nested,current);}
                        catch(Throwable ee){
                        }});
    Scene s= new Scene(root);
    nested.setScene(s);
    nested.setTitle("View Guest");
    nested.show();
    
     
    }
    private void tb_details(Stage nested, Admin current){
        Button view=new Button("View Table");
        Button change=new Button("Change Table");
        Button add=new Button("Add Table");
        Button search=new Button("Search Table");
        Button back=new Button("Back");
        GridPane root=new GridPane();
        root.add(view,0,0);
        root.add(change,1,0);
        root.add(search, 3, 0);
        root.add(add,4,0);
        root.add(back,5,0);
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        Scene s= new Scene(root);
        view.setOnAction(e->{viewTable(nested,current);});
        change.setOnAction(e->{changeTable(nested,current);});
        search.setOnAction(e->{searchTable(nested,current);});
        add.setOnAction(e->{ });//still not done!
        back.setOnAction(e->{adminmenu(nested,current);});//still not done!
        nested.setTitle("Table");
        nested.setScene(s);
        nested.show();
    }
    private void viewTable(Stage nested,Admin current){
    ComboBox b1=new ComboBox();
    ObservableList<String> items = FXCollections.observableArrayList();
    for(int i=0;i<system.Table.getlist().size();i++){
    String it="";
    it+=(i+1);
    items.add(it);
    }
    b1.setItems(items);
    Label l1 = new Label("Table Number");
    ListView<String> l = new ListView();
    Button bt2=new Button("Back");
    l.setPrefWidth(150);
    l.setPrefHeight(250);
    GridPane root= new GridPane();
    root.add(l1,0,0);
    root.add(b1,1,0);
    root.add(l,0,1);
    root.add(bt2,1,2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    b1.setOnAction(e->{
                        try{
                        String get=(String)b1.getValue();
                        int id =Integer.parseInt(get);
                        ObservableList<String> itemss = FXCollections.observableArrayList();
                        itemss.add(Table.getTable(id).toString());
                        l.setItems(itemss);
                        }
                        catch(IndexOutOfBoundsException ee){
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Menu not found");
                            a.show();
                        }
                        });
    bt2.setOnAction(e->{tb_details(nested,current);});
    Scene s= new Scene(root);
    nested.setScene(s);
    nested.setTitle("View Menu");
    nested.show();
    }
    private void changeTable(Stage nested,Admin current){
        Label l1=new Label("Table Number ");
    Label l3=new Label("Table Number ");
    Label NoOfSeats =new Label("Number of seats" );
    Label Categ=new Label("Category");
    Label Cost =new Label("Cost" );
    Button edit=new Button("Edit");
    Button exit=new Button("Back");
    Button remove=new Button("Remove");
    ComboBox b1=new ComboBox();
    TextField cost=new TextField();
    Spinner<Integer> spin=new Spinner<>(); 
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
    spin.setValueFactory(valueFactory);
    ObservableList<String> items = FXCollections.observableArrayList();
    for(int i=0;i<system.Table.getlist().size();i++){
    String it="";
    it+=(i+1);
    items.add(it);
    }
    ComboBox b2=new ComboBox();
    ObservableList<String> itemss = FXCollections.observableArrayList();
    itemss.add("Standard");
    itemss.add("Couples");
    itemss.add("Family");
    itemss.add("Private");
    b1.setItems(items);
    b2.setItems(itemss);
    ComboBox b3=new ComboBox();
    b3.setItems(items);
    GridPane root=new GridPane();
    root.add(l1, 0, 0);
    root.add(b1, 1, 0);
    root.add(Categ, 0, 1);
    root.add(b2, 1, 1);
    root.add(NoOfSeats, 0, 2);
    root.add(spin, 1, 2);
    root.add(Cost, 0, 3);
    root.add(cost, 1, 3);
    root.add(edit, 0, 4);
    root.add(l3, 0, 5);
    root.add(b3, 1, 5);
    root.add(remove, 0, 6);
    root.add(exit,6,6);
    edit.setOnAction(e->{String getmenu=(String)b1.getValue();
                         int id =Integer.parseInt(getmenu);
                         String getcat=(String)b2.getValue();
                         int NoSeats=spin.getValue();
                         int price = Integer.parseInt(cost.getText());
                         if(getcat.equals("Standard")){
                             current.editTable(id-1, Category.Standard, price, NoSeats);
                         }
                         else if(getcat.equals("Couples")){
                             current.editTable(id-1, Category.Couples, price, NoSeats);}
                         else if(getcat.equals("Family")){
                         current.editTable(id-1, Category.Family, price, NoSeats);}
                         else if(getcat.equals("Private")){
                         current.editTable(id-1, Category.Private, price, NoSeats); 
                         }});
    remove.setOnAction(e->{String getmenu=(String)b3.getValue();
                         int idd =Integer.parseInt(getmenu);
                         current.removeTable(idd-1);
                         changeTable(nested,current);});
    exit.setOnAction(e->{tb_details(nested,current);});
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    Scene s=new Scene(root,1000,500);
    nested.setScene(s);
    nested.show();
    }
    private void searchTable(Stage nested,Admin current){
    ListView data=new ListView();    
    Label Categ= new Label("Category");    
    ComboBox Category=new ComboBox();
    ObservableList<String> itemss = FXCollections.observableArrayList();
    itemss.add("Standard");
    itemss.add("Couples");
    itemss.add("Family");
    itemss.add("Private");
    Category.setItems(itemss);
    Button search=new Button("Search");
    Button exit=new Button("Back");
    data.setPrefWidth(150);
    data.setPrefHeight(250);
    GridPane root=new GridPane();
    root.add(Categ, 0, 0);
    root.add(Category, 1, 0);
    root.add(data, 0, 1);
    root.add(search, 0, 2);
    root.add(exit, 1, 2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
   
    search.setOnAction(e->{
                             ArrayList<Table> TablesData = new ArrayList<>() ;
                        String getcat=(String)Category.getValue();
                        if(getcat.equals("Standard")){
                        TablesData=Admin.searchTable(system.Category.Standard);
                        }
                         else if(getcat.equals("Couples")){
                             TablesData=Admin.searchTable(system.Category.Couples);
                         }else if(getcat.equals("Family")){
                         TablesData=Admin.searchTable(system.Category.Family);}
                         else if(getcat.equals("Private")){
                         TablesData=Admin.searchTable(system.Category.Private); 
                         }
                        ObservableList<String> itemsss = FXCollections.observableArrayList();
                        for(int i=0;i<TablesData.size();i++){
                            itemsss.add(TablesData.get(i).toString());
                        }
                        data.setItems(itemsss);
                        });
    exit.setOnAction(e->{tb_details(nested,current);});
    Scene s=new Scene(root,1000,500);
    nested.setScene(s);
    nested.show();
    }
    private void menudetails(Stage nested, Admin current){
    Button bt1=new Button("View Menu");
    Button bt2=new Button("Change menu");
    Button bt4=new Button("Add Meal");
    Button bt5=new Button("Back");
    GridPane root=new GridPane();
    root.add(bt1,0,0);
    root.add(bt2,1,0);
    root.add(bt4,3,0);
    root.add(bt5,4,0);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    bt1.setOnAction(e->{viewmenu(nested,current);});
    bt2.setOnAction(e->{changemenu(nested,current);});
    bt4.setOnAction(e->{addmeal(nested,current);});
    bt5.setOnAction(e->{adminmenu(nested,current);});
    Scene s= new Scene(root,1000,500);
    nested.setScene(s);
    nested.setTitle("Admin Menu");
    nested.show();
    
    }
    private void addmeal(Stage nested,Admin current){
    Label l1=new Label("Menu Id");
    Label l2=new Label("Meal Id");
    Label l3=new Label("Meal Name");
    Label l4=new Label("Price");
    ComboBox b1=new ComboBox();
    Button add=new Button("Add");
    Button Back=new Button("Back");
    ObservableList<String> items = FXCollections.observableArrayList();
    for(int i=0;i<system.Menu.getlist().size();i++){
    String it="";
    it+=(i+1);
    items.add(it);
    }
    b1.setItems(items);
    TextField mealid=new TextField();
    TextField mealName=new TextField();
    TextField mealPrice=new TextField();
    mealid.setPromptText("Meal Id");
    mealName.setPromptText("Meal Name");
    mealPrice.setPromptText("Meal Price");
    GridPane root=new GridPane();
    root.add(l1,0,0);
    root.add(b1,1,0);
    root.add(l2,0,1);
    root.add(mealid,1,1);
    root.add(l3,0,2);
    root.add(mealName,1,2);
    root.add(l4,0,3);
    root.add(mealPrice,1,3);
    root.add(add,0,4);
    root.add(Back,1,4);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    add.setOnAction(e->{String getmenu=(String)b1.getValue();
                         int id =(Integer.parseInt(getmenu));
                         int Mealid=Integer.parseInt(mealid.getText());
                         String name=mealName.getText();
                         double price=Double.parseDouble(mealPrice.getText());
                         new Meal(id, Mealid, name, price);
    });
    Back.setOnAction(e->{menudetails(nested, current);});
    Scene s=new Scene(root,1000,500);
    nested.setScene(s);
    nested.show();
    }
    private void changemenu(Stage nested,Admin current){
    Label l1=new Label("Menu Id ");
    Label l3=new Label("Menu Id ");
    Label l2 =new Label("New Category " );
    Button edit=new Button("Edit");
    Button exit=new Button("Back");
    Button remove=new Button("Remove");
    ComboBox b1=new ComboBox();
    ObservableList<String> items = FXCollections.observableArrayList();
    for(int i=0;i<system.Menu.getlist().size();i++){
    String it="";
    it+=(i+1);
    items.add(it);
    }
    ComboBox b2=new ComboBox();
    ObservableList<String> itemss = FXCollections.observableArrayList();
    itemss.add("Breakfast");
    itemss.add("Lunch");
    itemss.add("Beverages");
    itemss.add("Dinner");
    itemss.add("Dessert");
    b1.setItems(items);
    b2.setItems(itemss);
    ComboBox b3=new ComboBox();
    b3.setItems(items);
    GridPane root=new GridPane();
    root.add(l1, 0, 0);
    root.add(b1, 1, 0);
    root.add(l2, 0, 1);
    root.add(b2, 1, 1);
    root.add(edit, 0, 2);
    root.add(l3, 0, 3);
    root.add(b3, 1, 3);
    root.add(remove, 0, 4);
    root.add(exit,5,5);
    edit.setOnAction(e->{String getmenu=(String)b1.getValue();
                         int id =Integer.parseInt(getmenu);
                         String getcat=(String)b2.getValue();
                         if(getcat.equals("Lunch")){
                             current.editMenu(id-1, MenuCategory.Lunch);
                         }
                         else if(getcat.equals("Dinner")){
                             current.editMenu(id-1, MenuCategory.Dinner);}
                         else if(getcat.equals("Breakfast")){
                         current.editMenu(id-1, MenuCategory.Breakfast);}
                         else if(getcat.equals("Dessert")){
                         current.editMenu(id-1, MenuCategory.Dessert); 
                         }
                         else if(getcat.equals("Beverages")){
                         current.editMenu(id-1, MenuCategory.Beverages);}
                         else if(getcat.equals("Lunch")){
                         current.editMenu(id-1, MenuCategory.Beverages);}});
    remove.setOnAction(e->{String getmenu=(String)b3.getValue();
                         int idd =Integer.parseInt(getmenu);
                         current.removeMenu(idd-1);});
    exit.setOnAction(e->{menudetails(nested,current);});
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    Scene s=new Scene(root,1000,500);
    nested.setScene(s);
    nested.show();
    
    }
    private void viewmenu(Stage nested,Admin current){
    ComboBox b1=new ComboBox();
    ObservableList<String> items = FXCollections.observableArrayList();
    for(int i=0;i<system.Menu.getlist().size();i++){
    String it="";
    it+=(i+1);
    items.add(it);
    }
    b1.setItems(items);
    Label l1 = new Label("Menu Id");
    ListView<String> l = new ListView();
    Button bt1=new Button("View");
    Button bt2=new Button("Back");
    l.setPrefWidth(150);
    l.setPrefHeight(250);
    GridPane root= new GridPane();
    root.add(l1,0,0);
    root.add(b1,1,0);
    root.add(l,0,1);
    root.add(bt1,0,2);
    root.add(bt2,1,2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    b1.setOnAction(e->{
                        try{
                        String get=(String)b1.getValue();
                        int id =Integer.parseInt(get);
                        if(id<1||id>system.Menu.getlist().size()+1){
                        Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Menu not found");
                            a.show();
                        }else{
                        ArrayList<Meal> m=Admin.searchMenu(id-1);
                        ObservableList<String> itemss = FXCollections.observableArrayList();
                        itemss.add(system.Menu.getlist().get(id-1).toString());
                        for(int i =0;i<m.size();i++){
                        itemss.add(m.get(i).getName());
                        }
                        l.setItems(itemss);}
                        }
                        catch(IndexOutOfBoundsException ee){
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("Menu not found");
                            a.show();
                        }
                        });
    
    bt2.setOnAction(e->{menudetails(nested,current);});
    Scene s= new Scene(root,1000,500);
    nested.setScene(s);
    nested.setTitle("View Menu");
    nested.show();
    }
    private void usercreate(Stage nested,Admin current){
    Button bt1=new Button ("Admin create");
    Button bt2=new Button("Receptionist create");
    Button bt3=new Button("Back");
    GridPane root = new GridPane();
    root.add(bt1,0,0);
    root.add(bt2,1,0);
    root.add(bt3,2,0);
    bt1.setPrefHeight(30);
    bt1.setPrefWidth(120);
    bt2.setPrefHeight(30);
    bt2.setPrefWidth(120);
    bt3.setPrefHeight(30);
    bt3.setPrefWidth(120);
    root.setPadding(new Insets(20));
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    bt1.setOnAction(e->{admincreate(nested,current);});
    bt2.setOnAction(e->{receptionistcreate(nested,current);});
    bt3.setOnAction(e->{adminmenu(nested,current);});
    Scene s =new Scene(root,1000,500);
    nested.setTitle("User");
    nested.setScene(s);
    nested.show();
    }
    private void admincreate(Stage nested,Admin current){
        Button bt1=new Button("Sign up");
        Button bt2=new Button("Back");
        Label l1= new Label("Name");
        Label l2= new Label("Address");
        Label l3= new Label("Date of Birth");
        Label l4= new Label("Phone no:");
        Label l5= new Label("Email");
        Label l6= new Label("User Name");
        Label l7= new Label("Password");
        TextField na = new TextField();
        TextField ad = new TextField();
        TextField da = new TextField();
        TextField ph = new TextField();
        TextField em = new TextField();
        TextField us = new TextField();
        TextField pas = new TextField();
        GridPane root = new GridPane();
        root.add(l1,0,0);
        root.add(l2,0,1);
        root.add(l3,0,2);
        root.add(l4,0,3);
        root.add(l5,0,4);
        root.add(l6,0,5);
        root.add(l7,0,6);
        root.add(na,1,0);
        root.add(ad,1,1);
        root.add(da,1,2);
        root.add(ph,1,3);
        root.add(em,1,4);
        root.add(us,1,5);
        root.add(pas,1,6);
        root.add(bt1,0,7);
        root.add(bt2, 1, 7);
        root.setPadding(new Insets(20));
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.TOP_LEFT);
        bt1.setOnAction(e->{String name=na.getText();
                            String Address=ad.getText();
                            String date= da.getText();
                            String phone =ph.getText();
                            String email=em.getText();
                            String user=us.getText();
                            String password=pas.getText();
                            Boolean check=true;
                            for(int i=0;i<Admin.getAdmins().size();i++){
                            if(Admin.getAdmins().get(i).getUserName().equals(user)){
                            check=false;
                            break;
                            }
                            }
                            if(check==true){
                            new Admin(name,Address,date,phone,email,user,password);
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("Success");
                            a.setHeaderText("Account created");
                            a.show();
                            usercreate(nested,current);
                            }
                            else if(check==false){
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("UserName already used");
                            a.show();
                            }
                            
                               });
        bt2.setOnAction(e->{usercreate(nested,current);});
        Scene s =new Scene(root,1000,500);
        nested.setTitle("Admin");
        nested.setScene(s);
        nested.show();

    }
    private void receptionistcreate(Stage nested,Admin current){
    Button bt1=new Button("Sign up");
        Button bt2=new Button("Back");
        Label l1= new Label("Name");
        Label l2= new Label("Address");
        Label l3= new Label("Date of Birth");
        Label l4= new Label("Phone no:");
        Label l5= new Label("Email");
        Label l6= new Label("User Name");
        Label l7= new Label("Password");
        TextField na = new TextField();
        TextField ad = new TextField();
        TextField da = new TextField();
        TextField ph = new TextField();
        TextField em = new TextField();
        TextField us = new TextField();
        TextField pas = new TextField();
        GridPane root = new GridPane();
        root.add(l1,0,0);
        root.add(l2,0,1);
        root.add(l3,0,2);
        root.add(l4,0,3);
        root.add(l5,0,4);
        root.add(l6,0,5);
        root.add(l7,0,6);
        root.add(na,1,0);
        root.add(ad,1,1);
        root.add(da,1,2);
        root.add(ph,1,3);
        root.add(em,1,4);
        root.add(us,1,5);
        root.add(pas,1,6);
        root.add(bt1,0,7);
        root.add(bt2, 1, 7);
        root.setPadding(new Insets(20));
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.TOP_LEFT);
        bt1.setOnAction(e->{String name=na.getText();
                            String Address=ad.getText();
                            String date= da.getText();
                            String phone =ph.getText();
                            String email=em.getText();
                            String user=us.getText();
                            String password=pas.getText();
                            Boolean check=true;
                            for(int i=0;i<Receptionist.getList().size();i++){
                            if(Receptionist.getList().get(i).getUserName().equals(user)){
                            check=false;
                            break;
                            }
                            }
                            if(check==true){
                            new Receptionist(name,Address,date,phone,email,user,password);
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("Success");
                            a.setHeaderText("Account created");
                            a.show();
                            usercreate(nested,current);
                            }
                            else if(check==false){
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Error");
                            a.setHeaderText("UserName already used");
                            a.show();
                            }
                            
                               });
        bt2.setOnAction(e->{usercreate(nested,current);});
        Scene s =new Scene(root,1000,500);
        nested.setTitle("Receptionist");
        nested.setScene(s);
        nested.show();
    }
    private void guestPreferencesMenu(Stage mainWindow,Receptionist current){
        //Declaration of Nodes
        Text headerText=new Text("Select Guest Preferences");
        headerText.setFont(Font.font("Cosmic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,36));
        ComboBox<String> names=new ComboBox<>();
        names.setPromptText("Select Guest");
        ComboBox<String> pref=new ComboBox<>(FXCollections.observableArrayList("Standard","Couples","Family","Private"));
        pref.setPromptText("Select Preferred Category");
        Button back=new Button("Back");
        Button btSelect=new Button("Select");

        //Designing of layout
        HBox backBox=new HBox(back);
        backBox.setAlignment(Pos.CENTER_LEFT);
        backBox.setPadding(new Insets(5,0,0,10));
        HBox header=new HBox(headerText);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(30));
        HBox buttonBox=new HBox(btSelect);
        buttonBox.setAlignment(Pos.BASELINE_RIGHT);
        buttonBox.setPadding(new Insets(20));
        VBox body=new VBox(10,names,pref);
        body.setAlignment(Pos.CENTER);
        VBox root=new VBox(backBox,header,body,buttonBox);

        //Loading Nodes with content
        ArrayList<Guest> guests=Guest.getList();
        ObservableList<String> guestNames=FXCollections.observableArrayList();
        for(Guest iter:guests){ guestNames.add(iter.getName());}
        names.setItems(guestNames);

        //Event Handling of nodes
        back.setOnAction(e->{recepOptions(mainWindow,current);});
        btSelect.setOnAction(e->{
            if(names.getSelectionModel().getSelectedIndex()>=0&&
            pref.getSelectionModel().getSelectedIndex()>=0){
                guests.get(names.getSelectionModel().getSelectedIndex()).setPreferedCategory(pref.getSelectionModel().getSelectedIndex());
                names.getSelectionModel().select(-1);
                pref.getSelectionModel().select(-1);
                Alert warning=new Alert(Alert.AlertType.INFORMATION);
                warning.setHeaderText(null);
                warning.setContentText("Preferred Category has changed successfully");
                warning.showAndWait();
            }else{
                Alert warning=new Alert(Alert.AlertType.ERROR);
                warning.setHeaderText(null);
                warning.setContentText("Please fill All Required Fields");
                warning.showAndWait();
            }
        });

        //Scene & Main window modification
        Scene guestPrefScene=new Scene(root,500,250);
        mainWindow.setScene(guestPrefScene);
        mainWindow.setTitle("Select Guest Preferences");
    }
    private void cancelReservationMenu(Stage mainWindow,Receptionist current){
        //Declaration of Nodes
        Text headerText=new Text("Cancel Reservation");
        headerText.setFont(Font.font("Cosmic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,36));
        ComboBox<Integer> reservationNumbers=new ComboBox<>();
        reservationNumbers.setPromptText("Reservation ID");
        TextArea reservationDetails=new TextArea();
        reservationDetails.setEditable(false);
        reservationDetails.setPrefHeight(300);
        Button btDelete=new Button("Delete Reservation");
        Button back=new Button("Back");

        //Designing of layout
        HBox backBox=new HBox(back);
        backBox.setAlignment(Pos.CENTER_LEFT);
        HBox header=new HBox(headerText);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(30));
        VBox body=new VBox(20,reservationNumbers,reservationDetails);
        body.setAlignment(Pos.CENTER);
        body.setPadding(new Insets(0,10,0,10));
        HBox buttonBox=new HBox(btDelete);
        buttonBox.setAlignment(Pos.BASELINE_RIGHT);
        buttonBox.setPadding(new Insets(20));
        VBox root=new VBox(backBox,header,body,buttonBox);

        //Loading Nodes with content
        ArrayList<Reservation>reservations=Reservation.search(current);
        ObservableList<Integer> resId=FXCollections.observableArrayList();
        for(Reservation iter:reservations){
            resId.add(iter.getReservationNumber());
        }
        reservationNumbers.setItems(resId);

        //Event Handling of nodes
        back.setOnAction(e->{recepOptions(mainWindow,current);});
        reservationNumbers.setOnAction(e->{
            int index=reservationNumbers.getSelectionModel().getSelectedIndex();
            if(index>=0){
                reservationDetails.setText("Reservation Details:-\n"+reservations.get(index).toString());
            }
        });
        btDelete.setOnAction(e->{
            int index=reservationNumbers.getValue();
            if(index>0){
                try{
                    current.cancelReservation(index);
                }catch (InvalidAttributeValueException ignored){}
            }
            reservationNumbers.getItems().clear();
            reservationDetails.clear();
            ArrayList<Reservation> newReservations=Reservation.search(current);
            ObservableList<Integer> newResId=FXCollections.observableArrayList();
            for(Reservation iter:newReservations){
                newResId.add(iter.getReservationNumber());
            }
            reservationNumbers.setItems(newResId);
        });

        //Scene & Main window modification
        Scene cancelResScene=new Scene(root,1000,500);
        mainWindow.setScene(cancelResScene);
        mainWindow.setTitle("Cancel Reservation");
    }
    private void createReservationMenu(Stage mainWindow,Receptionist current){
        //Declaration of Nodes
        TextArea order=new TextArea();
        order.setPrefSize(200,400);
        order.setEditable(false);
        Button btCrRes=new Button("Create Reservation"),btCancel=new Button("Cancel"),btAdd=new Button("Add");
        Button back=new Button("Back");
        ArrayList<ComboBox<String>> tiles=new ArrayList<>(9);
        /*O->Guest Names,1->Table number,2->Number of Guests,3->Start Hours,4->Start Minutes
        5->End Hours,6->End Minutes,7->Menus,8->Meals*/
        ArrayList<Guest> guests=Guest.getList();
        ArrayList<system.Menu> menus=system.Menu.getlist();
        ArrayList<ObservableList<String>> content= new ArrayList<>(4);
        //0->Contents of Guest Names,1->Contents of Hours,2->Contents of Minutes,3->Content of Menus Names

        //Loading Nodes with content
        for(int i=0;i<4;i++){ content.add(FXCollections.observableArrayList()); }
        for(Guest temp:guests){ content.get(0).add(temp.getName()); }
        for(system.Menu temp:menus){ content.get(3).add(temp.toString()); }
        tiles.add(new ComboBox<>(content.get(0)));
        for(int i=1;i<9;i++) {
            tiles.add(new ComboBox<>());
        }
        tiles.get(0).setPromptText("Select Guest");
        tiles.get(0).setPrefWidth(190);
        tiles.get(1).setPromptText("Table");
        tiles.get(1).setPrefWidth(120);
        tiles.get(2).setPromptText("Num of Guests");
        tiles.get(3).setPromptText("hh");
        tiles.get(4).setPromptText("mm");
        tiles.get(5).setPromptText("hh");
        tiles.get(6).setPromptText("mm");
        tiles.get(7).setPromptText("Menu");
        tiles.get(7).setPrefWidth(120);
        tiles.get(8).setPromptText("Meals");
        tiles.get(8).setPrefWidth(120);
        DatePicker day=new DatePicker(LocalDate.now());
        day.setPrefWidth(190);
        for(int i=0;i<10;i++){content.get(1).add(("0"+Integer.toString(i)));}
        for(int i=10;i<24;i++){content.get(1).add((Integer.toString(i)));}
        for(int i=0;i<10;i+=5){content.get(2).add(("0"+Integer.toString(i)));}
        for(int i=10;i<60;i+=5){content.get(2).add((Integer.toString(i)));}
        for(int i=3;i<7;i+=2){tiles.get(i).setItems(content.get(1));}
        for(int i=4;i<7;i+=2){tiles.get(i).setItems(content.get(2));}
        tiles.get(7).setItems(content.get(3));

        //Event Handling of Nodes
        EventHandler<ActionEvent> updatingTable=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if((tiles.get(3).getValue()!=null)&&(tiles.get(4).getValue()!=null)&&
                        (tiles.get(5).getValue()!=null)&&(tiles.get(6).getValue()!=null)&&
                        (day.getEditor().getText()!=null)){
                    String start=(tiles.get(3).getValue()+":"+tiles.get(4).getValue());
                    String end=(tiles.get(5).getValue()+":"+tiles.get(6).getValue());
                    LocalTime startTime=null;
                    LocalTime endTime=null;
                    Date reserving=null;
                    SimpleDateFormat sf=new SimpleDateFormat("MM/dd/yyyy");
                    try{
                        startTime=LocalTime.parse(start);
                    }catch (DateTimeParseException exception){
                        System.out.println("Start Couldn't be parsed");
                    }
                    try{
                        endTime=LocalTime.parse(end);
                    }catch (DateTimeParseException exception){
                        System.out.println("End Couldn't be parsed");
                    }
                    try{
                        reserving=sf.parse(day.getEditor().getText());
                    }catch(ParseException exception){
                        System.out.println("Date Couldn't be parsed");
                    }
                    ArrayList<Table> available=Table.searchByDate(reserving,startTime,endTime);
                    ObservableList<String> tableIds=FXCollections.observableArrayList();
                    for(Table temp: available){tableIds.add(Integer.valueOf(temp.getTableID()).toString());}
                    tiles.get(1).setItems(tableIds);
                }
            }
        };
        day.setOnAction(updatingTable);
        tiles.get(3).setOnAction(updatingTable);
        tiles.get(4).setOnAction(updatingTable);
        tiles.get(5).setOnAction(updatingTable);
        tiles.get(6).setOnAction(updatingTable);
        tiles.get(1).setOnAction(e->{
            int max=0;
            try{
                if(tiles.get(1).getValue()!=null){
                    max=Table.getTable(Integer.valueOf(tiles.get(1).getValue()).intValue()).getNoOfSeats();
                }
            }catch (NumberFormatException ignored){}
            ObservableList<String> numOfGuests=FXCollections.observableArrayList();
            for(int i=1;i<=max;i++){numOfGuests.add(Integer.valueOf(i).toString());}
            tiles.get(2).setItems(numOfGuests);
        });
        tiles.get(7).setOnAction(e->{
            int index=tiles.get(7).getSelectionModel().getSelectedIndex();
            if(index>=0){
                ArrayList<Meal> menuMeals=menus.get(index).getMeals();
                ObservableList<String> meals=FXCollections.observableArrayList();
                for(Meal temp:menuMeals){meals.add(temp.getName());}
                tiles.get(8).setItems(meals);
            }
        });
        back.setOnAction(e->{recepOptions(mainWindow,current);});
        btAdd.setOnAction(e->{
            if(tiles.get(8).getValue()!=null){
                order.appendText(tiles.get(8).getValue()+"\n");
            }
        });
        btCrRes.setOnAction(e->{
            if(tiles.get(0).getSelectionModel().getSelectedIndex()>=0&&
                    tiles.get(1).getSelectionModel().getSelectedIndex()>=0&&
                    tiles.get(2).getSelectionModel().getSelectedIndex()>=0&&
                    tiles.get(3).getSelectionModel().getSelectedIndex()>=0&&
                    tiles.get(4).getSelectionModel().getSelectedIndex()>=0&&
                    tiles.get(5).getSelectionModel().getSelectedIndex()>=0&&
                    tiles.get(6).getSelectionModel().getSelectedIndex()>=0&&
                    day.getValue()!=null){
                int guestId=guests.get(tiles.get(0).getSelectionModel().getSelectedIndex()).getId();
                int tableNum=Integer.valueOf(tiles.get(1).getValue());
                int numOfGuests=Integer.valueOf(tiles.get(2).getValue());
                String startTime=tiles.get(3).getValue()+':'+tiles.get(4).getValue();
                String endTime=tiles.get(5).getValue()+':'+tiles.get(6).getValue();
                String orderInString=null;
                String[] mealsInString=null;
                ArrayList<Meal> actualMeals=new ArrayList<>();
                if(order.getText()!=null&& !order.getText().isEmpty()){
                    orderInString=order.getText();
                    mealsInString=orderInString.split("\n");
                    for(String tmp:mealsInString) {
                        actualMeals.add(Meal.getMealByNames(tmp));
                    }
                }
                try {
                    current.createReservation(guestId,tableNum,numOfGuests,day.getEditor().getText(),startTime,endTime,actualMeals);
                    btCancel.fire();
                } catch (InvalidAttributeValueException | ParseException ex) {
                    Alert warning=new Alert(Alert.AlertType.ERROR);
                    warning.setHeaderText(null);
                    warning.setContentText(ex.getMessage());
                    warning.showAndWait();
                }

            }else{
                Alert warning=new Alert(Alert.AlertType.ERROR);
                warning.setHeaderText(null);
                warning.setContentText("Please fill All Required Fields");
                warning.showAndWait();
            }
        });
        btCancel.setOnAction(e->{
            for(ComboBox<String> temp:tiles){
                temp.getSelectionModel().select(-1);
            }
            day.getEditor().setText(day.getConverter().toString(LocalDate.now()));
            order.clear();
            tiles.get(2).getItems().clear();
            tiles.get(1).getItems().clear();
            tiles.get(8).getItems().clear();
        });

        //Designing of layout
        HBox backBox=new HBox(back);
        backBox.setAlignment(Pos.CENTER_LEFT);
        Text headerText=new Text("Create Reservation");
        headerText.setFont(Font.font("Cosmic Sans MS",FontWeight.BOLD,FontPosture.REGULAR,36));
        ArrayList<VBox> columns=new ArrayList<>(4);
        HBox btAddBox=new HBox(btAdd);
        HBox btCrCanBox=new HBox(3,btCrRes,btCancel);
        btAddBox.setAlignment(Pos.BASELINE_RIGHT);
        btCrCanBox.setAlignment(Pos.BASELINE_RIGHT);
        btCrCanBox.setPadding(new Insets(0,0,10,0));
        columns.add(new VBox(30,tiles.get(0),day,new HBox(3,new Label("Start Time:"),tiles.get(3),tiles.get(4)),new HBox(3,new Label("End Time:  "),tiles.get(5),tiles.get(6))));
        columns.add(new VBox(30,tiles.get(1),tiles.get(2)));
        columns.add(new VBox(30,tiles.get(7),tiles.get(8),btAddBox));
        columns.add(new VBox(20,order,btCrCanBox));
        for(int i=0;i<3;i++){columns.get(i).setPadding(new Insets(0,10,0,10));}
        HBox header=new HBox(headerText);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(50));
        HBox body=new HBox();
        for(VBox temp:columns){ body.getChildren().add(temp); }
        VBox root=new VBox(backBox,header,body);

        //Scene & Main window modification
        Scene crResScene=new Scene(root,700,400);
        mainWindow.setScene(crResScene);
        mainWindow.setTitle("Create Reservation");
        mainWindow.setResizable(false);
    }
    private void recepOptions(Stage mainWindow,Receptionist current){
        //Nodes Declaration
        ArrayList<VBox> columns=new ArrayList<>(3);
        ArrayList<ImageView> images=new ArrayList<>(3);
        ArrayList<Text> titles=new ArrayList<>(3);
        Button btBack=new Button("Back");
        titles.add(new Text("Create Reservation"));
        titles.add(new Text("Cancel Reservation"));
        titles.add(new Text("Select Guest Preferences"));
        images.add(new ImageView(new Image("file:D:\\take_reservation.jpg")));
        images.add(new ImageView(new Image("file:D:\\reservation.jpg")));
        images.add(new ImageView(new Image("file:D:\\guest icon.png")));

        //Nodes Styling
        for(ImageView temp:images){temp.setFitHeight(180); temp.setFitWidth(230);}
        for(Text temp:titles){temp.setFont(Font.font("Cosmic Sans MS",FontWeight.BLACK,FontPosture.REGULAR,28));}

        //Layout Building
        for(int i=0;i<3;i++){ columns.add(new VBox(20,images.get(i),titles.get(i))); }
        VBox buttonBox=new VBox(btBack);
        HBox layout=new HBox(50,buttonBox);
        for(VBox temp:columns){layout.getChildren().add(temp); temp.setAlignment(Pos.CENTER);}
        layout.setAlignment(Pos.CENTER);
        VBox.setMargin(images.get(2),new Insets(0,20,0,0));
        VBox.setMargin(titles.get(2),new Insets(0,20,0,0));

        //Animations
        ArrayList<ScaleTransition> zooming=new ArrayList<>(6);
        for(int i=0;i<3;i++){
            zooming.add(new ScaleTransition(Duration.millis(400),images.get(i)));
            zooming.get(i).setByX(0.5); zooming.get(i).setByY(0.5);
            zooming.get(i).setCycleCount(2);
            zooming.get(i).setAutoReverse(true);
        }
        for(int i=3;i<6;i++){
            zooming.add(new ScaleTransition(Duration.millis(400),titles.get(i-3)));
            zooming.get(i).setByX(0.5); zooming.get(i).setByY(0.5);
            zooming.get(i).setCycleCount(2);
            zooming.get(i).setAutoReverse(true);
        }
        ArrayList<ParallelTransition> tileTransition=new ArrayList<>(3);
        for(int i=0;i<3;i++){
            tileTransition.add(new ParallelTransition(zooming.get(i),zooming.get(i+3)));
        }

        //Event Handling of Nodes
        columns.get(0).setOnMouseEntered(e->{
            tileTransition.get(0).play();
        });
        columns.get(1).setOnMouseEntered(e->{
            tileTransition.get(1).play();
        });
        columns.get(2).setOnMouseEntered(e->{
            tileTransition.get(2).play();
        });
        columns.get(0).setOnMouseClicked(e->{
            createReservationMenu(mainWindow, current);
        });
        columns.get(1).setOnMouseClicked(e->{
            cancelReservationMenu(mainWindow, current);
        });
        columns.get(2).setOnMouseClicked(e->{
            guestPreferencesMenu(mainWindow, current);
        });
        btBack.setOnAction(e->{
            recepLogin(mainWindow);
        });
        //Scene & Main window modification
        Scene optionsScene=new Scene(layout,1035,500);
        columns.get(0).requestFocus();
        mainWindow.setResizable(false);
        mainWindow.setScene(optionsScene);
        mainWindow.setTitle("Receptionist Options");
    }
    private void recepLogin(Stage mainWindow){
        //Fonts
        Font lbFont=Font.font("Cosmic Sans MS",FontWeight.MEDIUM,FontPosture.REGULAR,16);

        //Nodes Declaration
        Text header=new Text("Log In");
        TextField tfUN=new TextField();
        PasswordField pfPW=new PasswordField();
        Label lbUN=new Label("Username: "),lbPW=new Label("Password: ");
        Button btLogIn=new Button("Log In"),btCancel=new Button("Cancel");
        Button btBack=new Button("Back");

        //Nodes Styling
        header.setFont(Font.font("Cosmic Sans MS", FontWeight.BOLD, FontPosture.REGULAR,48));
        tfUN.setPromptText("Enter your username here");
        pfPW.setPromptText("Enter your password here");
        tfUN.setPrefSize(200,30);
        pfPW.setPrefSize(200,30);
        lbUN.setFont(lbFont);
        lbPW.setFont(lbFont);
        lbUN.setPrefSize(90,20);
        lbPW.setPrefSize(90,20);

        //Event-handling of Nodes
        tfUN.setOnKeyPressed(e->{
            if(!tfUN.getText().isEmpty()&&e.getCode().equals(KeyCode.ENTER)){
                pfPW.requestFocus();
            }
        });
        pfPW.setOnKeyPressed(e->{
            if(!tfUN.getText().isEmpty()&&!pfPW.getText().isEmpty()&&e.getCode().equals(KeyCode.ENTER)){
                btLogIn.fire();
            }
        });
        btLogIn.setOnAction(e->{
            Receptionist temp=null;
            try{
                temp=Receptionist.login(tfUN.getText(),pfPW.getText());
                recepOptions(mainWindow,temp);
            }catch(InvalidAttributeValueException excep){
                Alert invalidLogin=new Alert(Alert.AlertType.ERROR);
                invalidLogin.setHeaderText(null);
                invalidLogin.setContentText(excep.getMessage());
                invalidLogin.showAndWait();
            }
        });
        btCancel.setOnAction(e->{
            tfUN.clear();
            pfPW.clear();
        });
        btBack.setOnAction(e->{
            start(mainWindow);
        });

        //Layout Building
        ArrayList<HBox> rows=new ArrayList<>(3);
        rows.add(new HBox(btBack));
        rows.add(new HBox(header));
        rows.add(new HBox(5,lbUN,tfUN));
        rows.add(new HBox(6,lbPW,pfPW));
        rows.add(new HBox(7,btLogIn,btCancel));
        rows.get(0).setAlignment(Pos.TOP_LEFT);
        rows.get(1).setPadding(new Insets(0,0,30,0));
        rows.get(1).setAlignment(Pos.CENTER);
        rows.get(2).setAlignment(Pos.CENTER);
        rows.get(3).setAlignment(Pos.CENTER);
        rows.get(4).setAlignment(Pos.CENTER_RIGHT);
        rows.get(4).setPadding(new Insets(50,0,0,0));
        VBox layout=new VBox(5);
        layout.setPadding(new Insets(10));
        for (HBox row : rows) layout.getChildren().add(row);

        //Scene & Main window modification
        Scene LogInScene=new Scene(layout,500,300);
        mainWindow.setScene(LogInScene);
        mainWindow.setTitle("Log In");
        mainWindow.setResizable(false);
    }
    private void guestlogin(Stage nested){
    Button bt1=new Button("Back");
    Button bt2 = new Button("Login");
    Button bt3 = new Button("Sign up");
    Label l1=new Label("UserName");
    Label l2=new Label("Password");
    bt1.setPrefHeight(30);
    bt1.setPrefWidth(80);
    bt2.setPrefHeight(30);
    bt2.setPrefWidth(80);
    l1.setPrefHeight(40);
    l1.setPrefWidth(100);
    l2.setPrefHeight(40);
    l2.setPrefWidth(100);
    PasswordField pas=new PasswordField();
    TextField name=new TextField();
    GridPane root=new GridPane();
    bt1.getStyleClass().add("custom-button");
    bt2.getStyleClass().add("custom-button");
    bt3.getStyleClass().add("custom-button");
    name.getStyleClass().add("custom-textfield");
    pas.getStyleClass().add("custom-passwordfield");
    root.add(l1,0,0);
    root.add(name,1,0);
    root.add(l2,0,1);
    root.add(pas,1,1);
    root.add(bt2,0,2);
    root.add(bt3,1,2);
    root.add(bt1,2,2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    Scene s=new Scene(root);
    bt1.setOnAction(e->{nested.setScene(start);});
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
    bt3.setOnAction(e->{guestcreate(nested);});
    nested.setScene(s);
    root.setStyle("-fx-background-image: url('file:C://test.jpg');   "
             + "-fx-background-size: cover;\n" +
"    -fx-background-repeat: no-repeat;");
    s.getStylesheets().add("/restaurant/styles.css");
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
        new Menu(MenuCategory.Beverages);
        new Menu(MenuCategory.Breakfast);
        new Menu(MenuCategory.Lunch);
        new Menu(MenuCategory.Dinner);
        new Menu(MenuCategory.Dessert);
        new Meal(1,1,"Spiro spats",15);
        new Meal(1,2,"Mango juice",15);
        new Meal(1,3,"Strawberry",15);
        new Meal(2,4,"Eggs",15);
        new Meal(2,5,"French Toast",15);
        new Meal(2,6,"Croissant",15);
        new Meal(3,7,"Steak",15);
        new Meal(3,8,"rice",15);
        new Meal(3,9,"Shawerma",15);
        new Meal(4,10,"la vache quere",15);
        new Meal(4,11,"salad",15);
        new Meal(4,12,"honey",15);
        new Meal(5,13,"Molten Cake",15);
        new Meal(5,14,"Cheese Cake",15);
        new Meal(5,15,"Carrot Cake",15);
        new Table(3);
        new Table(4);
        new Table(10);
        new Table(20);
        new Admin("Frank", "Paris", "19-2-2001", "01069856960", "frank.heisenberg@gmail.com", "A", "A");
        new Admin("John","Chicago","8-5-1962","01200588939","johnwhite@gmail.com","B","B");
        new Guest("Frank", "Paris", "19-2-2001", "01069856960", "frank.heisenberg@gmail.com", "E", "E");
        new Guest("John","Chicago","8-5-1962","01200588939","johnwhite@gmail.com","F","F");
        new Guest("Ayman","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        new Guest("Zakaria","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        new Guest("Mahmoud","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        new Receptionist("Nehad","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        new Receptionist("David", "NewZealand", "19-2-2001", "01069856960", "frank.heisenberg@gmail.com", "C", "C");
        new Receptionist("Mark","China","8-5-1962","01200588939","johnwhite@gmail.com","D","D");
        launch(args);
    } 
}
