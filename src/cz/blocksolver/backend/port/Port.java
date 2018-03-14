package cz.blocksolver.backend.port;

public abstract class Port {
    protected String name;
    protected PortType type;
    protected Double value;

    public Port(PortType type, Double value){
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PortType getType() {
        return type;
    }

    public void setType(PortType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
