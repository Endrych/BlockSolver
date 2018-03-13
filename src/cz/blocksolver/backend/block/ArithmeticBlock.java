package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.arithmetic.*;
import cz.blocksolver.backend.port.InputPort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArithmeticBlock extends Block  {

    private Collection<IArithmeticOperation> listOfOperations;
    private IArithmeticOperation operation;
    private Collection<InputPort> inputPorts;
    private Double result;
    private List<Double> inputValues;

    public ArithmeticBlock(String name, Integer x, Integer y, Integer width, Integer height, IArithmeticOperation operation) {
        super(name, x, y, width, height);
        this.operation = operation;
        this.listOfOperations = new ArrayList<>();
        this.inputPorts = new ArrayList<>();
        this.inputPorts.add(new InputPort(1));
        this.inputPorts.add(new InputPort(2));
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
        this.result = operation.executeOperation();
    }

    public Double getResult() {
        return result;
    }

    public void getInputs(){
        if (getNumberOfInputPorts() != 2){
        }else{
            this.inputPorts =

        }
    }

}
