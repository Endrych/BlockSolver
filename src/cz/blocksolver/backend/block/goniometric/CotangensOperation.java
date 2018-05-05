package cz.blocksolver.backend.block.goniometric;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CotangensOperation implements IGoniometricOperation {
    private OperationResult outcome;
    private Double result;

    private static IGoniometricOperation instance;

    private CotangensOperation() {
    }

    public static IGoniometricOperation getInstance(){
        if(instance == null){
            instance = new CotangensOperation();
        }

        return instance;
    }


    @Override
    public OperationResult executeOperation(Double val,PortType type) {
        if(type == PortType.RADIAN){
            result = cotangensFromRadian(val);
        }else if(type == PortType.DEGREE){
            result = cotangensFromDegree(val);
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

    private Double cotangensFromRadian(Double radian) {
        return 1.0 / Math.tan(radian);
    }

    private Double cotangensFromDegree(Double degree){
        return 1.0 / Math.tan(Math.toRadians(degree));
    }
}
