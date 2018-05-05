package cz.blocksolver.frontend;

import cz.blocksolver.backend.block.*;
import cz.blocksolver.backend.block.arithmetic.*;
import cz.blocksolver.backend.block.goniometric.CosinusOperation;
import cz.blocksolver.backend.block.goniometric.CotangensOperation;
import cz.blocksolver.backend.block.goniometric.SinusOperation;
import cz.blocksolver.backend.block.goniometric.TangensOperation;
import cz.blocksolver.backend.block.unary.*;
import cz.blocksolver.backend.port.OutputPort;
import cz.blocksolver.backend.port.PortType;
import cz.blocksolver.backend.schema.Schema;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoadSchema {
    public void execute(MainDisplay display, Schema schema, ArrayList<DragBlock> dragBlocks) throws ParserConfigurationException, IOException, SAXException {

        Integer Index = 1;
        String filePath = "C:\\savedFile\\cars.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("block");
            DragBlock newBlock;// = new DragBlock();
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;


//                    NodeList input1 = getElementsByTagName("input_port1");
//                    System.out.println(input1);
                    newBlock = new DragBlock(display);

                    newBlock.setName(eElement.getElementsByTagName("drag_name").item(0).getTextContent());

                    if(eElement.getElementsByTagName("block_type").item(0).getTextContent().equals("ARITHMETIC")){
                        newBlock.dragBlock.setType(BlockType.ARITHMETIC);
                        IArithmeticOperation op_type = AddOperation.getInstance();
                        if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("add")){
                            op_type = AddOperation.getInstance();

                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("sub")){
                            op_type = SubtractionOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("mult")){
                            op_type = MultiplyOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("div")){
                            op_type = DivisionOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("pow")){
                            op_type = PowOperation.getInstance();
                        }

                        newBlock.dragBlock = new ArithmeticBlock(
                                eElement.getElementsByTagName("name").item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName("coord_x").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("coord_y").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent()),
                                op_type
                        );
                        newBlock.setType("arithmetic");
                    }else if(eElement.getElementsByTagName("block_type").item(0).getTextContent().equals("GONIOMETRIC")){
                        newBlock.dragBlock.setType(BlockType.GONIOMETRIC);
                        IGoniometricOperation op_type = SinusOperation.getInstance();
                        if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("sin")){
                            op_type = SinusOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("cos")){
                            op_type = CosinusOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("tang")){
                            op_type = TangensOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("cotg")){
                            op_type = CotangensOperation.getInstance();
                        }

                        newBlock.dragBlock = new GoniometricBlock(
                                eElement.getElementsByTagName("name").item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName("coord_x").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("coord_y").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent()),
                                op_type
                        );
                        newBlock.dragBlock.getInputPort(1).setType(PortType.DEGREE);
                        newBlock.setType("goniometric");

                    }else if(eElement.getElementsByTagName("block_type").item(0).getTextContent().equals("UNARY")){
                        newBlock.dragBlock.setType(BlockType.UNARY);
                            IUnaryOperation op_type = CubeRootOperation.getInstance();
                        if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("cro")){
                            op_type = CubeRootOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("pot")){
                            op_type = SquareOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("sqr")){
                            op_type = SquareRootOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("dec")){
                            op_type = DecrementOperation.getInstance();
                        }else if(eElement.getElementsByTagName("operation_type").item(0).getTextContent().equals("inc")){
                            op_type =IncrementOperation.getInstance();
                        }

                        newBlock.dragBlock = new UnaryBlock(
                                eElement.getElementsByTagName("name").item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName("coord_x").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("coord_y").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent()),
                                op_type
                        );
                        newBlock.setType("unary");
                    }

                    newBlock.setOperation(eElement.getElementsByTagName("operation_type").item(0).getTextContent());

                    newBlock.XCoord = Integer.parseInt(eElement.getElementsByTagName("coord_x").item(0).getTextContent());
                    newBlock.YCoord = Integer.parseInt(eElement.getElementsByTagName("coord_y").item(0).getTextContent());

                    //                    System.out.println("???? " + eElement.getElementsByTagName("input_port1").item(0).getTextContent());
                    newBlock.setIndex(Integer.parseInt(eElement.getElementsByTagName("index").item(0).getTextContent()));
                    if(Index < (Integer.parseInt(eElement.getElementsByTagName("index").item(0).getTextContent()))){
                        Index = (Integer.parseInt(eElement.getElementsByTagName("index").item(0).getTextContent()));
                    }
                    newBlock.dragBlock.setOutputPort(new OutputPort(PortType.NUMBER, 0.0));
                    newBlock.dragBlock.getOutputPort().setBlockIndex(newBlock.getIndex());
//                        System.out.println("???? " + eElement.getElementsByTagName("value").item(0).getTextContent());

                    newBlock.dragBlock.getInputPort(1);
                    schema.addBlock(newBlock.dragBlock);
                    dragBlocks.add(newBlock);
//  schema.addBlock();
                    System.out.println("First Name : " +newBlock.dragBlock);

                }
            }
            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    if (eElement.getElementsByTagName("in1_output_port").getLength() > 0) {
                        OutputPort myOut = null;
                        for(int i = 0; i < dragBlocks.size(); i++){
                            if(dragBlocks.get(i).getIndex() == Integer.parseInt(eElement.getElementsByTagName("in1_output_port").item(0).getTextContent())){
                                myOut = dragBlocks.get(i).dragBlock.getOutputPort();
                            }
                        }
                        dragBlocks.get(temp).dragBlock.getInputPort(1).setOutputPort(myOut);
                        dragBlocks.get(temp).dragBlock.getInputPort(1).setConnectedToOutputPort(true);
                    } else if (eElement.getElementsByTagName("in1_value").getLength() > 0) {
                        if (eElement.getElementsByTagName("in1_value").item(0).getTextContent().equals("null")) {
                            dragBlocks.get(temp).dragBlock.getInputPort(1).setValue(0.0);
                        } else {
                            dragBlocks.get(temp).dragBlock.getInputPort(1).setValue(Double.parseDouble(eElement.getElementsByTagName("in1_value").item(0).getTextContent()));
                            dragBlocks.get(temp).dragBlock.getInputPort(1).setValueSet(true);
                            if(dragBlocks.get(temp).dragBlock.getType() == BlockType.GONIOMETRIC){
                                if(eElement.getElementsByTagName("in_type").item(0).getTextContent().equals("DEGREE")){
                                    dragBlocks.get(temp).dragBlock.getInputPort(1).setType(PortType.DEGREE);
                                }else if(eElement.getElementsByTagName("in_type").item(0).getTextContent().equals("RADIAN")){
                                    dragBlocks.get(temp).dragBlock.getInputPort(1).setType(PortType.RADIAN);
                                }
                            }
                        }
                    }
                    if (eElement.getElementsByTagName("in2_output_port").getLength() > 0) {
                        OutputPort myOut = null;
                        for(int i = 0; i < dragBlocks.size(); i++){
                            if(dragBlocks.get(i).getIndex() == Integer.parseInt(eElement.getElementsByTagName("in2_output_port").item(0).getTextContent())){
                                myOut = dragBlocks.get(i).dragBlock.getOutputPort();
                            }
                        }
                        dragBlocks.get(temp).dragBlock.getInputPort(2).setOutputPort(myOut);
                        dragBlocks.get(temp).dragBlock.getInputPort(2).setConnectedToOutputPort(true);
                    } else if (eElement.getElementsByTagName("in2_value").getLength() > 0) {
                        if (eElement.getElementsByTagName("in2_value").item(0).getTextContent().equals("null")) {
                            dragBlocks.get(temp).dragBlock.getInputPort(2).setValue(0.0);
                        } else {
                            dragBlocks.get(temp).dragBlock.getInputPort(2).setValue(Double.parseDouble(eElement.getElementsByTagName("in2_value").item(0).getTextContent()));
                            dragBlocks.get(temp).dragBlock.getInputPort(2).setValueSet(true);
                        }
                    }

                    if(eElement.getElementsByTagName("line1").getLength() > 0){
                        OutputPort myOut = null;

                        for(int i = 0; i < dragBlocks.size(); i++){
                            if(dragBlocks.get(i).getIndex() == Integer.parseInt(eElement.getElementsByTagName("out_index").item(0).getTextContent())) {
                                myOut = dragBlocks.get(i).dragBlock.getOutputPort();
                            }
                        }

                        dragBlocks.get(temp).setInputPortLine1(new ConnectingLine(
                                myOut,
                                Integer.parseInt(eElement.getElementsByTagName("out_x").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("out_y").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("in_x").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("in_y").item(0).getTextContent()) ,
                                1,
                                eElement.getElementsByTagName("type").item(0).getTextContent(),
                                display.LoadLines(
                                        Integer.parseInt(eElement.getElementsByTagName("out_x").item(0).getTextContent()),
                                        Integer.parseInt(eElement.getElementsByTagName("out_y").item(0).getTextContent()),
                                        Integer.parseInt(eElement.getElementsByTagName("in_x").item(0).getTextContent()),
                                        Integer.parseInt(eElement.getElementsByTagName("in_y").item(0).getTextContent()) ,
                                        eElement.getElementsByTagName("type").item(0).getTextContent(),
                                        1,
                                        myOut
                                )
                        ));
                    }

                    if(eElement.getElementsByTagName("line2").getLength() > 0){
                        OutputPort myOut = null;

                        for(int i = 0; i < dragBlocks.size(); i++){
                            if(dragBlocks.get(i).getIndex() == Integer.parseInt(eElement.getElementsByTagName("out_index2").item(0).getTextContent())) {
                                myOut = dragBlocks.get(i).dragBlock.getOutputPort();
                            }
                        }

                        dragBlocks.get(temp).setInputPortLine2(new ConnectingLine(
                                myOut,
                                Integer.parseInt(eElement.getElementsByTagName("out_x2").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("out_y2").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("in_x2").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("in_y2").item(0).getTextContent()) ,
                                2,
                                eElement.getElementsByTagName("type2").item(0).getTextContent(),
                                display.LoadLines(
                                        Integer.parseInt(eElement.getElementsByTagName("out_x2").item(0).getTextContent()),
                                        Integer.parseInt(eElement.getElementsByTagName("out_y2").item(0).getTextContent()),
                                        Integer.parseInt(eElement.getElementsByTagName("in_x2").item(0).getTextContent()),
                                        Integer.parseInt(eElement.getElementsByTagName("in_y2").item(0).getTextContent()) ,
                                        eElement.getElementsByTagName("type2").item(0).getTextContent(),
                                        2,
                                        myOut
                                )
                        ));
                    }
                    display.getChildren().add(dragBlocks.get(temp));
                    dragBlocks.get(temp).relocateToPoint(new Point2D(dragBlocks.get(temp).dragBlock.getX(),dragBlocks.get(temp).dragBlock.getY()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Index += 2;
        display.setIndex(Index);
    }
}
