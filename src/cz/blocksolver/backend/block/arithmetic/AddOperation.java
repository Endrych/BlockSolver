package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;
import cz.blocksolver.backend.block.OperationResult;

/**
 * Trida reprezentujici operaci scitani
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class AddOperation implements IArithmeticOperation {
    private OperationResult outcome;
    private Double result;

    /**
     * Instance operace
     */
    private static IArithmeticOperation instance;

    /**
     * Privatni konstruktor (singleton)
     */
    private AddOperation(){
    }

    /**
     * Ziska instanci tridy (singleton)
     * @return instance
     */
    public static IArithmeticOperation getInstance(){
        if(instance == null){
            instance = new AddOperation();
        }
        return instance;
    }

    /**
     * Provedeni operace
     * @param firstInput vstupni hodnota 1
     * @param secondInput vstupni hodnota 2
     * @return vysledek operace
     */
    @Override
    public OperationResult executeOperation(Double firstInput, Double secondInput) {
        result = firstInput + secondInput;
        outcome = new OperationResult(result);
        return outcome;
    }

    @Override
    public String toString() { return "Sčítání"; }
}
