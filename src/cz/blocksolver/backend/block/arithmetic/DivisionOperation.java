package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;

public class DivisionOperation implements IArithmeticOperation {
    OperationResult outcome;
    Double result;

    @Override
    public OperationResult executeOperation(Double firstInput, Double secondInput) {
        try
        {
            return firstInput / secondInput;
        }
        catch (ArithmeticException exception){
            return 0.0;
        }
    }
}
