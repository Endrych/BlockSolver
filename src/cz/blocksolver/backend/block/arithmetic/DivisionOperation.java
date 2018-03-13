package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;
import cz.blocksolver.backend.block.OperationResult;

public class DivisionOperation implements IArithmeticOperation {
    private OperationResult outcome;
    private Double result;

    private static IArithmeticOperation instance;

    private DivisionOperation(){
    }

    public static IArithmeticOperation getInstance(){
        if(instance == null){
            instance = new DivisionOperation();
        }
        return instance;
    }

    @Override
    public OperationResult executeOperation(Double firstInput, Double secondInput) {
        try
        {
            result = firstInput / secondInput;
            outcome = new OperationResult(result);
            return outcome;
        }
        catch (ArithmeticException exception){
            outcome = new OperationResult(0.0, false);
            return outcome;
        }
    }

    @Override
    public String toString() { return "Dělení"; }
}
