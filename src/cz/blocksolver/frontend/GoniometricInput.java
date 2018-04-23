package cz.blocksolver.frontend;

import cz.blocksolver.backend.port.PortType;

public class GoniometricInput {
    public PortType type;
    public String value;

    public PortType getType() {
        return type;
    }

    public void setType(PortType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
