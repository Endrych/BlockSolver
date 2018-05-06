package cz.blocksolver.frontend;

import cz.blocksolver.backend.port.PortType;

/**
 * Slouzi k uchovani hodnot vstupniho portu Goniometrickeho bloku
 */
public class GoniometricInput {
    public PortType type;
    public String value;

    /**
     * Vrati typ vstupniho portu
     * @return
     */
    public PortType getType() {
        return type;
    }

    /**
     * Nastavi typ vstupniho portu
     * @param type
     */
    public void setType(PortType type) {
        this.type = type;
    }

    /**
     * Vrati hodnotu vstupniho portu
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Nastavi hodnotu vstupniho portu
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
