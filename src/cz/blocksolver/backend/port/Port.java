package cz.blocksolver.backend.port;

/**
 * Abstraktni trida reprezentujici port
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public abstract class Port {
    protected String name;
    protected PortType type;
    protected Double value;
    protected Integer blockIndex;

    /**
     * Konstruktur
     * @param type Typ portu
     * @param value Hodnota na portu
     */
    public Port(PortType type, Double value){
        this.type = type;
        this.value = value;
    }

    /**
     * Getter pro index portu
     * @return index portu
     */
    public Integer getBlockIndex() {
        return blockIndex;
    }

    /**
     * Setter pro index portu
     * @param blockIndex novy index
     */
    public void setBlockIndex(Integer blockIndex) {
        this.blockIndex = blockIndex;
    }

    /**
     * Getter pro nazev portu
     * @return nazev portu
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pro nazev bloku
     * @param name novy nazev portu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter pro typ portu
     * @return typ portu
     */
    public PortType getType() {
        return type;
    }

    /**
     * Setter pro typ portu
     * @param type novy typ portu
     */
    public void setType(PortType type) {
        this.type = type;
    }

    /**
     * Getter pro hodnotu portu
     * @return hodnota portu
     */
    public Double getValue() {
        return value;
    }

    /**
     * Setter pro hodnotu portu
     * @param value nova hodnota portu
     */
    public void setValue(Double value) {
        this.value = value;
    }
}
