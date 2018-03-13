package cz.blocksolver.backend.block;


import cz.blocksolver.backend.block.arithmetic.PowOperation;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Block implements IBlock {
    protected String name;
    protected Integer x, y, width, height;
    protected OutputPort outputPort;

    public Block(String name, Integer x, Integer y, Integer width, Integer height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.outputPort = new OutputPort();
    }

    //getter pro output setter pryc
    @Override
    public void executeBlock() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Collection<InputPort> getInputPorts() {
        return inputPorts;
    }

    public void setInputPorts(InputPort inputPort) {
        inputPorts.add(inputPort);
    }

    public OutputPort getOutputPort() {
        return outputPort;
    }

    public void setOutputPort(OutputPort outputPort) {
        this.outputPort = outputPort;
    }
}
