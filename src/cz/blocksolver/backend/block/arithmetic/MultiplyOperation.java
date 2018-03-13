package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;

public class MultiplyOperation implements IArithmeticOperation {
    OperationResult outcome;
    Double result;

    @Override
    public OperationResult executeOperation(Double firstInput, Double secondInput) {
        result = firstInput * secondInput;
        outcome = new OperationResult(result);
        return outcome;
    }
}
