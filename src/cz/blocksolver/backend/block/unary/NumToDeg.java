package cz.blocksolver.backend.block.unary;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.IUnaryOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.PortType;

public class NumToDeg implements IUnaryOperation{
    private OperationResult outcome;
    private Double result;

    private static IUnaryOperation instance;

    private NumToDeg() {
    }

    public static IUnaryOperation getInstance(){
        if(instance == null){
            instance = new NumToDeg();
        }

        return instance;
    }

    @Override
    public OperationResult executeOperation(Double val) {
        result = val;

        outcome = new OperationResult(result, PortType.DEGREE);
        return outcome;
    }

}
