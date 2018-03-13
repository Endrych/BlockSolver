package cz.blocksolver.backend.port;

public class OutputPort extends Port {
    private InputPort inputPort;

    public InputPort getInputPort() {
        return inputPort;
    }

    public void setInputPort(InputPort inputPort) {
        this.inputPort = inputPort;
    }

    @Override
    public int hashCode() {
        return this.inputPort.hashCode() ^ this.name.hashCode() ^ this.type.hashCode() ^ this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OutputPort outputPort = (OutputPort) obj;
        return this.inputPort.equals(outputPort.inputPort)
                && this.name.equals(outputPort.name)
                && this.type == outputPort.type
                && this.value.equals(outputPort.value);
    }

    @Override
    public String toString() {
        return "Výstupní port";
    }
}
