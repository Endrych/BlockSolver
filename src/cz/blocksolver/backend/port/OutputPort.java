package cz.blocksolver.backend.port;

import java.util.Objects;

public class OutputPort extends Port {
    private InputPort inputPort;

    public OutputPort(PortType type, Double value) {
        super(type, value);
    }

    public InputPort getInputPort() {
        return inputPort;
    }

    public void setInputPort(InputPort inputPort) {
        this.inputPort = inputPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputPort)) return false;
        OutputPort that = (OutputPort) o;
        return Objects.equals(this.inputPort, that.inputPort) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.value, that.value) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(inputPort, name,value,type);
    }

    @Override
    public String toString() {
        return "Výstupní port";
    }
}
