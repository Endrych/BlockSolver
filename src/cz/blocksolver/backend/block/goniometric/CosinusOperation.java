package cz.blocksolver.backend.block.goniometric;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Trida reprezentujici operaci cosinus
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class CosinusOperation implements IGoniometricOperation{
    private OperationResult outcome;
    private Double result;

    /**
     * Instance operace
     */
    private static IGoniometricOperation instance;

    /**
     * Privatni konstruktor (singleton)
     */
    private CosinusOperation() {
    }

    /**
     * Ziska instanci tridy (singleton)
     * @return instance
     */
    public static IGoniometricOperation getInstance(){
        if(instance == null){
            instance = new CosinusOperation();
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
            result = cosinusFromRadian(val);
        }else if(type == PortType.DEGREE){
            result = cosinusFromDegree(val);
        }else{
            // TODO: CHYBA
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
     * Vypocet cosinu z radianu
     * @param radian hodnota v radianech
     * @return hodnota cosinu
     */
    private Double cosinusFromRadian(Double radian) {
        return Math.cos(radian);
    }

    /**
     * Vypocet cosinu ze stupnu
     * @param degree hodnota ve stupnich
     * @return hodnota cosinu
     */
    private Double cosinusFromDegree(Double degree){
        return Math.cos(Math.toRadians(degree));
    }

}
