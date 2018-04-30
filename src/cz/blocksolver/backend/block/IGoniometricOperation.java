package cz.blocksolver.backend.block;

import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.PortType;

public interface IGoniometricOperation {
    OperationResult executeOperation(Double value, PortType type);
}
