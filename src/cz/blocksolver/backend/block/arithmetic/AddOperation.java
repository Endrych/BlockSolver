package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;
import cz.blocksolver.backend.block.OperationResult;

public class AddOperation implements IArithmeticOperation {
    private OperationResult outcome;
    private Double result;

    private static IArithmeticOperation instance;

    private AddOperation(){
    }

    public static IArithmeticOperation getInstance(){
        if(instance == null){
            instance = new AddOperation();
        }
        return instance;
    }

    @Override
    public OperationResult executeOperation(Double firstInput, Double secondInput) {
        result = firstInput + secondInput;
        outcome = new OperationResult(result);
        return outcome;
    }

    @Override
    public String toString() { return "Sčítání"; }
}
