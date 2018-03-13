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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Výstupní port";
    }
}
