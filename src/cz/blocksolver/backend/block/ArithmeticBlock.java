package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.arithmetic.*;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.util.ArrayList;
import java.util.Collection;

public class ArithmeticBlock extends Block  {

    private Collection<IArithmeticOperation> listOfOperations;
    private IArithmeticOperation operation;
    private InputPort[] inputPorts = new InputPort[2];
    private OperationResult result;

    public ArithmeticBlock(String name, Integer x, Integer y, Integer width, Integer height, IArithmeticOperation operation) {
        super(name, x, y, width, height);
        this.operation = operation;
        this.listOfOperations = new ArrayList<>();
        this.inputPorts[0] = new InputPort(PortType.NUMBER, 0.0, 1);
        this.inputPorts[1] = new InputPort(PortType.NUMBER, 0.0,2);
        initializeOperations();
    }
    //gettery na inputy
    private void initializeOperations() {
        this.listOfOperations.add(AddOperation.getInstance());
        this.listOfOperations.add(SubtractionOperation.getInstance());
        this.listOfOperations.add(MultiplyOperation.getInstance());
        this.listOfOperations.add(DivisionOperation.getInstance());
        this.listOfOperations.add(PowOperation.getInstance());
    }

    public void changeOperation(IArithmeticOperation operation){
        this.operation = operation;
    }

    @Override
    public void executeBlock(){
        if(compareTypes()) {
//            tODO: check if succeded
            this.result = operation.executeOperation(inputPorts[0].getValue(), inputPorts[1].getValue());
            outputPort.setValue(result.getResult());
        }else{
//            TODO:
        }
    }

    private Boolean compareTypes(){
        return inputPorts[0].getType() == inputPorts[1].getType();
    }

    public InputPort getInputPort(Integer index){
        if(index == 1){
            return inputPorts[0];
        }else if(index == 2){
            return inputPorts[1];
        }else{
            throw new IndexOutOfBoundsException("getInputPort in ArithmeticBlock\n");
        }
    }
}
