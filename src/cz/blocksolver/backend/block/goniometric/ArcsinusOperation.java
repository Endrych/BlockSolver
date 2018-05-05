package cz.blocksolver.backend.block.goniometric;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

/**
 * Trida reprezentujici operaci arcsinus
 * Vraci hodnotu v radianech
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class ArcsinusOperation implements IGoniometricOperation {

    private OperationResult outcome;
    private Double result;

    /**
     * Instance operace
     */
    private static IGoniometricOperation instance;

    /**
     * Privatni konstruktor (singleton)
     */
    private ArcsinusOperation() {
    }

    /**
     * Ziska instanci tridy (singleton)
     * @return instance
     */
    public static IGoniometricOperation getInstance(){
        if(instance == null){
            instance = new ArcsinusOperation();
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
        if(type == PortType.NUMBER){
            result = sinRadianFromNumber(val);
        }else{
//            TODO: CHYBA
        }

        outcome = new OperationResult(result, PortType.RADIAN);
        return outcome;
    }

    /**
     * Metoda prevede cislo na arcsinus
     * @param number
     * @return
     */
    private Double sinRadianFromNumber(Double number){
        return Math.asin(number);
    }
}
