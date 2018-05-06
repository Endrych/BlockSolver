package cz.blocksolver.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class About {
    public static void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label();
        window.setTitle("About");
        label.setText("About");
        VBox vbox = new VBox();
        Text text = new Text();
        text.setText("Aplikace umožňuje tvorbu blokových schémat pro \nzákladní " +
                "matematické operace\n\n" +
                "Jsou podporovány tři typy bloků a to Aritmetické, \n" +
                "Goniometrické a Unární\n\n" +
                "Při použití Goniometrických bloků je potřeba \n" +
                "zajistit, aby se na vstupu vyskytovaly buď stupně\n" +
                "nebo radiány a to pomocí konvertorů rad(x), který\n" +
                "provede převod na radiány a deg(x), který slouží\n" +
                "pro převod na stupně\n\n\n" +
                "Autoři: Marek Kukučka, David Endrych\n");


        Button submit = new Button("Ok");

        submit.setOnAction(e ->{
            window.close();
        });

        vbox.getChildren().addAll(text, submit);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,vbox);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
