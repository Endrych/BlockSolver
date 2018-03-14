package cz.blocksolver.backend.block.unary;

import cz.blocksolver.backend.block.IUnaryOperation;
import cz.blocksolver.backend.block.OperationResult;

public class IncrementOperation implements IUnaryOperation{

    private static IUnaryOperation instance;
    private Double result;
    private OperationResult output;

    private IncrementOperation(){
    }

    public static IUnaryOperation getInstance(){
        if(instance == null){
            instance = new IncrementOperation();
        }
        return instance;
    }

    @Override
    public OperationResult executeOperation(Double input) {
        result = input + new Double(1.0);
        output = new OperationResult(result);
        return output;
    }
}
