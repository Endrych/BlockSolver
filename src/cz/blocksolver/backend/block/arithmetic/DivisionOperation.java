package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;

public class DivisionOperation implements IArithmeticOperation {
    @Override
    public Double executeOperation(Double firstInput, Double secondInput) {
        try
        {
            return firstInput / secondInput;
        }
        catch (ArithmeticException exception){
            return 0.0;
        }
    }
}
