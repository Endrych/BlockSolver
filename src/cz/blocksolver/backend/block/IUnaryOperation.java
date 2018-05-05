package cz.blocksolver.backend.block;

/**
 * Interface pro unarni operaci
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public interface IUnaryOperation {
    /**
     * Metada provede operaci
     * @param input vstupni hodnota
     * @return vysledek operace
     */
    OperationResult executeOperation(Double input);
}
