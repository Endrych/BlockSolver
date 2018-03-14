package cz.blocksolver.backend.block.unary;

import cz.blocksolver.backend.block.IUnaryOperation;
import cz.blocksolver.backend.block.OperationResult;

public class SquareRootOperation implements IUnaryOperation {
    private static IUnaryOperation instance;
    private Double result;
    private OperationResult output;

    private SquareRootOperation(){
    }

    public static IUnaryOperation getInstance(){
        if(instance == null){
            instance = new SquareRootOperation();
        }
        return instance;
    }

    @Override
    public OperationResult executeOperation(Double input) {
        result = Math.sqrt(input);
        output = new OperationResult(result);
        return output;
    }
}
