package cz.blocksolver.backend.block;


import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import cz.blocksolver.backend.port.PortType;

/**
 * Abstraktni trida reprezentujici blok
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public abstract class Block implements IBlock {
    protected String name;
    protected Integer x, y, width, height;
    protected OutputPort outputPort;
    protected BlockType type;
    protected Boolean executed;


    /**
     * Konstruktor
     * @param name nazev bloku
     * @param x x-ova poloha
     * @param y y-ova poloha
     * @param width sirka
     * @param height vyska
     * @param type typ bloku
     */
    public Block(String name, Integer x, Integer y, Integer width, Integer height, BlockType type) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.outputPort = new OutputPort(PortType.NUMBER, 0.0);
        this.type = type;
        this.executed = false;
    }

    /**
     * Setter pro vystupni port
     * @param outputPort novy vystupni port
     */
    public void setOutputPort(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    /**
     * Setter pro typ portu
     * @param type novy typ portu
     */
    public void setType(BlockType type) {
        this.type = type;
    }

    /**
     * Getter pro informaci jestli byl blok proveden
     * @return pravdivostni hodnota jestli byl uz blok proveden
     */
    public Boolean getExecuted() {
        return executed;
    }

    /**
     * Setter pro informaci jestli byl blok proveden
     * @param executed nova hodnota
     */
    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }

    /**
     * Getter pro typ portu
     * @return typ portu
     */
    public BlockType getType() {
        return type;
    }

    /**
     * Provedeni bloku
     * @param one prvni hodnota
     * @param two druha hodnota
     * @return null
     */
    public Boolean executeBlock(Double one, Double two) {
        return null;
    }

    /**
     * Provedeni bloku
     * @param one prvni hodnota
     * @return null druha hodnota
     */
    public Boolean executeBlock(Double one) {
        return null;
    }

    /**
     * Metoda vraci vstupni port podle indexu
     * @param index Index portu ktery chceme ziskat
     * @return vstupni port
     */
    @Override
    public abstract InputPort getInputPort(Integer index);

    /**
     * Getter pro nazev bloku
     * @return nazev bloku
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pro nazev bloku
     * @param name nazev bloku
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter pro x-ovou pozici
     * @return x-ova pozice
     */
    public Integer getX() {
        return x;
    }

    /**
     * Setter pro x-ovou pozici
     * @param x x-ova pozice
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Setter pro y-pozici
     * @return y-ova pozice
     */
    public Integer getY() {
        return y;
    }

    /**
     * Setter pro y-pozici
     * @param y y-ova pozice
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * Getter pro sirku bloku
     * @return sirka bloku
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Setter pro sirku bloku
     * @param width sirka bloku
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Getter pro vysku bloku
     * @return vyska bloku
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Setter pro vysku bloku
     * @param height vyska bloku
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Getter pro vystupni port
     * @return vystupni port
     */
    public OutputPort getOutputPort() {
        return outputPort;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
