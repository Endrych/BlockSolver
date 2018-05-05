package cz.blocksolver.backend.port;

import java.util.Objects;

/**
 * Trida reprezentujici vstupni port
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class InputPort extends Port {
    private OutputPort outputPort;
    private Integer index;
    private Boolean valueSet;
    private Boolean connectedToOutputPort;

    /**
     * Konstruktor
     * @param type typ portu
     * @param value hodnota portu
     * @param index index vstupniho portu
     */
    public InputPort(PortType type, Double value, Integer index) {
        super(type, value);
        this.index = index;
        this.valueSet = false;
        this.connectedToOutputPort = false;
    }

    /**
     * Getter pro informaci jestli uz je nastavena hodnota
     * @return informace jestli hodnota uz je nastavena
     */
    public Boolean getValueSet() {
        return valueSet;
    }

    /**
     * Setter pro informaci jestli uz je nastavena hodnota
     * @param valueSet nova hodnota
     */
    public void setValueSet(Boolean valueSet) {
        this.valueSet = valueSet;
    }

    /**
     * Getter pro informaci jestli je port spojen s vystupnim portem
     * @return informace jestli je port spojen s vystupnim portem
     */
    public Boolean getConnectedToOutputPort() {
        return connectedToOutputPort;
    }

    /**
     * Setter pro informaci jestli je port spojen s vystupnim portem
     * @param connectedToOutputPort nova hodnota
     */
    public void setConnectedToOutputPort(Boolean connectedToOutputPort) {
        this.connectedToOutputPort = connectedToOutputPort;
    }

    /**
     * Getter pro ziskani indexu portu
     * @return index portu
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Setter pro index portu
     * @param index nova hodnota indexu portu
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Getter pro vystupni port se kterym je port propojen
     * @return vystupni port
     */
    public OutputPort getOutputPort() {
        return outputPort;
    }

    /**
     * Setter pro vystupni port se kterym je port propojen
     * @param outputPort novy vystupni port
     */
    public void setOutputPort(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputPort inputPort = (InputPort) o;
        return Objects.equals(this.outputPort, inputPort.outputPort) &&
                Objects.equals(this.index, inputPort.index) &&
                Objects.equals(this.name, inputPort.name) &&
                Objects.equals(this.value, inputPort.value) &&
                Objects.equals(this.type, inputPort.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(outputPort, index, name,value,type);
    }

    @Override
    public String toString() {
        return "Vstupní port " + this.index;
    }
}
