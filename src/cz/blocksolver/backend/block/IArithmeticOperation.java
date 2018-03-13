package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.arithmetic.OperationResult;

public interface IArithmeticOperation {
    OperationResult executeOperation(Double firstInput, Double secondInput);
}
