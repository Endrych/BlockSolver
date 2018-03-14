package cz.blocksolver.backend.block;

import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.util.ArrayList;
import java.util.Collection;

public class GoniometricBlock extends Block {

    private Collection<IGoniometricOperation> listOfOperations;
    private IGoniometricOperation operation;
    private InputPort inputPort;
    private OperationResult result;


    public GoniometricBlock(String name, Integer x, Integer y, Integer width, Integer height, IGoniometricOperation operation) {
        super(name, x, y, width, height);
        this.operation = operation;
        this.inputPort = new InputPort(PortType.NUMBER, 0.0,1);
        listOfOperations = new ArrayList<>();
        initializeListOfOperations();
    }

    private void initializeListOfOperations() {
    }

    public void changeOperation(IGoniometricOperation operation){
        this.operation = operation;
    }

    public void executeBlock(){

    }

    public InputPort getInputPort(Integer index){
        if(index == 1){
            return inputPort;
        }else{
            throw new IndexOutOfBoundsException("getInputPort in GoniometricBlock\n");
        }
    }
}
