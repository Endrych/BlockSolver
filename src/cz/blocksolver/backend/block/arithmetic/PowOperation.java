package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;
import cz.blocksolver.backend.block.OperationResult;

import static java.lang.StrictMath.pow;

public class PowOperation implements IArithmeticOperation {
    private OperationResult outcome;
    private Double result;

    private static IArithmeticOperation instance;

    private PowOperation(){
    }

    public static IArithmeticOperation getInstance(){
        if(instance == null){
            instance = new PowOperation();
        }
        return instance;
    }


    @Override
    public OperationResult executeOperation(Double firstInput, Double secondInput) {
        result = pow(firstInput, secondInput);
        outcome = new OperationResult(result);
        return outcome;
    }

    @Override
    public String toString() { return "Umocnění"; }
}
