package sparqlExec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // .getClassLoader() added to avoid issues with maven
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen.fxml"));

        primaryStage.setTitle("Main screen");
        primaryStage.setScene(new Scene(root, 450, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
