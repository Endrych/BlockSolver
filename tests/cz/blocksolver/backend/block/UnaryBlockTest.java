package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.unary.*;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnaryBlockTest {
    private UnaryBlock uBlock;

    @Before
    public void setUp(){
        uBlock = new UnaryBlock("Unary Block 1", 40,40,80,60, IncrementOperation.getInstance());
        InputPort a = uBlock.getInputPort(1);
        a.setValue(10.0);
    }

    @Test
    public void testIncrementOperation(){
        uBlock.executeBlock();
        OutputPort b = uBlock.getOutputPort();
        Assert.assertEquals(new Double(11.0), b.getValue());
    }

    @Test
    public void testDecrementOperation(){
        uBlock.changeOperation(DecrementOperation.getInstance());
        uBlock.executeBlock();
        OutputPort b = uBlock.getOutputPort();
        Assert.assertEquals(new Double(9.0), b.getValue());
    }

    @Test
    public void testSquareOperation(){
        uBlock.changeOperation(SquareOperation.getInstance());
        uBlock.executeBlock();
        OutputPort b = uBlock.getOutputPort();
        Assert.assertEquals(new Double(100.0), b.getValue());
    }

    @Test
    public void testSquareRootOperation(){
        uBlock.changeOperation(SquareRootOperation.getInstance());
        InputPort a = uBlock.getInputPort(1);
        a.setValue(16.0);
        uBlock.executeBlock();
        OutputPort b = uBlock.getOutputPort();
        Assert.assertEquals(new Double(4.0), b.getValue());
    }

    @Test
    public void testCubeRootOperation(){
        uBlock.changeOperation(CubeRootOperation.getInstance());
        InputPort a = uBlock.getInputPort(1);
        a.setValue(512.0);
        uBlock.executeBlock();
        OutputPort b = uBlock.getOutputPort();
        Assert.assertEquals(new Double(8.0), b.getValue());
    }

    @Test
    public void testToString(){
        Assert.assertEquals("Unary Block 1",uBlock.toString());
    }

}