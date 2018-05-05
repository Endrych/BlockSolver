package cz.blocksolver.backend.block.unary;

import cz.blocksolver.backend.block.IUnaryOperation;
import cz.blocksolver.backend.block.OperationResult;

public class DecrementOperation implements IUnaryOperation {

    private static IUnaryOperation instance;
    private Double result;
    private OperationResult output;

    private DecrementOperation(){
    }

    public static IUnaryOperation getInstance(){
        if(instance == null){
            instance = new DecrementOperation();
        }
        return instance;
    }

    @Override
    public OperationResult executeOperation(Double input) {
        result = input - new Double(1.0);
        output = new OperationResult(result);
        return output;
    }
}
