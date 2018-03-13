package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.arithmetic.*;

import java.util.ArrayList;
import java.util.Collection;

public class ArithmeticBlock extends Block  {

    private Collection<IArithmeticOperation> listOfOperations;
    private IArithmeticOperation operation;
    private Double result;

    public ArithmeticBlock(String name, Integer x, Integer y, Integer width, Integer height, IArithmeticOperation operation) {
        super(name, x, y, width, height);
        this.operation = operation;
        this.listOfOperations = new ArrayList<>();
        initializeOperations();
    }

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




}
