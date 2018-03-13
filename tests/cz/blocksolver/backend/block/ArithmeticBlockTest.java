package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.arithmetic.AddOperation;
import cz.blocksolver.backend.block.arithmetic.DivisionOperation;
import cz.blocksolver.backend.block.arithmetic.PowOperation;
import cz.blocksolver.backend.block.arithmetic.SubtractionOperation;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticBlockTest {
    public ArithmeticBlock aBlock = new ArithmeticBlock("ArithmeticBlock1",45,65,100,100, AddOperation.getInstance());

    @Test
    public void testArithmeticBlock(){
        String test = aBlock.getName();
        Assert.assertEquals("ArithmeticBlock1", test);
    }

    @Test
    public void testBasicAddOperation(){
        InputPort a = aBlock.getInputPort(1);
        a.setValue(45.5);
        aBlock.executeBlock();
        OutputPort b = aBlock.getOutputPort();
        Double c = 45.5;
        Assert.assertEquals(c, b.getValue());
    }

    @Test
    public void testChangeOperation(){
        InputPort a = aBlock.getInputPort(1);
        a.setValue(20.0);
        InputPort d = aBlock.getInputPort(2);
        d.setValue(-5.0);
        aBlock.changeOperation(SubtractionOperation.getInstance());
        aBlock.executeBlock();
        OutputPort b = aBlock.getOutputPort();
        Double c = 25.0;
        Assert.assertEquals(c, b.getValue());

    }

}