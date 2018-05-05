package cz.blocksolver.backend.block.unary;

import cz.blocksolver.backend.block.IGoniometricOperation;
import cz.blocksolver.backend.block.IUnaryOperation;
import cz.blocksolver.backend.block.OperationResult;
import cz.blocksolver.backend.port.PortType;

/**
 * Trida reprezentujici operaci prevod cisla na stupne
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class NumToDeg implements IUnaryOperation{
    private OperationResult outcome;
    private Double result;

    /**
     * Instance operace
     */
    private static IUnaryOperation instance;

    /**
     * Privatni konstruktor (singleton)
     */
    private NumToDeg() {
    }

    /**
     * Ziska instanci tridy (singleton)
     * @return instance
     */
    public static IUnaryOperation getInstance(){
        if(instance == null){
            instance = new NumToDeg();
        }

        return instance;
    }

    /**
     * Provedeni operace
     * @param val vstupni hodnota
     * @return vysledek operace
     */
    @Override
    public OperationResult executeOperation(Double val) {
        result = val;

        outcome = new OperationResult(result, PortType.DEGREE);
        return outcome;
    }

}
