package system;
import employees.Receptionist;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import user.Guest;
import javax.management.InvalidAttributeValueException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application{
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
        ArrayList<Menu> menus=Menu.getlist();
        ArrayList<ObservableList<String>> content= new ArrayList<>(4);
        //0->Contents of Guest Names,1->Contents of Hours,2->Contents of Minutes,3->Content of Menus Names

        //Loading Nodes with content
        for(int i=0;i<4;i++){ content.add(FXCollections.observableArrayList()); }
        for(Guest temp:guests){ content.get(0).add(temp.getName()); }
        for(Menu temp:menus){ content.get(3).add(temp.toString()); }
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
        images.add(new ImageView(new Image("D:\\Abdelrahman Stuff\\College\\Year 2\\Object Oriented Programming\\Project\\media\\take_reservation.jpg")));
        images.add(new ImageView(new Image("D:\\Abdelrahman Stuff\\College\\Year 2\\Object Oriented Programming\\Project\\media\\reservation.jpg")));
        images.add(new ImageView(new Image("D:\\Abdelrahman Stuff\\College\\Year 2\\Object Oriented Programming\\Project\\media\\guest icon.png")));

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
    public void start(Stage mainWindow){
        mainWindow.show();
        recepLogin(mainWindow);
    }
    public static void main(String[] args){
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
        new Guest("Ayman","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        new Guest("Zakaria","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        new Guest("Mahmoud","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        new Receptionist("Nehad","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        launch(args);
    }
}