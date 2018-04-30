package cz.blocksolver.backend.block.goniometric;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CosinusOperation implements IGoniometricOperation{
    private OperationResult outcome;
    private Double result;

    private static IGoniometricOperation instance;

    private CosinusOperation() {
    }

    public static IGoniometricOperation getInstance(){
        if(instance == null){
            instance = new CosinusOperation();
        }

        return instance;
    }

    @Override
    public OperationResult executeOperation(Double val,PortType type) {
        if(type == PortType.RADIAN){
            result = cosinusFromRadian(val);
        }else if(type == PortType.DEGREE){
            result = cosinusFromDegree(val);
        }else{
//            TODO: CHYBA
        }
        outcome = new OperationResult(roundResultToFiveDigits(result), PortType.NUMBER);
        return outcome;
    }

    private Double roundResultToFiveDigits(Double result){
        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private Double cosinusFromRadian(Double radian) {
        return Math.cos(radian);
    }

    private Double cosinusFromDegree(Double degree){
        return Math.cos(Math.toRadians(degree));
    }

}
