package cz.blocksolver.frontend;

import cz.blocksolver.backend.schema.Schema;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Vytvori a zobrazi menu
 */
public class MenuLayout extends VBox {

    @FXML public MenuItem save;
    @FXML public MenuItem load;
    @FXML public MenuItem run;
    @FXML public MenuItem debug;
    @FXML public CheckMenuItem block_value;
    @FXML public MenuItem new_schema;
    @FXML public MenuItem about;

    /**
     * Provede vytvoreni a zobrazeni menu a jeho
     * jednotlivych menu itemu a nastavi jejich Event Handlery
     * @param display
     * @param primaryStage
     */
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

        block_value.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if(block_value.isSelected()){
                    for(int i = 0; i < display.getDragBlockList().size(); i++){
                        display.getDragBlockList().get(i).Handle_showResult(true);
                    }
                }else{
                    for(int i = 0; i < display.getDragBlockList().size(); i++){
                        display.getDragBlockList().get(i).Handle_showResult(false);
                    }
                }
            }
        });

        about.setOnAction(e -> About.display());

        new_schema.setOnAction(e -> display.wipeSchema());
    }

    @FXML
    private void initialize() {}

    /**
     * Stara se o nacteni souboru a pote vyvola funcki, ktera
     * provede nacteni dat ze souboru
     * @param display
     * @param primaryStage
     */
    public void loadSchema(MainDisplay display, Stage primaryStage){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
        File xmlFile = fileChooser.showOpenDialog(primaryStage);
        if(xmlFile != null){
            display.wipeSchema();
            LoadSchema sch = new LoadSchema();
            try {
                sch.execute(display, display.schema, display.getDragBlockList(), xmlFile);
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

    }

    /**
     * Vyvola funkci, ktera se postara o ulozeni schematu do XML souboru
     * @param display
     * @param primaryStage
     */
    public void saveSchema(MainDisplay display, Stage primaryStage){
        SaveSchema sch = new SaveSchema();
        sch.execute(display.schema, display.getDragBlockList(), primaryStage);
    }



}
