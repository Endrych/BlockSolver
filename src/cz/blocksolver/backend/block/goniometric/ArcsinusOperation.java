package cz.blocksolver.backend.block.goniometric;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

//Vraci hodnotu v radianech
public class ArcsinusOperation implements IGoniometricOperation {

    private OperationResult outcome;
    private Double result;

    private static IGoniometricOperation instance;

    private ArcsinusOperation() {
    }

    public static IGoniometricOperation getInstance(){
        if(instance == null){
            instance = new ArcsinusOperation();
        }

        return instance;
    }


    @Override
    public OperationResult executeOperation(Double val,PortType type) {
        if(type == PortType.NUMBER){
            result = sinRadianFromNumber(val);
        }else{
//            TODO: CHYBA
        }

        outcome = new OperationResult(result, PortType.RADIAN);
        return outcome;
    }

    private Double sinRadianFromNumber(Double number){
        return Math.asin(number);
    }
}
