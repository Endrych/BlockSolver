package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.goniometric.CosinusOperation;
import cz.blocksolver.backend.block.goniometric.CotangensOperation;
import cz.blocksolver.backend.block.goniometric.SinusOperation;
import cz.blocksolver.backend.block.goniometric.TangensOperation;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class GoniometricBlock extends Block {

    private Collection<IGoniometricOperation> listOfOperations;
    private IGoniometricOperation operation;
    private InputPort inputPort;
    private OperationResult result;


    public GoniometricBlock(String name, Integer x, Integer y, Integer width, Integer height, IGoniometricOperation operation) {
        super(name, x, y, width, height, BlockType.GONIOMETRIC);
        this.operation = operation;
        this.inputPort = new InputPort(PortType.NUMBER, 0.0,1);
        listOfOperations = new ArrayList<>();
        initializeListOfOperations();
    }

    private void initializeListOfOperations() {
        listOfOperations.add(SinusOperation.getInstance());
        listOfOperations.add(CosinusOperation.getInstance());
        listOfOperations.add(TangensOperation.getInstance());
        listOfOperations.add(CotangensOperation.getInstance());
    }

    public void changeOperation(IGoniometricOperation operation){
        this.operation = operation;
    }

    public Boolean executeBlock(Double one){
        //TODO kontrola zda uspel execute
        result = operation.executeOperation(one, inputPort.getType());
        outputPort.setValue(result.getResult());
        outputPort.setType(result.getPortType());
        return true;
    }

    public InputPort getInputPort(Integer index){
        if(index == 1){
            return inputPort;
        }else{
            throw new IndexOutOfBoundsException("getInputPort in GoniometricBlock\n");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoniometricBlock that = (GoniometricBlock) o;
        return Objects.equals(listOfOperations, that.listOfOperations) &&
                Objects.equals(operation, that.operation) &&
                Objects.equals(inputPort, that.inputPort) &&
                Objects.equals(result, that.result)&&
                Objects.equals(name, that.name) &&
                Objects.equals(x,that.x)&&
                Objects.equals(y,that.y)&&
                Objects.equals(width,that.width)&&
                Objects.equals(height,that.height)&&
                Objects.equals(outputPort,that.outputPort)&&
                Objects.equals(type,that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(listOfOperations, operation, inputPort, result,name,x,y,width,height,outputPort,type);
    }
}
