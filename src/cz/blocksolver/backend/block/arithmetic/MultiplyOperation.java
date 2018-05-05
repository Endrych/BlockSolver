package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;
import cz.blocksolver.backend.block.OperationResult;

public class MultiplyOperation implements IArithmeticOperation {
    private OperationResult outcome;
    private Double result;

    private static IArithmeticOperation instance;

    private MultiplyOperation(){
    }

    public static IArithmeticOperation getInstance(){
        if(instance == null){
            instance = new MultiplyOperation();
        }
        return instance;
    }

    @Override
    public OperationResult executeOperation(Double firstInput, Double secondInput) {
        result = firstInput * secondInput;
        outcome = new OperationResult(result);
        return outcome;
    }

    @Override
    public String toString() { return "Násobení"; }
}
