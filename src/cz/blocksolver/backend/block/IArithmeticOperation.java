package cz.blocksolver.backend.block;

/**
 * Interface pro aritmetickou operaci
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public interface IArithmeticOperation {
    /**
     * Metada provede operaci
     * @param firstInput vstupni hodnota 1
     * @param secondInput vstupni hodnota 2
     * @return vysledek operace
     */
    OperationResult executeOperation(Double firstInput, Double secondInput);
}
