package cz.blocksolver.backend.block.unary;

import cz.blocksolver.backend.block.IUnaryOperation;
import cz.blocksolver.backend.block.OperationResult;

public class SquareOperation implements IUnaryOperation {

    private static IUnaryOperation instance;
    private Double result;
    private OperationResult output;

    private SquareOperation(){
    }

    public static IUnaryOperation getInstance(){
        if(instance == null){
            instance = new SquareOperation();
        }
        return instance;
    }

    @Override
    public OperationResult executeOperation(Double input) {
        result = Math.pow(input, 2.0);
        output = new OperationResult(result);

        return output;
    }
}
