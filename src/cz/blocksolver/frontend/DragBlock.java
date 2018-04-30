package cz.blocksolver.frontend;

import cz.blocksolver.backend.block.*;
import cz.blocksolver.backend.block.arithmetic.*;
import cz.blocksolver.backend.block.goniometric.CosinusOperation;
import cz.blocksolver.backend.block.goniometric.CotangensOperation;
import cz.blocksolver.backend.block.goniometric.SinusOperation;
import cz.blocksolver.backend.block.goniometric.TangensOperation;
import cz.blocksolver.backend.block.unary.*;
import cz.blocksolver.backend.port.PortType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

//import static cz.blocksolver.frontend.MainDisplay.ConnectBlocks;
//import static cz.blocksolver.frontend.MainDisplay.outputActive;
//import static cz.blocksolver.frontend.MainDisplay.outputCoords;


public class DragBlock extends AnchorPane{

    @FXML public AnchorPane block_pane;
    @FXML public Label block_type;
    @FXML public Circle input_1;
    @FXML public Circle input_2;
    @FXML public Circle output;

    private EventHandler handle_input1 = null;
    private EventHandler handle_input2 = null;
    private EventHandler handle_output = null;
    private EventHandler handle_showResult = null;
    private EventHandler handle_righClick = null;

    public MainDisplay display;
    public Integer XCoord;
    public Integer YCoord;
    public Integer Index;
    public String Type;
    public String Operation;
    public Line inputPortLine1;
    public Line inputPortLine2;
//    public Boolean eventsActive = false;
    public Block dragBlock =  new ArithmeticBlock("Unknown", 0, 0, 64, 64, AddOperation.getInstance());

    public DragBlock(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/cz/blocksolver/frontend/resources/DragBlock.fxml")));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        }catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }

    public DragBlock(MainDisplay display){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/cz/blocksolver/frontend/resources/DragBlock.fxml")));
        loader.setRoot(this);
        loader.setController(this);
        this.display = display;
        System.out.println("display " + this.display);
        try {
            loader.load();
        }catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }

    public DragBlock(String type, MainDisplay display){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/cz/blocksolver/frontend/resources/DragBlock.fxml")));
        loader.setRoot(this);
        loader.setController(this);
        this.display = display;
        System.out.println("display " + this.display);
        try {
            loader.load();
        }catch (IOException exception){
            throw new RuntimeException(exception);
        }

        if(type.equals("g")){
            input_2.setVisible(false);
        }else if(type.equals("u")){
            input_2.setVisible(false);
        }

    }

    public MainDisplay getDisplay() {
        return display;
    }

    public void setDisplay(MainDisplay display) {
        this.display = display;
    }

    public void setName(String name){
        block_type.setText(name);
    }

    public String getName(){
        return block_type.getText();
    }

    public void activateEvents() {
        chooseBlock();
        System.out.println(display.schema.getBlocks());
        System.out.println("RUN");
        if(Type.equals("arithmetic")){
            handle_input1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(display.getOutputActive()){
                        display.setOutputActive(false);
                        display.inputPort = dragBlock.getInputPort(1);
                        display.inputIndex = Index;
                        if(display.checkPortsIndex()){
                            if(inputPortLine1 != null){
                                inputPortLine1.setVisible(false);
                            }
                            inputPortLine1 = display.ConnectBlocks(XCoord, YCoord, "a" ,1);
                            dragBlock.getInputPort(1).setOutputPort(display.outputPort);
                            dragBlock.getInputPort(1).setConnectedToOutputPort(true);
                        }

                    }else{
                        System.out.println(dragBlock.getType());
                        String val;
                        if(dragBlock.getInputPort(1).getOutputPort() != null){
                            val = ChangeInputArithmetic.display("first", dragBlock.getInputPort(1).getOutputPort().getValue());
                            System.out.println(dragBlock.getInputPort(1).getOutputPort());
                        }else{
                            val = ChangeInputArithmetic.display("first", dragBlock.getInputPort(1).getValue());
                        }
                        if(val.equals("canceled")){
                            System.out.println(val);
                        }else{
                            System.out.println("Change " + val);
                            dragBlock.getInputPort(1).setValueSet(true);
                            dragBlock.getInputPort(1).setValue(Double.parseDouble(val));
                        }
                    }
                }
            };
            handle_input2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(display.getOutputActive()){
                        display.setOutputActive(false);
                        display.inputPort = dragBlock.getInputPort(1);
                        display.inputIndex = Index;
                        if(display.checkPortsIndex()){
                            if(inputPortLine2 != null){
                                inputPortLine2.setVisible(false);
                            }
                            inputPortLine2 = display.ConnectBlocks(XCoord, YCoord, "a", 2);
                            dragBlock.getInputPort(2).setOutputPort(display.outputPort);
                            dragBlock.getInputPort(2).setConnectedToOutputPort(true);
                        }

                    }else{
                        System.out.println(dragBlock.getType());
                        String val;
                        if(dragBlock.getInputPort(2).getOutputPort() != null){
                            val = ChangeInputArithmetic.display("first", dragBlock.getInputPort(2).getOutputPort().getValue());
                            System.out.println(dragBlock.getInputPort(2).getOutputPort());
                        }else{
                            val = ChangeInputArithmetic.display("first", dragBlock.getInputPort(2).getValue());
                        }
                        if(val.equals("canceled")){
                            System.out.println(val);
                        }else{
                            System.out.println("Change " + val);
                            dragBlock.getInputPort(2).setValueSet(true);
                            dragBlock.getInputPort(2).setValue(Double.parseDouble(val));
                        }
                    }
                }
            };
            handle_output = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(display);
                    display.outputIndex = Index;
//                    Line line = new Line(200, 200, 10, 110);
//                    display.main_display.getChildren().add(line);//                    display.setOutputActive(true);
                    display.setOutputCoords(XCoord,  YCoord);
                    System.out.println(XCoord + " : " + YCoord);
                    display.setOutputActive(true);
                    display.outputPort = dragBlock.getOutputPort();
                    //                    System.out.println(event.getSceneX());
//                    display.outputCoords.put("x", event.getSceneX());
//                    display.outputCoords.put("y", event.getSceneY());
//                    dragBlock.executeBlock();
//                    System.out.println(dragBlock.getOutputPort().getValue());
                }
            };
            handle_showResult = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(dragBlock.getExecuted()){
                        System.out.println(dragBlock.getOutputPort().getValue());
                    }else{
                        System.out.println("Block not executed");
                    }
                }
            };

            handle_righClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MouseButton button = event.getButton();
                    if(button==MouseButton.SECONDARY){

                    }
                }
            };


            input_1.setOnMouseClicked(handle_input1);
            input_2.setOnMouseClicked(handle_input2);
            output.setOnMouseClicked(handle_output);
            block_pane.setOnMouseEntered(handle_showResult);
            block_pane.setOnMouseClicked(handle_righClick);
        }else if(Type.equals("goniometric")){
            input_2.setVisible(false);
            handle_input1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(display.getOutputActive()){
                        display.setOutputActive(false);
                        display.inputPort = dragBlock.getInputPort(1);
                        display.inputIndex = Index;
                        dragBlock.getInputPort(1).setType(PortType.DEGREE);
                        if(display.checkPortsIndex()){
                            if(inputPortLine1 != null){
                                inputPortLine1.setVisible(false);
                            }
                            inputPortLine1 = display.ConnectBlocks(XCoord, YCoord, "g" ,1);
                            dragBlock.getInputPort(1).setOutputPort(display.outputPort);
                            dragBlock.getInputPort(1).setConnectedToOutputPort(true);
                        }
                    }else{
                        System.out.println(dragBlock.getType());
                        GoniometricInput val;
                        if(dragBlock.getInputPort(1).getOutputPort() != null){
                            val = ChangeInputGoniometric.display("first", dragBlock.getInputPort(1).getOutputPort().getValue(),dragBlock.getInputPort(1).getType());
                            System.out.println(dragBlock.getInputPort(1).getOutputPort());
                        }else{
                            val = ChangeInputGoniometric.display("first", dragBlock.getInputPort(1).getValue(), dragBlock.getInputPort(1).getType());
                        }

                        if(val.getValue().equals("canceled")){
                            System.out.println(val.getValue());
                        }else{
                            System.out.println("Change " + val.getType());
                            System.out.println("Change " + val.getValue());
                            if(Double.parseDouble(val.getValue()) == dragBlock.getInputPort(1).getValue()){
                                dragBlock.getInputPort(1).setType(val.getType());
                            }else{
                                dragBlock.getInputPort(1).setValue(Double.parseDouble(val.getValue()));
                                dragBlock.getInputPort(1).setType(val.getType());
                                dragBlock.getInputPort(1).setValueSet(true);

                                //TODO smazat linku a zbavit se ji
                            }
                        }
                    }
                }
            };
            handle_output = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    display.outputIndex = Index;
                    display.setOutputCoords(XCoord,  YCoord);
                    display.setOutputActive(true);
                    display.outputPort = dragBlock.getOutputPort();
                }
            };
            handle_showResult = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(dragBlock.getExecuted()){
                        System.out.println(dragBlock.getOutputPort().getValue());
                    }else{
                        System.out.println("Block not executed");
                    }
                }
            };
            input_1.setOnMouseClicked(handle_input1);
            output.setOnMouseClicked(handle_output);
            block_pane.setOnMouseEntered(handle_showResult);
        }else if(Type.equals("unary")){
            input_2.setVisible(false);
            handle_input1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(display.getOutputActive()){
                        display.setOutputActive(false);
                        display.inputPort = dragBlock.getInputPort(1);
                        display.inputIndex = Index;
                        if(display.checkPortsIndex()){
                            if(inputPortLine1 != null){
                                inputPortLine1.setVisible(false);
                            }
                            inputPortLine1 = display.ConnectBlocks(XCoord, YCoord, "u" ,1);
                            dragBlock.getInputPort(1).setOutputPort(display.outputPort);
                            dragBlock.getInputPort(1).setConnectedToOutputPort(true);
                        }
                    }else{
                        System.out.println(dragBlock.getType());
                        String val;
                        if(dragBlock.getInputPort(1).getOutputPort() != null){
                            val = ChangeInputUnary.display("first", dragBlock.getInputPort(1).getOutputPort().getValue());
                            System.out.println(dragBlock.getInputPort(1).getOutputPort());
                        }else{
                            val = ChangeInputUnary.display("first", dragBlock.getInputPort(1).getValue());
                        }
                        if(val.equals("canceled")){
                            System.out.println(val);
                        }else{
                            System.out.println("Change " + val);
                            dragBlock.getInputPort(1).setValueSet(true);
                            dragBlock.getInputPort(1).setValue(Double.parseDouble(val));
                        }
                    }
                }
            };

            handle_output = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    display.outputIndex = Index;
                    display.setOutputCoords(XCoord,  YCoord);
                    display.setOutputActive(true);
                    display.outputPort = dragBlock.getOutputPort();
                }
            };
            handle_showResult = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(dragBlock.getExecuted()){
                        System.out.println(dragBlock.getOutputPort().getValue());
                    }else{
                        System.out.println("Block not executed");
                    }
                }
            };
            input_1.setOnMouseClicked(handle_input1);
            output.setOnMouseClicked(handle_output);
            block_pane.setOnMouseEntered(handle_showResult);
        }

    }


    @FXML
    private void initialize() {}

    public void relocateToPoint(Point2D point2D) {
        Point2D localCoordinates = getParent().sceneToLocal(point2D);

        relocate(
                (int) (localCoordinates.getX() - (getBoundsInLocal().getWidth()/2)),
                (int) (localCoordinates.getY() - (getBoundsInLocal().getHeight()/2))
        );
    }

    public String getType() {
        return Type;
    }

    public Integer getIndex() {
        return Index;
    }

    public String getOperation() {
        return Operation;
    }

    public void setIndex(Integer index) {
        Index = index;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setOperation(String operation) {
        Operation = operation;
    }

    public void chooseBlock(){
        if (Type.equals("arithmetic")){
            if(Operation.equals("add")){
                dragBlock =  new ArithmeticBlock("Arr", XCoord, YCoord, 64, 64, AddOperation.getInstance());
            }else if(Operation.equals("sub")){
                dragBlock =  new ArithmeticBlock("Arr", XCoord, YCoord, 64, 64, SubtractionOperation.getInstance());
            }else if(Operation.equals("mult")){
                dragBlock =  new ArithmeticBlock("Arr", XCoord, YCoord, 64, 64, MultiplyOperation.getInstance());
            }else if(Operation.equals("div")){
                dragBlock =  new ArithmeticBlock("Arr", XCoord, YCoord, 64, 64, DivisionOperation.getInstance());
            }else if(Operation.equals("pow")){
                dragBlock =  new ArithmeticBlock("Arr", XCoord, YCoord, 64, 64, PowOperation.getInstance());
            }
        }else if(Type.equals("goniometric")){
            if(Operation.equals("sin")){
                dragBlock = new GoniometricBlock("Gon", XCoord, YCoord, 64, 64, SinusOperation.getInstance());
                dragBlock.getInputPort(1).setType(PortType.DEGREE);
            }else if(Operation.equals("cos")){
                dragBlock = new GoniometricBlock("Gon", XCoord, YCoord, 64, 64, CosinusOperation.getInstance());
                dragBlock.getInputPort(1).setType(PortType.DEGREE);
            }else if(Operation.equals("tang")){
                dragBlock = new GoniometricBlock("Gon", XCoord, YCoord, 64, 64, TangensOperation.getInstance());
                dragBlock.getInputPort(1).setType(PortType.DEGREE);
            }else if(Operation.equals("cotg")){
                dragBlock = new GoniometricBlock("Gon", XCoord, YCoord, 64, 64, CotangensOperation.getInstance());
                dragBlock.getInputPort(1).setType(PortType.DEGREE);
            }
        }else if(Type.equals("unary")){
            if(Operation.equals("cro")){
                dragBlock = new UnaryBlock("Unary", XCoord, YCoord, 64, 64, CubeRootOperation.getInstance());
            }else if(Operation.equals("pot")){
                dragBlock = new UnaryBlock("Unary", XCoord, YCoord, 64, 64, SquareOperation.getInstance());
            }else if(Operation.equals("sqr")){
                dragBlock = new UnaryBlock("Unary", XCoord, YCoord, 64, 64, SquareRootOperation.getInstance());
            }else if(Operation.equals("dec")){
                dragBlock = new UnaryBlock("Unary", XCoord, YCoord, 64, 64, DecrementOperation.getInstance());
            }else if(Operation.equals("inc")){
                dragBlock = new UnaryBlock("Unary", XCoord, YCoord, 64, 64, IncrementOperation.getInstance());
            }
        }
    }

    public void displayBlock(){
        if(Type.equals("goniometric")){
            input_2.setVisible(false);
        }else if(Type.equals("unary")){
            input_2.setVisible(false);
        }
    }

    public void setCoordinates(double x, double y) {
        Double dx = new Double(x);
        Double dy = new Double(y);
        System.out.println(dx + " / " + dy);
        XCoord = dx.intValue();
        YCoord = dy.intValue();
        System.out.println(XCoord + " / " + YCoord);
    }

    public void higlight() {

        block_pane.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 2;" + "-fx-background-color: yellow;"
                + "-fx-border-radius: 5;" + "-fx-border-color: red;");
    }

    public void removeHiglight() {
        block_pane.setStyle("-fx-background-color: yellow;");
    }
    //    public void setType(String type){
//        System.out.println("HERE");
//        if(type.equals("arithmetic")){
//            System.out.println("HERE2");
//            ABlock = new ArithmeticBlock("Unknown", 0, 0, 64, 64, SubtractionOperation.getInstance());
//            System.out.println(ABlock.getType());
//        }
//
//    }
//
//    public void setOperation(String operation) {
//        if(operation.equals("add")){
//
//        }
//    }
}

