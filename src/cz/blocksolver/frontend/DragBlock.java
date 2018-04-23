package cz.blocksolver.frontend;

import cz.blocksolver.backend.block.*;
import cz.blocksolver.backend.block.arithmetic.*;
import cz.blocksolver.backend.block.goniometric.CosinusOperation;
import cz.blocksolver.backend.block.goniometric.CotangensOperation;
import cz.blocksolver.backend.block.goniometric.SinusOperation;
import cz.blocksolver.backend.block.goniometric.TangensOperation;
import cz.blocksolver.backend.block.unary.*;
import cz.blocksolver.backend.port.PortType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;

import static sun.security.krb5.Confounder.intValue;

public class DragBlock extends AnchorPane{

    @FXML public AnchorPane block_pane;
    @FXML public Label block_type;
    @FXML public Circle input_1;
    @FXML public Circle input_2;
    @FXML public Circle output;

    private EventHandler handle_input1 = null;
    private EventHandler handle_input2 = null;
    private EventHandler handle_output = null;

    public Integer XCoord;
    public Integer YCoord;
    public String Index;
    public String Type;
    public String Operation;
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

    public DragBlock(String type){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/cz/blocksolver/frontend/resources/DragBlock.fxml")));
        loader.setRoot(this);
        loader.setController(this);

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

    public void setName(String name){
        block_type.setText(name);
    }

    public String getName(){
        return block_type.getText();
    }

    public void activateEvents() {
        chooseBlock();
        if(Type.equals("arithmetic")){
            handle_input1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(dragBlock.getType());
                    String val = ChangeInputArithmetic.display("first", dragBlock.getInputPort(1).getValue());
                    if(val.equals("canceled")){
                        System.out.println(val);
                    }else{
                        System.out.println("Change " + val);
                        dragBlock.getInputPort(1).setValue(Double.parseDouble(val));
                    }
                }
            };
            handle_input2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String val = ChangeInputArithmetic.display("second", dragBlock.getInputPort(2).getValue());
                    if(val.equals("canceled")){
                        System.out.println(val);
                    }else{
                        System.out.println("Change " + val);
                        dragBlock.getInputPort(2).setValue(Double.parseDouble(val));
                    }
                }
            };
            handle_output = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dragBlock.executeBlock();
                    System.out.println(dragBlock.getOutputPort().getValue());
                }
            };
            input_1.setOnMouseClicked(handle_input1);
            input_2.setOnMouseClicked(handle_input2);
            output.setOnMouseEntered(handle_output);
        }else if(Type.equals("goniometric")){
            input_2.setVisible(false);
            handle_input1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(dragBlock.getType());
                    GoniometricInput val = ChangeInputGoniometric.display("first", dragBlock.getInputPort(1).getValue(), dragBlock.getInputPort(1).getType());
                    if(val.getValue().equals("canceled")){
                        System.out.println(val.getValue());
                    }else{
                        System.out.println("Change " + val.getType());
                        System.out.println("Change " + val.getValue());
                        dragBlock.getInputPort(1).setValue(Double.parseDouble(val.getValue()));
                        dragBlock.getInputPort(1).setType(val.getType());
                    }
                }
            };
            handle_output = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dragBlock.executeBlock();
                    System.out.println(dragBlock.getOutputPort().getValue());
                }
            };
            input_1.setOnMouseClicked(handle_input1);
            output.setOnMouseEntered(handle_output);
        }else if(Type.equals("unary")){
            input_2.setVisible(false);
            handle_input1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(dragBlock.getType());
                    String val = ChangeInputUnary.display("first", dragBlock.getInputPort(1).getValue());
                    if(val.equals("canceled")){
                        System.out.println(val);
                    }else{
                        System.out.println("Change " + val);
                        dragBlock.getInputPort(1).setValue(Double.parseDouble(val));
                    }
                }
            };

            handle_output = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dragBlock.executeBlock();
                    System.out.println(dragBlock.getOutputPort().getValue());
                }
            };
            input_1.setOnMouseClicked(handle_input1);
            output.setOnMouseEntered(handle_output);
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

    public String getIndex() {
        return Index;
    }

    public String getOperation() {
        return Operation;
    }

    public void setIndex(String index) {
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
                dragBlock =  new ArithmeticBlock("Unknown", XCoord, YCoord, 64, 64, AddOperation.getInstance());
            }else if(Operation.equals("sub")){
                dragBlock =  new ArithmeticBlock("Unknown", XCoord, YCoord, 64, 64, SubtractionOperation.getInstance());
            }else if(Operation.equals("mult")){
                dragBlock =  new ArithmeticBlock("Unknown", XCoord, YCoord, 64, 64, MultiplyOperation.getInstance());
            }else if(Operation.equals("div")){
                dragBlock =  new ArithmeticBlock("Unknown", XCoord, YCoord, 64, 64, DivisionOperation.getInstance());
            }else if(Operation.equals("pow")){
                dragBlock =  new ArithmeticBlock("Unknown", XCoord, YCoord, 64, 64, PowOperation.getInstance());
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
        XCoord = dx.intValue();
        YCoord = dy.intValue();
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

