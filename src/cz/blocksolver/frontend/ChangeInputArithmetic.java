package cz.blocksolver.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Umoznuje rucni zadavani hodnot do jednotlivych Aritmetickych bloku
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class ChangeInputArithmetic {

    static String newValue;

    /**
     * Slouzi k rucnimu zadani hodnoty vstupniho portu
     * @param position - znaci zda se jedna o prvni nebo druhy port
     * @param current - momentalni hodnota ulozena ve vstupnim portu
     * @return - nova hodnota vstupniho portu nebo retezec "canceled"
     * v pripade ze nedoslo, k upraveni hodnoty
     */
    public static String display(String position, Double current){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label();
        if(position.equals("first")){
            window.setTitle("First input");
            label.setText("Enter first input");
        }else if(position.equals("second")){
            window.setTitle("Second input");
            label.setText("Enter second input");
        }

        TextField textField = new TextField();
        textField.setText(current.toString());
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        submit.setOnAction(e ->{
            newValue = textField.getText();
            try
            {
                Double.parseDouble(newValue);
                window.close();
            }
            catch(NumberFormatException exception)
            {
                textField.setStyle("-fx-border-color: red");
            }
        });

        cancel.setOnAction(e -> {
            newValue = "canceled";
            window.close();
        });

        HBox hlayout = new HBox(10);
        hlayout.getChildren().addAll(submit,cancel);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,textField, hlayout);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return newValue;


    }

}
