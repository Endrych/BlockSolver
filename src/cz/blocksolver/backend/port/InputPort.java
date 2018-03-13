package cz.blocksolver.backend.port;

import java.util.Objects;

public class InputPort extends Port {
    private OutputPort outputPort;

    public OutputPort getOutputPort() {
        return outputPort;
    }

    public void setOutputPort(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputPort inputPort = (InputPort) o;
        return this.outputPort.equals(inputPort.outputPort)
                && this.name.equals(inputPort.name)
                && this.type == inputPort.type
                && this.value.equals(inputPort.value);
    }

    @Override
    public int hashCode() {
        return this.outputPort.hashCode() ^ this.name.hashCode() ^ this.type.hashCode() ^ this.value.hashCode();
    }

    @Override
    public String toString() {
        return "Vstupní port";
    }
}
