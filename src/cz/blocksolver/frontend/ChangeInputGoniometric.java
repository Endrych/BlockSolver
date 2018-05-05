package cz.blocksolver.frontend;

import cz.blocksolver.backend.port.PortType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangeInputGoniometric {
    static String newValue;
    static String newType;

    public static GoniometricInput display(String position, Double current, PortType type){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label();
        if(position.equals("first")){
            window.setTitle("First input");
            label.setText("Enter first input");
        }

        TextField textField = new TextField();
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Degree", "Radian");
        if(type == PortType.DEGREE){
            choiceBox.setValue("Degree");
        }else if(type == PortType.RADIAN){
            choiceBox.setValue("Radian");
        }
        textField.setText(current.toString());
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        GoniometricInput input = new GoniometricInput();

        submit.setOnAction(e ->{
            newValue = textField.getText();
            newType = choiceBox.getValue();
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
        layout.getChildren().addAll(label,choiceBox,textField, hlayout);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        input.setValue(newValue);
        if(newType.equals("Degree")){
            input.setType(PortType.DEGREE);
        }else if(newType.equals("Radian")){
            input.setType(PortType.RADIAN);
        }
        return input;


    }
}
