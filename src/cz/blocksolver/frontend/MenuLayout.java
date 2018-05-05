package cz.blocksolver.frontend;

import cz.blocksolver.backend.schema.Schema;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class MenuLayout extends VBox {

    @FXML public MenuItem save;
    @FXML public MenuItem load;
    @FXML public MenuItem run;
    @FXML public MenuItem debug;

    public MenuLayout(MainDisplay display, Stage primaryStage){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/cz/blocksolver/frontend/resources/MenuLayout.fxml")));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }catch (IOException exception){
            throw new RuntimeException(exception);
        }
        System.out.println("NEW DISPLAY " + display);

        load.setOnAction(e -> loadSchema(display, primaryStage));
        save.setOnAction(e -> saveSchema(display, primaryStage));
        run.setOnAction(e -> {
            try {
                display.executeSchema();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (SAXException e1) {
                e1.printStackTrace();
            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            }
        });
        debug.setOnAction(e -> display.debugSchema());
    }

    @FXML
    private void initialize() {}

    public void loadSchema(MainDisplay display, Stage primaryStage){
        System.out.println("NEW DISPLAY " + display);

        for(int i = 0; i < display.getDragBlockList().size(); i++){
            display.getDragBlockList().get(i).setVisible(false);
        }
        for(int i = display.getDragBlockList().size()-1; i >= 0; i--){
            display.getDragBlockList().remove(i);
        }

        display.schema = new Schema(display.schema.getName());
        LoadSchema sch = new LoadSchema();
        try {
            sch.execute(display, display.schema, display.getDragBlockList(), primaryStage);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        for(int i=0; i < display.getDragBlockList().size(); i++){
            display.getDragBlockList().get(i).activateEvents(true);
        }
    }

    public void saveSchema(MainDisplay display, Stage primaryStage){
        SaveSchema sch = new SaveSchema();
        sch.execute(display.schema, display.getDragBlockList(), primaryStage);
    }



}
