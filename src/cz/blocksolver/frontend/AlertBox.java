package cz.blocksolver.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Slouzi k vytvoreni a zobrazeni boxu s nejakym varovanim
 * nebo upozornenim
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class AlertBox {

    /**
     * Slouzi k vytvoreni a zobrazeni boxu
     * @param showText - text, ktery informuje o tom co se stalo
     *                 a co je spatne
     * @param Title - nazev varovani nebo upozorneni
     */
    public static void display(String showText, String Title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label();
        window.setTitle("Error");
        label.setText(Title);
        VBox vbox = new VBox();
        Text text = new Text();
        text.setText(showText);

        Button submit = new Button("Ok");

        submit.setOnAction(e -> {
            window.close();
        });

        vbox.getChildren().addAll(text, submit);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, vbox);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
