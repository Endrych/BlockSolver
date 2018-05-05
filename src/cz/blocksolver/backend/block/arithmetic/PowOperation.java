package cz.blocksolver.backend.block.arithmetic;

import cz.blocksolver.backend.block.IArithmeticOperation;
import cz.blocksolver.backend.block.OperationResult;

import static java.lang.StrictMath.pow;

/**
 * Trida reprezentujici operaci umocneni
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class PowOperation implements IArithmeticOperation {
    private OperationResult outcome;
    private Double result;

    /**
     * Instance operace
     */
    private static IArithmeticOperation instance;

    /**
     * Privatni konstruktor (singleton)
     */
    private PowOperation(){
    }

    /**
     * Ziska instanci tridy (singleton)
     * @return instance
     */
    public static IArithmeticOperation getInstance(){
        if(instance == null){
            instance = new PowOperation();
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
        result = pow(firstInput, secondInput);
        outcome = new OperationResult(result);
        return outcome;
    }

    @Override
    public String toString() { return "Umocnění"; }
}
