package cz.blocksolver.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Hlavni trida
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class Main extends Application {

    /**
     * Vytvori zakladni Scenu
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();

        try{

            Scene scene = new Scene(root, 1280, 720);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }

        MainDisplay display = new MainDisplay(primaryStage);

        root.setTop(new MenuLayout(display, primaryStage));
        root.setCenter(display);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
