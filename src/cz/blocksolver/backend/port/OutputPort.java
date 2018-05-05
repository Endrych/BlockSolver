package cz.blocksolver.backend.port;

import java.util.Objects;

/**
 * Trida reprezentujici vystupni port
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class OutputPort extends Port {
    private InputPort inputPort;
    private Boolean containsResult;

    /**
     * Konstruktor
     * @param type typ portu
     * @param value hodnota portu
     */
    public OutputPort(PortType type, Double value) {
        super(type, value);
        this.containsResult = false;
    }

    /**
     * Getter pro zjisteni jestli port obsahuje vysledek
     * @return informace o nastaveni vysledku
     */
    public Boolean getContainsResult() {
        return containsResult;
    }

    /**
     * Setter pro informaci jestli port obsahuje vysledek
     * @param containsResult nova informace jestli je port obsahuje vysledek
     */
    public void setContainsResult(Boolean containsResult) {
        this.containsResult = containsResult;
    }

    /**
     * Getter pro vstupni port
     * @return vstupni port
     */
    public InputPort getInputPort() {
        return inputPort;
    }

    /**
     * Setter pro vstupni port
     * @param inputPort novy vstupni port
     */
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
