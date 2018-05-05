package cz.blocksolver.backend.block.goniometric;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Trida reprezentujici operaci tangens
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class TangensOperation implements IGoniometricOperation {
    private OperationResult outcome;
    private Double result;

    /**
     * Instance operace
     */
    private static IGoniometricOperation instance;

    /**
     * Privatni konstruktor (singleton)
     */
    private TangensOperation() {
    }

    /**
     * Ziska instanci tridy (singleton)
     * @return instance
     */
    public static IGoniometricOperation getInstance() {
        if (instance == null) {
            instance = new TangensOperation();
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
            result = tangensFromRadian(val);
        }else if(type == PortType.DEGREE){
            result = tangensFromDegree(val);
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
     * Vypocet tangensu z radianu
     * @param radian vstupni hodnota v radianech
     * @return hodnota tangensu
     */
    private Double tangensFromRadian(Double radian) {
        return Math.tan(radian);
    }

    /**
     * Vypocet tangensu ze stupnu
     * @param degree vstupni hodnota ve stupnich
     * @return hodnota tangensu
     */
    private Double tangensFromDegree(Double degree){
        return Math.tan(Math.toRadians(degree));
    }
}
