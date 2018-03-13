package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;

public class MultiplyOperation implements IArithmeticOperation {
    @Override
    public Double executeOperation(Double firstInput, Double secondInput) {
        return firstInput * secondInput;
    }
}
