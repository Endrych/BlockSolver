package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;

public class AddOperation implements IArithmeticOperation {
    OperationResult outcome;
    Double result;

    public OperationResult executeOperation(Double firstInput, Double secondInput) {
        result = Math.addExact(firstInput, secondInput);
        outcome = new OperationResult(result);
        return outcome;
    }
}
