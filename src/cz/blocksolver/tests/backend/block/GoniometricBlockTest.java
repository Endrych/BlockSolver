package cz.blocksolver.tests.backend.block;

import cz.blocksolver.backend.block.GoniometricBlock;
import cz.blocksolver.backend.block.goniometric.*;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import cz.blocksolver.backend.port.PortType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GoniometricBlockTest {

    private GoniometricBlock gBlock;
    @Before
    public void setUp() {
        gBlock = new GoniometricBlock("Goniometricky blok 1", 45, 65, 100, 50, SinusOperation.getInstance());

    }

    @Test
    public void testSinusOperationDegree(){
        InputPort a = gBlock.getInputPort(1);
        a.setValue(180.0);
        a.setType(PortType.DEGREE);
        gBlock.executeBlock(180.0);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.0), b.getValue());
    }

    @Test
    public void testSinusOperationRadian(){
        InputPort a = gBlock.getInputPort(1);
        a.setValue(1.5);
        a.setType(PortType.RADIAN);
        gBlock.executeBlock(1.5);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.99749), b.getValue());
    }

    @Test
    public void testCosinusOperationDegree(){
        gBlock.changeOperation(CosinusOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(180.0);
        a.setType(PortType.DEGREE);
        gBlock.executeBlock(180.0);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(-1.0), b.getValue());
    }

    @Test
    public void testCosinusOperationRadian(){
        gBlock.changeOperation(CosinusOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(0.5);
        a.setType(PortType.RADIAN);
        gBlock.executeBlock(0.5);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.87758), b.getValue());
    }

    @Test
    public void testTangensOperationRadian(){
        gBlock.changeOperation(TangensOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(5.0);
        a.setType(PortType.RADIAN);
        gBlock.executeBlock(5.0);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(-3.38052), b.getValue());
    }

    @Test
    public void testTangensOperationDegree(){
        gBlock.changeOperation(TangensOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(180.0);
        a.setType(PortType.DEGREE);
        gBlock.executeBlock(180.0);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.0), b.getValue());
    }

    @Test
    public void testCotangensOperationRadian(){
        gBlock.changeOperation(CotangensOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(0.5);
        a.setType(PortType.RADIAN);
        gBlock.executeBlock(0.5);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(1.83049), b.getValue());
    }

    @Test
    public void testCotangensOperationDegree(){
        gBlock.changeOperation(CotangensOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(50.0);
        a.setType(PortType.DEGREE);
        gBlock.executeBlock(50.0);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.8391), b.getValue());
    }


    @Ignore
    @Test
    public void testChangeOperationToAsin(){
        gBlock.changeOperation(ArcsinusOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(4.5);
        a.setType(PortType.NUMBER);
        gBlock.executeBlock(4.5);
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.5235), b.getValue());
    }

    @Test
    public void testToString(){
        Assert.assertEquals("Goniometricky blok 1",gBlock.toString());
    }

    @Test
    public void testHashCode(){
        GoniometricBlock gBlock1 = new GoniometricBlock("Goniometricky blok 1", 45, 65, 100, 50, SinusOperation.getInstance());
        gBlock1.getInputPort(1).setName("Port1");
        gBlock.getInputPort(1).setName("Port1");
        assertEquals(gBlock1.hashCode(),gBlock.hashCode());
        gBlock1.getInputPort(1).setValue(15.);
        assertNotEquals(gBlock1.hashCode(),gBlock.hashCode());
    }

    @Test
    public void testEquals(){
        GoniometricBlock gBlock1 = new GoniometricBlock("Goniometricky blok 1", 45, 65, 100, 50, SinusOperation.getInstance());
        gBlock1.getInputPort(1).setName("Port1");
        gBlock.getInputPort(1).setName("Port1");
        assertEquals(gBlock.equals(gBlock1),true);
        gBlock1.getInputPort(1).setValue(15.);
        assertEquals(gBlock1.equals(gBlock),false);
    }

}