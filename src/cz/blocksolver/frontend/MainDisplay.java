package cz.blocksolver.frontend;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainDisplay extends AnchorPane {

    @FXML SplitPane base_pane;
    @FXML AnchorPane main_display;
    @FXML VBox block_chooser;

    private DragBlock dragOverBlock = null;
    private EventHandler blockDragOverRoot = null;
    private EventHandler blockDragDropped = null;
    private EventHandler blockDragOverMainDisplay = null;

    private int Index = 1;

    public MainDisplay(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/cz/blocksolver/frontend/resources/MainDisplay.fxml")));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
        dragOverBlock = new DragBlock();
//
        dragOverBlock.setVisible(false);
        dragOverBlock.setOpacity(0.65);
        getChildren().add(dragOverBlock);

        Label arithmeticLabel = new Label("Aritmeticke bloky");
        Label goniometricLabel = new Label("Goniometricke bloky");
        Label unaryLabel = new Label("Unarni bloky");

        DragBlock block = new DragBlock();
        addDragDetection(block);

        block_chooser.getChildren().add(arithmeticLabel);
        block.setName("Scitani");
        block.setType("arithmetic");
        block.setOperation("add");
        block_chooser.getChildren().add(block);
        block = new DragBlock("a");
        addDragDetection(block);
        block.setName("Odcitani");
        block.setType("arithmetic");
        block.setOperation("sub");
        block_chooser.getChildren().add(block);
        block = new DragBlock("a");
        addDragDetection(block);
        block.setName("Nasobeni");
        block.setType("arithmetic");
        block.setOperation("mult");
        block_chooser.getChildren().add(block);
        block = new DragBlock("a");
        addDragDetection(block);
        block.setName("Deleni");
        block.setType("arithmetic");
        block.setOperation("div");
        block_chooser.getChildren().add(block);
        block = new DragBlock("a");
        addDragDetection(block);
        block.setName("Umocneni");
        block.setType("arithmetic");
        block.setOperation("pow");
        block_chooser.getChildren().add(block);
        block_chooser.getChildren().add(goniometricLabel);
        block = new DragBlock("g");
        addDragDetection(block);
        block.setName("Sinus");
        block.setType("goniometric");
        block.setOperation("sin");
        block_chooser.getChildren().add(block);
        block = new DragBlock("g");
        addDragDetection(block);
        block.setName("Cosinus");
        block.setType("goniometric");
        block.setOperation("cos");
        block_chooser.getChildren().add(block);
        block = new DragBlock("g");
        addDragDetection(block);
        block.setName("Tangens");
        block.setType("goniometric");
        block.setOperation("tang");
        block_chooser.getChildren().add(block);
        block = new DragBlock("g");
        addDragDetection(block);
        block.setName("Cotangens");
        block.setType("goniometric");
        block.setOperation("cotg");
        block_chooser.getChildren().add(block);

//        for (int i = 0; i < 7; i++) {
//
//            DragBlock block = new DragBlock();
//
//            addDragDetection(block);
//
//            block.setName("Block " + i);
//            block_chooser.getChildren().add(block);
//        }

        buildDragHandlers();
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

//                main_display.removeEventHandler(DragEvent.DRAG_OVER, blockDragOverMainDisplay);
//                main_display.removeEventHandler(DragEvent.DRAG_DROPPED, blockDragDropped);
//                base_pane.removeEventHandler(DragEvent.DRAG_OVER, blockDragOverRoot);
//
//                dragOverBlock.setVisible(false);
//
//                event.consume();
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
                        DragBlock droppedBlock = new DragBlock();

                        droppedBlock.setName(container.getValue("name"));
                        main_display.getChildren().add(droppedBlock);

                        Point2D cursorPoint = container.getValue("scene_coords");

                        droppedBlock.relocateToPoint(new Point2D(cursorPoint.getX()-32,cursorPoint.getY()-32));
                        droppedBlock.setType(container.getValue("type"));
                        droppedBlock.setOperation(container.getValue("operation"));
                        droppedBlock.activateEvents();
                        System.out.println (container.getData().toString());

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
            dragOverBlock.displayBlock();
            dragOverBlock.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));

            ClipboardContent content = new ClipboardContent();
            DragContainer container = new DragContainer();

            container.addData("name", dragOverBlock.getName());
            container.addData("position", Index);
            Index++;
            container.addData("type", dragOverBlock.getType());
            container.addData("operation", dragOverBlock.getOperation());
            content.put(DragContainer.AddNode, container);

            dragOverBlock.startDragAndDrop(TransferMode.ANY).setContent(content);
            dragOverBlock.setVisible(true);
            dragOverBlock.setMouseTransparent(true);


            event.consume();

        });
    }
}
