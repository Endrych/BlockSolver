package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.unary.*;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import cz.blocksolver.backend.port.PortType;

import java.util.ArrayList;
import java.util.Collection;

public class UnaryBlock extends Block{

    private IUnaryOperation operation;
    private Collection<IUnaryOperation> listOfOperations;
    private InputPort inputPort;
    private OperationResult result;

    public UnaryBlock(String name, Integer x, Integer y, Integer width, Integer height, BlockType type, IUnaryOperation operation) {
        super(name, x, y, width, height, type);
        this.operation = operation;
        inputPort = new InputPort(PortType.NUMBER, 0.0, 1 );
        listOfOperations = new ArrayList<>();
        initializeListOfOperations();
    }

    private void initializeListOfOperations() {
        listOfOperations.add(IncrementOperation.getInstance());
        listOfOperations.add(DecrementOperation.getInstance());
        listOfOperations.add(SquareOperation.getInstance());
        listOfOperations.add(SquareRootOperation.getInstance());
        listOfOperations.add(CubeRootOperation.getInstance());
    }

    @Override
    public void executeBlock() {
        result = this.operation.executeOperation(inputPort.getValue());
        outputPort.setValue(result.getResult());
    }

    public InputPort getInputPort(Integer index){
        if(index == 1){
            return inputPort;
        }else{
            throw new IndexOutOfBoundsException("getInputPort in GoniometricBlock\n");
        }
    }

    public void changeOperation(IUnaryOperation operation){
        this.operation = operation;
    }

}
