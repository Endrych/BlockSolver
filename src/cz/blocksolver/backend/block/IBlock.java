package cz.blocksolver.backend.block;

import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;

/**
 * Interface pro blok
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public interface IBlock {
    /**
     * Getter pro typ portu
     * @return typ portu
     */
    BlockType getType();

    /**
     * Getter pro nazev bloku
     * @return nazev bloku
     */
    String getName();

    /**
     * Setter pro nazev bloku
     * @param name nazev bloku
     */
    void setName(String name);

    /**
     * Getter pro x-ovou pozici
     * @return x-ova pozice
     */
    Integer getX();

    /**
     * Setter pro x-ovou pozici
     * @param x x-ova pozice
     */
    void setX(Integer x);

    /**
     * Setter pro y-pozici
     * @return y-ova pozice
     */
    Integer getY();

    /**
     * Setter pro y-pozici
     * @param y y-ova pozice
     */
    void setY(Integer y);

    /**
     * Getter pro sirku bloku
     * @return sirka bloku
     */
    Integer getWidth();

    /**
     * Setter pro sirku bloku
     * @param width sirka bloku
     */
    void setWidth(Integer width);

    /**
     * Getter pro vysku bloku
     * @return vyska bloku
     */
    Integer getHeight();

    /**
     * Setter pro vysku bloku
     * @param height vyska bloku
     */
    void setHeight(Integer height);

    /**
     * Getter pro vystupni port
     * @return vystupni port
     */
    OutputPort getOutputPort();

    /**
     * Metoda vraci vstupni port podle indexu
     * @param index Index portu ktery chceme ziskat
     * @return vstupni port
     */
    InputPort getInputPort(Integer index);
}
