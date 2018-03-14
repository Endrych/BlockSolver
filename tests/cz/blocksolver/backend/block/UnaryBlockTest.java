package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.unary.DecrementOperation;
import cz.blocksolver.backend.block.unary.IncrementOperation;
import cz.blocksolver.backend.block.unary.SquareOperation;
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

}