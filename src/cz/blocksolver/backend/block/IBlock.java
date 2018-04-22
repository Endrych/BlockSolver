package cz.blocksolver.backend.block;

import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;

public interface IBlock {
    void executeBlock();
    BlockType getType();
    String getName();
    void setName(String name);
    Integer getX();
    void setX(Integer x);
    Integer getY();
    void setY(Integer y);
    Integer getWidth();
    void setWidth(Integer width);
    Integer getHeight();
    void setHeight(Integer height);
    OutputPort getOutputPort();
    InputPort getInputPort(Integer index);
}
