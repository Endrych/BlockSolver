package cz.blocksolver.frontend;

import cz.blocksolver.backend.block.Block;
import cz.blocksolver.backend.block.BlockType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BlockDetail {
    static String newName;

    public static String display(Block block, DragBlock dragBlock){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label nameLabel = new Label();
        TextField nameField = new TextField();

        Label typeLabel = new Label();
        TextField typeField = new TextField();

        Label operationeLabel = new Label();
        TextField operationField = new TextField();

        Label input1Label = new Label();
        TextField input1Field = new TextField();

        Label input2Label = new Label();
        TextField input2Field = new TextField();

        Label outputLabel = new Label();
        TextField outputField = new TextField();


        window.setTitle("Block detail");

        nameLabel.setText("Name");
        nameField.setText(block.getName());
        nameField.setDisable(false);

        typeLabel.setText("Type");
        typeField.setText(block.getType().toString());
        typeField.setDisable(true);

        operationeLabel.setText("Operation");
        operationField.setText(dragBlock.getOperation());
        operationField.setDisable(true);

        input1Label.setText("Input 1");
        if(block.getInputPort(1).getConnectedToOutputPort()){
            if(block.getInputPort(1).getOutputPort().getContainsResult()){
                input1Field.setText(block.getInputPort(1).getOutputPort().getValue().toString());
            }else{
                input1Field.setText("None");
            }
        }else if(block.getInputPort(1).getValueSet()){
            input1Field.setText(block.getInputPort(1).getValue().toString());

        }else{
            input1Field.setText("None");
        }
        input1Field.setDisable(true);

        if(block.getType() == BlockType.ARITHMETIC){
            input2Label.setText("Input 2");

            if(block.getInputPort(2).getConnectedToOutputPort()){
                if(block.getInputPort(2).getOutputPort().getContainsResult()){
                    input2Field.setText(block.getInputPort(2).getOutputPort().getValue().toString());
                }else{
                    input2Field.setText("None");
                }
            }else if(block.getInputPort(2).getValueSet()){
                input2Field.setText(block.getInputPort(2).getValue().toString());

            }else{
                input2Field.setText("None");
            }
            input2Field.setDisable(true);
        }

        outputLabel.setText("Output");
        if(block.getOutputPort().getContainsResult()){
            outputField.setText(block.getOutputPort().getValue().toString());
        }else{
            outputField.setText("None");
        }

        outputField.setDisable(true);

        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        submit.setOnAction(e ->{
            window.close();
            newName = nameField.getText();
        });

        cancel.setOnAction(e -> {
            window.close();
            newName = "";
        });

        HBox hlayout = new HBox(10);
        hlayout.getChildren().addAll(submit,cancel);
        VBox layout = new VBox(10);
        if(block.getType() == BlockType.ARITHMETIC){
            layout.getChildren().addAll(nameLabel,nameField, typeLabel, typeField, operationeLabel, operationField,
                    input1Label, input1Field, input2Label, input2Field, outputLabel, outputField, hlayout);
        }else{
            layout.getChildren().addAll(nameLabel,nameField, typeLabel, typeField, operationeLabel, operationField,
                    input1Label, input1Field, outputLabel, outputField, hlayout);
        }

        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return newName;
    }
}
