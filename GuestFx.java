package user;
import employees.Admin;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import employees.Receptionist;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InvalidAttributeValueException;
import system.Category;
import system.Meal;
import system.MenuCategory;
import system.Table;
public class GuestFx extends Application {
    private static Guest currentGuest=new Guest("Mahmoud", "shorouk", "14/10/2003", "01202320112", "mostaa","a","b");
    
    private StackPane stackPane;
    
    public static void main(String[] args) throws InvalidAttributeValueException {
  
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Guest");
        stackPane = new StackPane();
        LoginScene();
        primaryStage.setScene(new Scene(stackPane, 300, 200));
        primaryStage.show();
    }

    private void LoginScene() {
        Label login = new Label("LOGIN");
        login.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        Label username = new Label("Username:");
        Label password = new Label("Password:");
        TextField usernameText = new TextField();
        PasswordField passwordText = new PasswordField();
        Button loginButton = new Button("Login");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(login, 0, 0, 2, 1);
        gridPane.add(username, 0, 1);
        gridPane.add(usernameText, 1, 1);
        gridPane.add(password, 0, 2);
        gridPane.add(passwordText, 1, 2);
        gridPane.add(loginButton, 1, 3);
        loginButton.setOnAction(event -> loginHandle(usernameText.getText(), passwordText.getText()));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(gridPane);
    }

    private void loginHandle(String username, String password) {
        currentGuest = User(username, password);
        if (currentGuest != null) {
            mainScene();
        } else {
   Alert ad = new Alert(
AlertType.ERROR);
ad.setTitle("Try again ");
ad.setHeaderText("Incorrect UserName or Password");
ad.show();
  
        }
    }

    private Guest User(String username, String password) {
        if ("Omar123".equals(username) && "Omar123".equals(password)) {
            return new Guest("Omar", "123 NewYork", "9/10/2004", "01120570370", "Omar@o.com", "Omar123", "Omar123");
        } else {
            return null;
        }
    }

    private void mainScene() {
        Button viewReservationButton = new Button("View Reservations");
        Button rateBookingButton = new Button("Rate Booking");
        rateBookingButton.setOnAction(event -> rateBookingScene());
        viewReservationButton.setOnAction(e->{
            if("[]".equals(currentGuest.ViewReservation())){
                Alert ad = new Alert(
AlertType.INFORMATION);
ad.setTitle("Reservations");
ad.setHeaderText("No Reservation Found");
ad.show();
            }
            else{
            ViewReservationScene();
            }});
         Button Exit= new Button("Exit");
       Exit.setOnAction(e->{
          javafx. application. Platform. exit();
       });
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(viewReservationButton, rateBookingButton,Exit);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(10, 10, 10, 10));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(mainLayout);
    }
private void ViewReservationScene() {
      VBox ViewReservationLayout = new VBox(10);
      Label ReservationLabel = new Label(currentGuest.ViewReservation());
        ViewReservationLayout.getChildren().add(ReservationLabel);
        ViewReservationLayout.setAlignment(Pos.CENTER);
        ViewReservationLayout.setPadding(new Insets(10, 10, 10, 10));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(ViewReservationLayout);
}
private void Back(){
    mainScene();
} 
    private void rateBookingScene() {
        Label ratingLabel = new Label("Enter Rating From 1 to 10:");
        TextField ratingTextField = new TextField();
        Button submitRatingButton = new Button("Submit");
            Button Back= new Button("Back");
       Back.setOnAction(e->{
          Back();
       });
       Button Exit= new Button("Exit");
       Exit.setOnAction(e->{
          javafx. application. Platform. exit();
       });
        submitRatingButton.setOnAction(e->{  if(ratingTextField.getText().isEmpty()){
              Alert ad = new Alert(
AlertType.ERROR);
ad.setTitle("Try agin ");
ad.setHeaderText("Enter a Rating");
ad.show();
        }
        else{
            submitRating(ratingTextField.getText());
                }});
        VBox rateBookingLayout = new VBox(10);
        rateBookingLayout.getChildren().addAll(ratingLabel, ratingTextField, submitRatingButton,Back,Exit);
        rateBookingLayout.setAlignment(Pos.CENTER);
        rateBookingLayout.setPadding(new Insets(10, 10, 10, 10));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(rateBookingLayout);
    }

    private void submitRating(String rating) {
        int Rating= Integer.parseInt(rating);
  if(Rating>=0&&Rating<=10){
 
Alert ad = new Alert(
AlertType.INFORMATION);
ad.setTitle("Thank You");
ad.setHeaderText("Rating Submitted Successfully");
ad.setContentText(rating);
ad.show();
Back();
  }
  else{
Alert ad = new Alert(
AlertType.ERROR);
ad.setTitle("Try agin ");
ad.setHeaderText("Enter a valid Rating");
ad.show();
  }
}

    

//    private void displayErrorMessage(String message) {
//        Label errorLabel = new Label("Error: " + message);
//        VBox errorLayout = new VBox(10);
//        errorLayout.getChildren().addAll(errorLabel);
//        errorLayout.setAlignment(Pos.CENTER);
//        errorLayout.setPadding(new Insets(10, 10, 10, 10));
//        stackPane.getChildren().clear();
//        stackPane.getChildren().add(errorLayout);
//        PauseTransition pause = new PauseTransition(Duration.seconds(3));
//        pause.setOnFinished(event -> LoginScene());
//        pause.play();
//   
// }
}
