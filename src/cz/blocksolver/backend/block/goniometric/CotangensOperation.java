package cz.blocksolver.backend.block.goniometric;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Trida reprezentujici operaci cotangens
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class CotangensOperation implements IGoniometricOperation {
    private OperationResult outcome;
    private Double result;

    /**
     * Instance operace
     */
    private static IGoniometricOperation instance;

    /**
     * Privatni konstruktor (singleton)
     */
    private CotangensOperation() {
    }

    /**
     * Ziska instanci tridy (singleton)
     * @return instance
     */
    public static IGoniometricOperation getInstance(){
        if(instance == null){
            instance = new CotangensOperation();
        }

        return instance;
    }

    /**
     * Provedeni operace
     * @param val vstupni hodnota
     * @param type typ
     * @return vysledek operace
     */
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

    /**
     * Metoda zaokrouhli vysledek na 5 cisel
     * @param result vstupni hodnota
     * @return upravena hodnota
     */
    private Double roundResultToFiveDigits(Double result){
        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Metoda vypocita cotangens z radianu
     * @param radian vstupni hodnota v radianech
     * @return hodnota cotangensu
     */
    private Double cotangensFromRadian(Double radian) {
        return 1.0 / Math.tan(radian);
    }

    /**
     * Metoda vypocita cotangens ze stupnu
     * @param degree vstupni hodnota ve stupnich
     * @return hodnota cotangensu
     */
    private Double cotangensFromDegree(Double degree){
        return 1.0 / Math.tan(Math.toRadians(degree));
    }
}
