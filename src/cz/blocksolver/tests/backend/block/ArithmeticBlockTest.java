package cz.blocksolver.tests.backend.block;

import cz.blocksolver.backend.block.ArithmeticBlock;
import cz.blocksolver.backend.block.BlockType;
import cz.blocksolver.backend.block.arithmetic.*;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticBlockTest {
    private ArithmeticBlock aBlock;

    @Before
    public void setup(){
        aBlock = new ArithmeticBlock("ArithmeticBlock1",45,65,100,100, AddOperation.getInstance());
        InputPort a = aBlock.getInputPort(1);
        a.setValue(50.0);
        InputPort d = aBlock.getInputPort(2);
        d.setValue(5.0);
    }


    @Test
    public void testInitialName(){
        String test = aBlock.getName();
        Assert.assertEquals("ArithmeticBlock1", test);
    }

    @Test
    public void testChangeNme(){
        aBlock.setName("Scitaci blok");
        Assert.assertEquals("Scitaci blok", aBlock.getName());
    }

    @Test
    public void testGetBlockType(){
        Assert.assertEquals(BlockType.ARITHMETIC, aBlock.getType());
    }

    @Test
    public void testInitialPositionX(){
        Assert.assertEquals(new Integer(45), aBlock.getX());
    }

    @Test
    public void testInitialPositionY(){
        Assert.assertEquals(new Integer(65), aBlock.getY());
    }

    @Test
    public void testChangePositionX(){
        aBlock.setX(78);
        Assert.assertEquals(new Integer(78), aBlock.getX());
    }

    @Test
    public void testChangePositionY(){
        aBlock.setY(90);
        Assert.assertEquals(new Integer(90), aBlock.getY());
    }

    @Test
    public void testInitialWidth(){
        Assert.assertEquals(new Integer(100), aBlock.getWidth());
    }

    @Test
    public void testInitialHeight(){
        Assert.assertEquals(new Integer(100), aBlock.getHeight());
    }

    @Test
    public void testChangeWidth(){
        aBlock.setWidth(200);
        Assert.assertEquals(new Integer(200), aBlock.getWidth());
    }

    @Test
    public void testChangeHeight(){
        aBlock.setHeight(200);
        Assert.assertEquals(new Integer(200), aBlock.getHeight());
    }

//    @Test
//    public void testBasicAddOperation(){
//        aBlock.executeBlock();
//        OutputPort b = aBlock.getOutputPort();
//        Assert.assertEquals(new Double(55), b.getValue());
//    }

//    @Test
//    public void testChangeToSubtractionOperation(){
//        aBlock.changeOperation(SubtractionOperation.getInstance());
//        aBlock.executeBlock();
//        OutputPort b = aBlock.getOutputPort();
//        Assert.assertEquals(new Double(45), b.getValue());
//    }
//
//    @Test
//    public void testChangeToMultiplyOperation(){
//        aBlock.changeOperation(MultiplyOperation.getInstance());
//        aBlock.executeBlock();
//        OutputPort b = aBlock.getOutputPort();
//        Assert.assertEquals(new Double(250), b.getValue());
//    }
//    @Test
//    public void testChangeToDivisionOperation(){
//        aBlock.changeOperation(DivisionOperation.getInstance());
//        aBlock.executeBlock();
//        OutputPort b = aBlock.getOutputPort();
//        Assert.assertEquals(new Double(10), b.getValue());
//    }
//    @Test
//    public void testChangeToPowOperation(){
//        aBlock.changeOperation(PowOperation.getInstance());
//        aBlock.executeBlock();
//        OutputPort b = aBlock.getOutputPort();
//        Assert.assertEquals(new Double(312500000), b.getValue());
//    }

    @Test
    public void testToString(){
        assertEquals("ArithmeticBlock1",aBlock.toString());
    }

    @Test
    public void testHashCode(){
        ArithmeticBlock aBlock1 = new ArithmeticBlock("ArithmeticBlock1",45,65,100,100, AddOperation.getInstance());
        aBlock1.getInputPort(1).setName("Port1");
        aBlock.getInputPort(1).setName("Port1");
        aBlock1.getInputPort(1).setValue(50.0);
        aBlock1.getInputPort(2).setValue(5.0);
        assertEquals(aBlock1.hashCode(),aBlock.hashCode());
        aBlock1.getInputPort(1).setValue(15.);
        assertNotEquals(aBlock1.hashCode(),aBlock.hashCode());
    }

    @Test
    public void testEquals(){
        ArithmeticBlock aBlock1 = new ArithmeticBlock("ArithmeticBlock1",45,65,100,100, AddOperation.getInstance());
        aBlock1.getInputPort(1).setName("Port1");
        aBlock.getInputPort(1).setName("Port1");
        aBlock1.getInputPort(1).setValue(50.0);
        aBlock1.getInputPort(2).setValue(5.0);
        assertEquals(aBlock1.equals(aBlock),true);
        aBlock1.getInputPort(1).setValue(15.);
        assertEquals(aBlock1.equals(aBlock),false);
    }

}