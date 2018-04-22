package cz.blocksolver.backend.block;

import cz.blocksolver.backend.port.InputPort;

public interface IGoniometricOperation {
    OperationResult executeOperation(InputPort input);
}
