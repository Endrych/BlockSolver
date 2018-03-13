package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;

public class DivisionOperation implements IArithmeticOperation {
    OperationResult outcome;
    Double result;

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
}
