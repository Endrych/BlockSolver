package cz.blocksolver.backend.block.unary;

import cz.blocksolver.backend.block.IUnaryOperation;
import cz.blocksolver.backend.block.OperationResult;

public class CubeRootOperation implements IUnaryOperation {
    private static IUnaryOperation instance;
    private Double result;
    private OperationResult output;

    private CubeRootOperation(){
    }

    public static IUnaryOperation getInstance(){
        if(instance == null){
            instance = new CubeRootOperation();
        }
        return instance;
    }

    @Override
    public OperationResult executeOperation(Double input) {
        result = Math.cbrt(input);
        output = new OperationResult(result);
        return output;
    }
}
