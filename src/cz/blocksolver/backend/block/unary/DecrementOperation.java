package cz.blocksolver.backend.block.unary;

import cz.blocksolver.backend.block.IUnaryOperation;
import cz.blocksolver.backend.block.OperationResult;

/**
 * Trida reprezentujici operaci dekrementace
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class DecrementOperation implements IUnaryOperation {

    /**
     * Instance operace
     */
    private static IUnaryOperation instance;

    private Double result;
    private OperationResult output;

    /**
     * Privatni konstruktor (singleton)
     */
    private DecrementOperation(){
    }

    /**
     * Ziska instanci tridy (singleton)
     * @return instance
     */
    public static IUnaryOperation getInstance(){
        if(instance == null){
            instance = new DecrementOperation();
        }
        return instance;
    }

    /**
     * Provedeni operace
     * @param input vstupni hodnota
     * @return vysledek operace
     */
    @Override
    public OperationResult executeOperation(Double input) {
        result = input - new Double(1.0);
        output = new OperationResult(result);
        return output;
    }
}
