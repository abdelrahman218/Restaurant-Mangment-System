package system;
import employees.Receptionist;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;

public class Main extends Application{
    private void recepOptions(Stage mainWindow,Receptionist current){
        //Nodes Declaration
        ArrayList<VBox> columns=new ArrayList<>(3);
        ArrayList<ImageView> images=new ArrayList<>(3);
        ArrayList<Text> titles=new ArrayList<>(3);
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
        HBox layout=new HBox(50);
        for(VBox temp:columns){layout.getChildren().add(temp); temp.setAlignment(Pos.CENTER);}
        layout.setAlignment(Pos.CENTER);
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
        //Event Handling of VBoxes
        columns.get(0).setOnMouseEntered(e->{
                tileTransition.get(0).play();
        });
        columns.get(1).setOnMouseEntered(e->{
            tileTransition.get(1).play();
        });
        columns.get(2).setOnMouseEntered(e->{
            tileTransition.get(2).play();
        });
        //Scene & Main window modification
        Scene optionsScene=new Scene(layout,1000,600);
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
        //Event-handling of buttons
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
        //Layout Building
        ArrayList<HBox> rows=new ArrayList<>(3);
        rows.add(new HBox(header));
        rows.add(new HBox(5,lbUN,tfUN));
        rows.add(new HBox(6,lbPW,pfPW));
        rows.add(new HBox(7,btLogIn,btCancel));
        rows.get(0).setPadding(new Insets(0,0,30,0));
        rows.get(0).setAlignment(Pos.CENTER);
        rows.get(1).setAlignment(Pos.CENTER);
        rows.get(2).setAlignment(Pos.CENTER);
        rows.get(3).setAlignment(Pos.CENTER_RIGHT);
        rows.get(3).setPadding(new Insets(50,0,0,0));
        VBox layout=new VBox(5);
        layout.setPadding(new Insets(10));
        for (HBox row : rows) layout.getChildren().add(row);
        //Scene & Main window modification
        Scene LogInScene=new Scene(layout,500,250);
        mainWindow.setScene(LogInScene);
        mainWindow.setTitle("Log In");
        mainWindow.setResizable(false);
    }
    public void start(Stage mainWindow){
        mainWindow.show();
        recepLogin(mainWindow);
    }
    public static void main(String[] args){
        new Receptionist("Nehad","Zahraa El-maadi","21/08/2004","01211665660","asdfnmje@gmail.com","abc","abcd");
        launch(args);
    }
}