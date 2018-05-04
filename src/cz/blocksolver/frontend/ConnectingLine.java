package cz.blocksolver.frontend;

import cz.blocksolver.backend.port.OutputPort;
import javafx.scene.shape.Line;

public class ConnectingLine {
    OutputPort outputPort;
    Integer outX;
    Integer outY;
    Integer inX;
    Integer inY;
    Integer portNum;
    String type;
    Line line;

    public ConnectingLine() {
        this.line = null;
    }

    public ConnectingLine(OutputPort outputPort, Integer outX, Integer outY, Integer inX, Integer inY, Integer portNum, String type, Line line) {
        this.outputPort = outputPort;
        this.outX = outX;
        this.outY = outY;
        this.inX = inX;
        this.inY = inY;
        this.portNum = portNum;
        this.type = type;
        this.line = line;
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
