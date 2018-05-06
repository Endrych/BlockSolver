package cz.blocksolver.frontend;

import cz.blocksolver.backend.port.OutputPort;
import javafx.scene.shape.Line;

/**
 * Slouzi k vytvoreni objektu, ktery reprezentuje spoj mezi
 * dvema bloky
 * @author David Endrych (xendry02)
 * @author Marek Kukučka (xkukuc04)
 */
public class ConnectingLine {
    OutputPort outputPort;
    Integer outX;
    Integer outY;
    Integer inX;
    Integer inY;
    Integer portNum;
    String type;
    Line line;
    Boolean showed = false;

    /**
     * Konstruktor
     */
    public ConnectingLine() {
        this.line = null;
    }

    /**
     * Konstruktor
     * @param outputPort - vystupni port
     * @param outX - souradnice X vystupniho portu
     * @param outY - souradnice Y vystupniho portu
     * @param inX - souradnice X vstupniho portu
     * @param inY -souradnice Y vstupniho portu
     * @param portNum - pozice vstupniho portu zda se jedna i prvni nebo druhy port
     * @param type - typ bloku, kteremu patri vstupni port
     */
    public ConnectingLine(OutputPort outputPort, Integer outX, Integer outY, Integer inX, Integer inY, Integer portNum, String type) {
        this.outputPort = outputPort;
        this.outX = outX;
        this.outY = outY;
        this.inX = inX;
        this.inY = inY;
        this.portNum = portNum;
        this.type = type;
    }


    /**
     * Vrati informaci zda je Line zobrazena
     * @return
     */
    public Boolean getShowed() {
        return showed;
    }


    public void setShowed(Boolean showed) {
        this.showed = showed;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Integer getPortNum() {
        return portNum;
    }

    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OutputPort getOutputPort() {
        return outputPort;
    }

    public void setOutputPort(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public Integer getOutX() {
        return outX;
    }

    public void setOutX(Integer outX) {
        this.outX = outX;
    }

    public Integer getOutY() {
        return outY;
    }

    public void setOutY(Integer outY) {
        this.outY = outY;
    }

    public Integer getInX() {
        return inX;
    }

    public void setInX(Integer inX) {
        this.inX = inX;
    }

    public Integer getInY() {
        return inY;
    }

    public void setInY(Integer inY) {
        this.inY = inY;
    }
}
