package cz.blocksolver.backend.block;

import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

/**
 * Interface pro goniometrickou operaci
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public interface IGoniometricOperation {
    /**
     * Metada provede operaci
     * @param value vstupni hodnota
     * @param type typ
     * @return vysledek operace
     */
    OperationResult executeOperation(Double value, PortType type);
}
