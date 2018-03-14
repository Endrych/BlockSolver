package cz.blocksolver.backend.block.goniometric;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SinusOperation implements IGoniometricOperation {

    private OperationResult outcome;
    private Double result;

    private static IGoniometricOperation instance;

    private SinusOperation() {
    }

    public static IGoniometricOperation getInstance(){
        if(instance == null){
            instance = new SinusOperation();
        }

        return instance;
    }

    @Override
    public OperationResult executeOperation(InputPort input) {
        PortType type = input.getType();
        if(type == PortType.RADIAN){
            result = sinusFromRadian(input.getValue());
        }else if(type == PortType.DEGREE){
            result = sinusFromDegree(input.getValue());
        }else{
//            TODO: CHYBA
        }
        outcome = new OperationResult(roundResultToSixDigits(result), PortType.NUMBER);
        return outcome;
    }

    private Double roundResultToSixDigits(Double result){
        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private Double sinusFromRadian(Double radian) {
        return Math.sin(radian);
    }

    private Double sinusFromDegree(Double degree){
        return Math.sin(Math.toRadians(degree));
    }



}
