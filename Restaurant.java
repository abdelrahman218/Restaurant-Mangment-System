package restaurant;
import employees.*;
import system.*;
import user.Guest;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import javafx.scene.image.Image;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
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
import javafx.stage.Screen;
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
    new Table(4);
    new Table(2);
    new Table(6);
    new Meal(1,1,"Egg",3.5);
    new Meal(1,2,"Bread",1.5);
    new Meal(2,3,"spiro spathis",10);
    new system.Menu(MenuCategory.Breakfast);
    new system.Menu(MenuCategory.Beverages);
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
    bt2.setOnAction(e->{receptionistlogin(primaryStage);});
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
     root.setStyle("-fx-background-image: url('file:C://fod.png');   "
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
    Button bt1 = new Button("Create User");
    Button bt2 = new Button("Table details");
    Button bt3 = new Button("Receptionist details");
    Button bt4 = new Button("Menu details");
    Button bt5 =new Button("Guest details");
    Button bt6 = new Button("View Logs");
    Button bt7 = new Button("Sign out");
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
    bt7.setPrefHeight(30);
    bt7.setPrefWidth(120);
    GridPane root=new GridPane();
    root.add(bt6,2,0);
    root.add(bt1,0,1);
    root.add(bt2,1,1);
    root.add(bt3,2,1);
    root.add(bt4,3,1);
    root.add(bt5,4,1);
    root.add(bt7,2,2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    Scene s= new Scene(root);
    bt1.setOnAction(e->{usercreate(nested,current);});
    bt2.setOnAction(e->{tb_details(nested,current);});
    bt4.setOnAction(e->{menudetails(nested,current);});
    bt7.setOnAction(e->{adminlogin(nested);});
    nested.setTitle("Admin Menu");
    nested.setScene(s);
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
    Scene s= new Scene(root,1000,500);
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
                         int id =(Integer.parseInt(getmenu)-1);
                         int Mealid=Integer.parseInt(mealid.getText());
                         String name=mealName.getText();
                         double price=Double.parseDouble(mealPrice.getText());
                         current.createMeal(id, Mealid, name, price);
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
    private void receptionistlogin(Stage nested){
    Button bt1=new Button("Back");
    Button bt2 = new Button("Login");
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
    bt1.getStyleClass().add("custom-button");
    bt2.getStyleClass().add("custom-button");
    PasswordField pas=new PasswordField();
    TextField name=new TextField();
    name.getStyleClass().add("custom-textfield");
    pas.getStyleClass().add("custom-passwordfield");
    GridPane root=new GridPane();
    root.add(l1,0,0);
    root.add(name,1,0);
    root.add(l2,0,1);
    root.add(pas,1,1);
    root.add(bt2,0,2);
    root.add(bt1,1,2);
    root.setHgap(10);
    root.setVgap(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    Scene s=new Scene(root);
    bt1.setOnAction(e->{nested.setScene(start);});
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
    root.setStyle("-fx-background-image: url('file:C://test.jpg');   "
             + "-fx-background-size: cover;\n" +
"    -fx-background-repeat: no-repeat;");
    nested.setScene(s);
    s.getStylesheets().add("/restaurant/styles.css");
    nested.setTitle("Login");
    nested.show();
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
        launch(args);
    } 
}
