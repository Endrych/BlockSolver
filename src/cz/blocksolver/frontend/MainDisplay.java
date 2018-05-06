package cz.blocksolver.frontend;

import com.sun.org.apache.xpath.internal.operations.Bool;
import cz.blocksolver.backend.block.Block;
import cz.blocksolver.backend.block.BlockType;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import cz.blocksolver.backend.port.Port;
import cz.blocksolver.backend.port.PortType;
import cz.blocksolver.backend.schema.Schema;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import jdk.internal.util.xml.impl.Input;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainDisplay extends AnchorPane {


    @FXML SplitPane base_pane;
    @FXML AnchorPane main_display;
    @FXML VBox block_chooser;


    private MainDisplay self = this;
    private DragBlock dragOverBlock = null;
    private EventHandler blockDragOverRoot = null;
    private EventHandler blockDragDropped = null;
    private EventHandler blockDragOverMainDisplay = null;
    private EventHandler connectBlocks = null;
    private ArrayList<DragBlock> dragBlockList = new ArrayList<>();
    public Schema schema = new Schema("Untitled");

    private EventHandler handle_line_hover = null;

    private Boolean outputActive = false;
    private Map<String, Integer> outputCoords = new TreeMap<>();
    public OutputPort outputPort;
    public InputPort inputPort;
    public Integer outputIndex;
    public Integer inputIndex;
    public Integer _index = 1;
    public Boolean showBlockValue = false;
    public DragBlock outputDragBlock;

    public ArrayList<DragBlock> getDragBlockList() {
        return dragBlockList;
    }

    public Boolean getOutputActive() {
        return outputActive;
    }

    public void setOutputActive(Boolean outputActive) {
        this.outputActive = outputActive;
    }

    public Map<String, Integer> getOutputCoords() {
        return outputCoords;
    }

    public void setOutputCoords(Integer x,Integer y) {
        System.out.println(x + " " + y);
        outputCoords.put("x" , x);
        outputCoords.put("y", y);
    }

    private Integer Index = 1;

    public MainDisplay(Stage primaryStage){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/cz/blocksolver/frontend/resources/MainDisplay.fxml")));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
//

        Button btnStartExecuteSchema = new Button("Start");
        btnStartExecuteSchema.setLayoutX(400);
        btnStartExecuteSchema.setLayoutY(0);


        btnStartExecuteSchema.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    executeSchema();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnStartDebugChema = new Button("Debug");
        btnStartDebugChema.setLayoutX(450);
        btnStartDebugChema.setLayoutY(0);

        btnStartDebugChema.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    debugSchema();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        main_display.getChildren().add(btnStartExecuteSchema);
        main_display.getChildren().add(btnStartDebugChema);



        self = this;
        dragOverBlock = new DragBlock();

        dragOverBlock.setVisible(false);
        dragOverBlock.setOpacity(0.65);
        getChildren().add(dragOverBlock);

        Label arithmeticLabel = new Label("Aritmetické bloky");
        arithmeticLabel.setPrefWidth(180);
        arithmeticLabel.setPadding(new Insets(10,0,0,35));
        Label goniometricLabel = new Label("Goniometricke bloky");
        goniometricLabel.setPrefWidth(180);
        goniometricLabel.setPadding(new Insets(10,0,0,25));
        Label unaryLabel = new Label("Unární bloky");
        unaryLabel.setPrefWidth(180);
        unaryLabel.setPadding(new Insets(10,0,0,50));
        HBox twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));
        DragBlock block = new DragBlock("a", self);
        addDragDetection(block);
        block_chooser.getChildren().add(arithmeticLabel);
        block.setName("x+y");
        block.setType("arithmetic");
        block.setOperation("add");
        twoHbox.getChildren().add(block);
        block = new DragBlock("a", self);
        addDragDetection(block);
        block.setName("x-y");
        block.setType("arithmetic");
        block.setOperation("sub");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);
        twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));
        block = new DragBlock("a",  self);
        addDragDetection(block);
        block.setName("x*y");
        block.setType("arithmetic");
        block.setOperation("mult");
        twoHbox.getChildren().add(block);
        block = new DragBlock("a", self);
        addDragDetection(block);
        block.setName("x/y");
        block.setType("arithmetic");
        block.setOperation("div");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);
        twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));
        block = new DragBlock("a", self);
        addDragDetection(block);
        block.setName("x^y");
        block.setType("arithmetic");
        block.setOperation("pow");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);
        twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));
        block = new DragBlock("a",  self);

        block_chooser.getChildren().add(goniometricLabel);
        block = new DragBlock("g", self);
        addDragDetection(block);
        block.setName("sin(x)");
        block.setType("goniometric");
        block.setOperation("sin");
        twoHbox.getChildren().add(block);
        block = new DragBlock("g", self);
        addDragDetection(block);
        block.setName("cos(x)");
        block.setType("goniometric");
        block.setOperation("cos");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);
        twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));
        block = new DragBlock("g",  self);
        addDragDetection(block);
        block.setName("tang(x)");
        block.setType("goniometric");
        block.setOperation("tang");
        twoHbox.getChildren().add(block);
        block = new DragBlock("g", self);
        addDragDetection(block);
        block.setName("cotg(x)");
        block.setType("goniometric");
        block.setOperation("cotg");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);
        twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));

        block_chooser.getChildren().add(unaryLabel);
        block = new DragBlock("u", self);
        addDragDetection(block);
        block.setName("x^2");
        block.setType("unary");
        block.setOperation("pot");
        twoHbox.getChildren().add(block);
        block = new DragBlock("u", self);
        addDragDetection(block);
        block.setName("x^1/2");
        block.setType("unary");
        block.setOperation("sqr");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);
        twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));
        block = new DragBlock("u", self);
        addDragDetection(block);
        block.setName("x^1/3");
        block.setType("unary");
        block.setOperation("cro");
        twoHbox.getChildren().add(block);
        block = new DragBlock("u", self);
        addDragDetection(block);
        block.setName("x--");
        block.setType("unary");
        block.setOperation("dec");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);
        twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));
        block = new DragBlock("u", self);
        addDragDetection(block);
        block.setName("x++");
        block.setType("unary");
        block.setOperation("inc");
        twoHbox.getChildren().add(block);
        block = new DragBlock("u", self);
        addDragDetection(block);
        block.setName("rad(x)");
        block.setType("unary");
        block.setOperation("rad");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);
        twoHbox = new HBox();
        twoHbox.setMinWidth(180.0);
        twoHbox.setSpacing(20);
        twoHbox.setPadding(new Insets(5,0,0,15));
        block = new DragBlock("u", self);
        addDragDetection(block);
        block.setName("deg(x)");
        block.setType("unary");
        block.setOperation("deg");
        twoHbox.getChildren().add(block);
        block_chooser.getChildren().add(twoHbox);


        main_display.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse click detected! " + mouseEvent.getSource());
//                outputActive = false;
            }
        });
        buildDragHandlers();
    }

    public Integer getIndex() {
        return Index;
    }

    public void setIndex(Integer index) {
        Index = index;
    }

    public Line ConnectBlocks(Integer x, Integer y, String type, Integer portNum, OutputPort outPort, ConnectingLine conLine){
        Line line = new Line();

        if(type.equals("a")){
            if(portNum == 1){
                line = new Line(outputCoords.get("x")-178, outputCoords.get("y")-23, x-238, y-34);
            }else{
                line = new Line(outputCoords.get("x")-178, outputCoords.get("y")-23, x-238, y-14);
            }
            main_display.getChildren().add(line);
        }else if(type.equals("u")){
            line = new Line(outputCoords.get("x")-178, outputCoords.get("y")-23, x-240, y-20);
            main_display.getChildren().add(line);
        }else if(type.equals("g")){
            line = new Line(outputCoords.get("x")-178, outputCoords.get("y")-23, x-240, y-20);
            main_display.getChildren().add(line);
        }

        handle_line_hover =  new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!conLine.getShowed()){
                    conLine.setShowed(true);
                    if(outPort.getContainsResult()){
                        //vlakna
                        System.out.println(outPort.getValue());
                        Label tempLabel = new Label();
                        tempLabel.setText(outPort.getValue().toString());
                        tempLabel.setLayoutX(event.getX());
                        tempLabel.setLayoutY(event.getY());
                        main_display.getChildren().add(tempLabel);
                        Timer timer = new java.util.Timer();

                        timer.schedule(new TimerTask() {
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        System.out.println("SDS");
                                        removeLineLabel(tempLabel);
                                        conLine.setShowed(false);
                                    }
                                });
                            }
                        }, 2000);

//                    main_display.getChildren().remove(tempLabel);
                    }else{
                        System.out.println("none");
                        Label tempLabel = new Label();
                        tempLabel.setText("none");
                        tempLabel.setLayoutX(event.getX());
                        tempLabel.setLayoutY(event.getY());
                        main_display.getChildren().add(tempLabel);

                        Timer timer = new java.util.Timer();

                        timer.schedule(new TimerTask() {
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        System.out.println("SDS");
                                        removeLineLabel(tempLabel);
                                        conLine.setShowed(false);
                                    }
                                });
                            }
                        }, 2000);

                    }
                }


            }
        };
        line.setOnMouseEntered(handle_line_hover);

        return line;
    }

    public Line LoadLines(Integer out_x, Integer out_y, Integer in_x, Integer in_y, String type, Integer portNum, OutputPort outPort, ConnectingLine conLine){
        Line line = new Line();
        out_x += 32;
        in_x += 32;
        if(type.equals("a")){
            if(portNum == 1){
                line = new Line(out_x-210, out_y-27, in_x-270, in_y-34);
            }else{
                line = new Line(out_x-210, out_y-27, in_x-270, in_y-14);
            }
            main_display.getChildren().add(line);
        }else if(type.equals("u")){
            line = new Line(out_x-210, out_y-27, in_x-270, in_y-34);
            main_display.getChildren().add(line);
        }else if(type.equals("g")){
            line = new Line(out_x-210, out_y-27, in_x-270, in_y-34);
            main_display.getChildren().add(line);
        }

        handle_line_hover =  new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!conLine.getShowed()){
                    conLine.setShowed(true);
                    if(outPort.getContainsResult()){
                        //vlakna
                        System.out.println(outPort.getValue());
                        Label tempLabel = new Label();
                        tempLabel.setText(outPort.getValue().toString());
                        tempLabel.setLayoutX(event.getX());
                        tempLabel.setLayoutY(event.getY());
                        main_display.getChildren().add(tempLabel);
                        Timer timer = new java.util.Timer();

                        timer.schedule(new TimerTask() {
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        System.out.println("SDS");
                                        removeLineLabel(tempLabel);
                                        conLine.setShowed(false);
                                    }
                                });
                            }
                        }, 2000);

//                    main_display.getChildren().remove(tempLabel);
                    }else{
                        System.out.println("none");
                        Label tempLabel = new Label();
                        tempLabel.setText("none");
                        tempLabel.setLayoutX(event.getX());
                        tempLabel.setLayoutY(event.getY());
                        main_display.getChildren().add(tempLabel);

                        Timer timer = new java.util.Timer();

                        timer.schedule(new TimerTask() {
                            public void run() {
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        System.out.println("SDS");
                                        removeLineLabel(tempLabel);
                                        conLine.setShowed(false);
                                    }
                                });
                            }
                        }, 2000);

                    }
                }
            }
        };
        line.setOnMouseEntered(handle_line_hover);

        return line;
    }

    public void removeLineLabel(Label label){
        main_display.getChildren().remove(label);
    }

    private void buildDragHandlers(){

        blockDragOverRoot = new EventHandler <DragEvent>(){
            @Override
            public void handle(DragEvent event) {
                Point2D point = main_display.sceneToLocal(event.getSceneX(), event.getSceneY());

                if(!main_display.boundsInLocalProperty().get().contains(point)){

                    event.acceptTransferModes(TransferMode.ANY);
                    dragOverBlock.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                    return;
                }

                event.consume();
            }
        };

        blockDragOverMainDisplay =  new EventHandler <DragEvent>(){
            @Override
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
                event.acceptTransferModes(TransferMode.ANY);

                dragOverBlock.relocateToPoint(new Point2D(event.getSceneX(),event.getSceneY()));

                event.consume();
            }
        };

        blockDragDropped =  new EventHandler <DragEvent>(){
            @Override
            public void handle(DragEvent event) {

                DragContainer container = (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

                container.addData("scene_coords", new Point2D(event.getSceneX(),event.getSceneY()));

                ClipboardContent content = new ClipboardContent();
                content.put(DragContainer.AddNode, container);

                event.getDragboard().setContent(content);
                event.setDropCompleted(true);
            }
        };

        this.setOnDragDone( new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {

                main_display.removeEventHandler(DragEvent.DRAG_OVER, blockDragOverMainDisplay);
                main_display.removeEventHandler(DragEvent.DRAG_DROPPED, blockDragDropped);
                base_pane.removeEventHandler(DragEvent.DRAG_OVER, blockDragOverRoot);

                dragOverBlock.setVisible(false);

                DragContainer container = (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

                if(container != null){
                    if(container.getValue("scene_coords") != null){
                        DragBlock droppedBlock;
                        if(container.getValue("type").equals("goniometric")){
                            droppedBlock = new DragBlock("g", self);

                        }else if(container.getValue("type").equals("unary")){
                            droppedBlock = new DragBlock("u", self);

                        }else{
                            droppedBlock = new DragBlock("a" ,self);
                        }
                        droppedBlock.setName(container.getValue("name"));
                        main_display.getChildren().add(droppedBlock);

                        Point2D cursorPoint = container.getValue("scene_coords");

                        droppedBlock.relocateToPoint(new Point2D(cursorPoint.getX()-32,cursorPoint.getY()-32));
                        droppedBlock.setType(container.getValue("type"));
                        droppedBlock.setOperation(container.getValue("operation"));
//                        droppedBlock.setDisplay(self);
                        droppedBlock.setCoordinates(cursorPoint.getX()-32, cursorPoint.getY()-32);
//                        droppedBlock.XCoord
                        droppedBlock.setIndex(Index);
                        droppedBlock.dragBlock.getInputPort(1).setBlockIndex(Index);
                        if(droppedBlock.dragBlock.getType() == BlockType.ARITHMETIC){
                            droppedBlock.dragBlock.getInputPort(2).setBlockIndex(Index);
                        }
                        droppedBlock.dragBlock.getOutputPort().setBlockIndex(Index);
                        Index++;
                        droppedBlock.activateEvents(false);
                        System.out.println (container.getData().toString());
                        schema.addBlock(droppedBlock.dragBlock);
                        dragBlockList.add(droppedBlock);

                    }
                }

                event.consume();
            }
        });
    }

    private void addDragDetection(DragBlock block){
        block.setOnDragDetected( event -> {
            base_pane.setOnDragOver(blockDragOverRoot);
            main_display.setOnDragOver(blockDragOverMainDisplay);
            main_display.setOnDragDropped(blockDragDropped);

            //reference na kliknuty Block objekt
            DragBlock _block = (DragBlock) event.getSource();

            //tady budu moci ponastavovat vlastnosti jako napriklad typ bloku atd.
            //zahajuje se tu operace drag
            dragOverBlock.setName(_block.getName());
            dragOverBlock.setType(_block.getType());
            dragOverBlock.setOperation(_block.getOperation());
            dragOverBlock.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));

            ClipboardContent content = new ClipboardContent();
            DragContainer container = new DragContainer();

            container.addData("name", dragOverBlock.getName());
            container.addData("type", dragOverBlock.getType());
            container.addData("operation", dragOverBlock.getOperation());
            content.put(DragContainer.AddNode, container);

            dragOverBlock.startDragAndDrop(TransferMode.ANY).setContent(content);
            dragOverBlock.setVisible(true);
            dragOverBlock.setMouseTransparent(true);


            event.consume();

        });
    }

    public boolean checkPortsIndex() {
        if(outputIndex == inputIndex){
            return false;
        }else{
            return true;
        }
    }

    private Double getPortValue(InputPort port){
        if(port.getConnectedToOutputPort()){
            return port.getOutputPort().getValue();
        }else if(port.getValueSet()){
            return port.getValue();
        }else{
            return 0.0;
        }
    }

    private Boolean hasSetValue(InputPort port){
        if(port.getConnectedToOutputPort()){
            if(port.getOutputPort().getContainsResult()){
                return true;
            }
            return false;
    }else if(port.getValueSet()){
            return true;
        }else{
            return false;
        }
    }

    public void fillBlocks(){
        for(int i = 0; i < schema.getBlocks().size(); i++) {
            if(!schema.getBlocks().get(i).getInputPort(1).getValueSet() &&
                    !schema.getBlocks().get(i).getInputPort(1).getConnectedToOutputPort()){
                dragBlockList.get(i).higlight();
                dragBlockList.get(i).demandInputValue(1);
                dragBlockList.get(i).removeHiglight();
            }
            if(schema.getBlocks().get(i).getType() == BlockType.ARITHMETIC){
                if(!schema.getBlocks().get(i).getInputPort(2).getValueSet() &&
                        !schema.getBlocks().get(i).getInputPort(2).getConnectedToOutputPort()){
                    dragBlockList.get(i).higlight();
                    dragBlockList.get(i).demandInputValue(2);
                    dragBlockList.get(i).removeHiglight();
                }
            }

        }
    }

    public Boolean checkForCycles(){
        List inputIndex = new ArrayList();
        Boolean hasCycle = false;
        for(int i = 0; i < schema.getBlocks().size(); i++) {
            schema.getBlocks().get(i).setExecuted(false);
            schema.getBlocks().get(i).getOutputPort().setContainsResult(false);
        }
                Boolean executedSome = false;
                do{
                    executedSome = cycleChecker();
                }while(executedSome);


        for(int i = 0; i < schema.getBlocks().size();i++){
            if(!hasSetValue(schema.getBlocks().get(i).getInputPort(1))){
                dragBlockList.get(i).higlight();
                hasCycle = true;
            }
            else if(schema.getBlocks().get(i).getType() == BlockType.ARITHMETIC){
                if(!hasSetValue(schema.getBlocks().get(i).getInputPort(2))){
                    dragBlockList.get(i).higlight();
                    hasCycle = true;
                }else{
                    dragBlockList.get(i).removeHiglight();
                }
            }
            else{
                dragBlockList.get(i).removeHiglight();
            }
        }

        for(int i = 0; i < schema.getBlocks().size(); i++) {
            schema.getBlocks().get(i).setExecuted(false);
            schema.getBlocks().get(i).getOutputPort().setContainsResult(false);
        }
        System.out.println(hasCycle.toString());
        return hasCycle;
    }

    public Boolean checkPortTypes(){
        Boolean found = false;
        for(int i = 0; i < schema.getBlocks().size(); i++) {
            if(schema.getBlocks().get(i).getType() == BlockType.GONIOMETRIC){
                if(schema.getBlocks().get(i).getInputPort(1).getType() == PortType.NUMBER){
                    dragBlockList.get(i).higlight();
                    found = true;
                }else{
                    dragBlockList.get(i).removeHiglight();
                }
            }else{
                if(!dragBlockList.get(i).getOperation().equals("rad") && !dragBlockList.get(i).getOperation().equals("deg"))
                    if(schema.getBlocks().get(i).getInputPort(1).getConnectedToOutputPort()) {
                        if (schema.getBlocks().get(i).getInputPort(1).getOutputPort().getType() != PortType.NUMBER) {
                            dragBlockList.get(i).higlight();
                            found = true;
                        } else {
                            dragBlockList.get(i).removeHiglight();
                        }
                    }
                    if(schema.getBlocks().get(i).getType() == BlockType.ARITHMETIC){
                        if(schema.getBlocks().get(i).getInputPort(2).getConnectedToOutputPort()) {
                            if (schema.getBlocks().get(i).getInputPort(2).getOutputPort().getType() != PortType.NUMBER) {
                                dragBlockList.get(i).higlight();
                                found = true;
                            } else {
                                dragBlockList.get(i).removeHiglight();
                            }
                        }
                    }
            }
        }

        return found;
    }

    public void executeSchema() throws InterruptedException, IOException, SAXException, ParserConfigurationException {

        fillBlocks();
        if(!checkPortTypes()){
            if(!checkForCycles()){
                for(int i = 0; i < schema.getBlocks().size(); i++) {
                    schema.getBlocks().get(i).setExecuted(false);
                    schema.getBlocks().get(i).getOutputPort().setContainsResult(false);
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Boolean executedSome = false;
                        _index = 1;

                        do{
                            executedSome = executeBlock(executedSome);
                        }while(executedSome);
                    }
                }).start();
            }else{
                AlertBox.display("Ve vašem schématu se vyskytuje cyklus,\n" +
                        "prosím proveďte jeho odstranění.\n\n" +
                        "Bloky které tvoří cyklus jsou \n" +
                        "zvýrazněny červeně\n", "Nalezen cyklus");
            }
        }else{
            AlertBox.display("Porušena kompatibilita vstupního,\n" +
                    "a výstupního portu propoje .\n\n" +
                    "Blok/y které jsou postiženy jsou \n" +
                    "zvýrazněny červeně\n", "Porušena kompatibilita");
        }

    }

    public void debugSchema(){

        fillBlocks();

        if(!checkPortTypes()) {
            if (!checkForCycles()) {
                for (int i = 0; i < schema.getBlocks().size(); i++) {
                    schema.getBlocks().get(i).setExecuted(false);
                    schema.getBlocks().get(i).getOutputPort().setContainsResult(false);
                }

                Boolean executedSome = false;
                _index = 1;
                Button btnDebugStep = new Button("Step");
                Button btnDebugFinish = new Button("Finish");
                Button btsDebugStop = new Button("Stop");


                btnDebugStep.setLayoutX(550);
                btnDebugStep.setLayoutY(0);

                btnDebugFinish.setLayoutX(600);
                btnDebugFinish.setLayoutY(0);

                btsDebugStop.setLayoutX(658);
                btsDebugStop.setLayoutY(0);

                btnDebugStep.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Boolean exec = executeBlock(true);
                            if(!exec){
                                main_display.getChildren().remove(btnDebugStep);
                                main_display.getChildren().remove(btnDebugFinish);
                                main_display.getChildren().remove(btsDebugStop);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                btnDebugFinish.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Boolean exec = true;
                            do{
                                exec = executeBlock(exec);
                            }while(exec);

                                main_display.getChildren().remove(btnDebugStep);
                                main_display.getChildren().remove(btnDebugFinish);
                                main_display.getChildren().remove(btsDebugStop);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                btsDebugStop.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            for(int i = 0; i < dragBlockList.size(); i++){
                                if(dragBlockList.get(i).getHiglighted()){
                                    dragBlockList.get(i).removeHiglight();
                                }
                            }

                            main_display.getChildren().remove(btnDebugStep);
                            main_display.getChildren().remove(btnDebugFinish);
                            main_display.getChildren().remove(btsDebugStop);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                main_display.getChildren().add(btnDebugStep);
                main_display.getChildren().add(btnDebugFinish);
                main_display.getChildren().add(btsDebugStop);

            }else{
                AlertBox.display("Ve vašem schématu se vyskytuje cyklus,\n" +
                        "prosím proveďte jeho odstranění.\n\n" +
                        "Bloky které tvoří cyklus jsou \n" +
                        "zvýrazněny červeně\n", "Nalezen cyklus");
            }
        }else{
            AlertBox.display("Porušena kompatibilita vstupního,\n" +
                    "a výstupního portu propoje .\n\n" +
                    "Blok/y které jsou postiženy jsou \n" +
                    "zvýrazněny červeně\n", "Porušena kompatibilita");
        }
    }

    public Boolean executeBlock(Boolean executedSome){
        for(int i = 0; i < schema.getBlocks().size(); i++){
            if(executedSome){
                if(schema.getBlocks().size() == 1){
                    dragBlockList.get(0).removeHiglight();
                }else{
                    dragBlockList.get(_index).removeHiglight();
                }
            }
            executedSome = false;
            System.out.println(schema.getBlocks().get(i));
            if(schema.getBlocks().get(i).getType() == BlockType.ARITHMETIC){
                System.out.println("Je aritmeticky");
                if(hasSetValue(schema.getBlocks().get(i).getInputPort(1)) && hasSetValue(schema.getBlocks().get(i).getInputPort(2))){
                    System.out.println("Hodnoty nastaveny");
                    if(!schema.getBlocks().get(i).getExecuted()){
                        if(schema.getBlocks().get(i).executeBlock(getPortValue(schema.getBlocks().get(i).getInputPort(1)), getPortValue(schema.getBlocks().get(i).getInputPort(2)))){
                            System.out.println("Value in executed block " + schema.getBlocks().get(i).getOutputPort().getValue());
                            executedSome = true;
                            schema.getBlocks().get(i).setExecuted(true);
                            schema.getBlocks().get(i).getOutputPort().setContainsResult(true);
                            new Thread(new Runnable() {
                                private int myParam;
                                public Runnable init(int myParam) {
                                    this.myParam = myParam;
                                    return this;
                                }
                                @Override
                                public void run() {
                                    dragBlockList.get(myParam).higlight();
                                }
                            }.init(i)).start();

                            _index = i;
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                    }
                }
            }else if(schema.getBlocks().get(i).getType() == BlockType.UNARY){
                System.out.println("Je Unarni");
                if(hasSetValue(schema.getBlocks().get(i).getInputPort(1))){
                    System.out.println("Hodnoty nastaveny");
                    if(!schema.getBlocks().get(i).getExecuted()){
                        if(schema.getBlocks().get(i).executeBlock(getPortValue(schema.getBlocks().get(i).getInputPort(1)))){
                            System.out.println("Value in executed block " + schema.getBlocks().get(i).getOutputPort().getValue());
                            executedSome = true;
                            schema.getBlocks().get(i).setExecuted(true);
                            schema.getBlocks().get(i).getOutputPort().setContainsResult(true);
                            new Thread(new Runnable() {
                                private int myParam;
                                public Runnable init(int myParam) {
                                    this.myParam = myParam;
                                    return this;
                                }
                                @Override
                                public void run() {
                                    dragBlockList.get(myParam).higlight();
                                }
                            }.init(i)).start();

                            _index = i;
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                    }
                }
            }else if(schema.getBlocks().get(i).getType() == BlockType.GONIOMETRIC){
                System.out.println("Je Goniometricky");
                if(hasSetValue(schema.getBlocks().get(i).getInputPort(1))){
                    System.out.println("Hodnoty nastaveny");
                    if(!schema.getBlocks().get(i).getExecuted()){
                        if(schema.getBlocks().get(i).executeBlock(getPortValue(schema.getBlocks().get(i).getInputPort(1)))){
                            System.out.println("Value in executed block " + schema.getBlocks().get(i).getOutputPort().getValue());
                            executedSome = true;
                            schema.getBlocks().get(i).setExecuted(true);
                            schema.getBlocks().get(i).getOutputPort().setContainsResult(true);
                            new Thread(new Runnable() {
                                private int myParam;
                                public Runnable init(int myParam) {
                                    this.myParam = myParam;
                                    return this;
                                }
                                @Override
                                public void run() {
                                    dragBlockList.get(myParam).higlight();
                                }
                            }.init(i)).start();

                            _index = i;
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                    }
                }

            }

        }
        return false;
    }

    public Boolean cycleChecker(){
        for(int i = 0; i < schema.getBlocks().size(); i++){
            System.out.println(schema.getBlocks().get(i));
            if(schema.getBlocks().get(i).getType() == BlockType.ARITHMETIC){
                if(hasSetValue(schema.getBlocks().get(i).getInputPort(1)) && hasSetValue(schema.getBlocks().get(i).getInputPort(2))){
                    if(!schema.getBlocks().get(i).getExecuted()){
                        if(schema.getBlocks().get(i).executeBlock(getPortValue(schema.getBlocks().get(i).getInputPort(1)), getPortValue(schema.getBlocks().get(i).getInputPort(2)))){
                            schema.getBlocks().get(i).setExecuted(true);
                            schema.getBlocks().get(i).getOutputPort().setContainsResult(true);
                            schema.getBlocks().get(i).getOutputPort().setValue(0.0);
                            return true;
                        }
                    }
                }
            }else if(schema.getBlocks().get(i).getType() == BlockType.UNARY){
                if(hasSetValue(schema.getBlocks().get(i).getInputPort(1))){
                    if(!schema.getBlocks().get(i).getExecuted()){
                        if(schema.getBlocks().get(i).executeBlock(getPortValue(schema.getBlocks().get(i).getInputPort(1)))){
                            schema.getBlocks().get(i).setExecuted(true);
                            schema.getBlocks().get(i).getOutputPort().setContainsResult(true);
                            schema.getBlocks().get(i).getOutputPort().setValue(0.0);
                            return true;
                        }
                    }
                }
            }else if(schema.getBlocks().get(i).getType() == BlockType.GONIOMETRIC){
                System.out.println("Je Goniometricky");
                if(hasSetValue(schema.getBlocks().get(i).getInputPort(1))){
                    System.out.println("Hodnoty nastaveny");
                    if(!schema.getBlocks().get(i).getExecuted()){
                        if(schema.getBlocks().get(i).executeBlock(getPortValue(schema.getBlocks().get(i).getInputPort(1)))){
                            System.out.println("Value in executed block " + schema.getBlocks().get(i).getOutputPort().getValue());
                            schema.getBlocks().get(i).setExecuted(true);
                            schema.getBlocks().get(i).getOutputPort().setContainsResult(true);
                            schema.getBlocks().get(i).getOutputPort().setValue(0.0);
                            return true;
                        }
                    }
                }

            }

        }
        return false;
    }

    public void wipeSchema(){
        for(int i = 0; i < dragBlockList.size(); i++){
            dragBlockList.get(i).setVisible(false);
            if(dragBlockList.get(i).getInputPortLine1().getLine() != null){
                dragBlockList.get(i).getInputPortLine1().getLine().setVisible(false);
            }
            if(dragBlockList.get(i).getInputPortLine2().getLine() != null){
                dragBlockList.get(i).getInputPortLine2().getLine().setVisible(false);
            }
        }

        dragBlockList = new ArrayList<>();
        System.out.println("DELKA " + dragBlockList.size() + " " + schema.getBlocks().size() );

        schema = new Schema("Unnamed");
        System.out.println("DELKA " + dragBlockList.size() + " " + schema.getBlocks().size() );

        Index = 1;

    }

}
