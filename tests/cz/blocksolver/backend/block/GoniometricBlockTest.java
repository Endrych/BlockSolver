package cz.blocksolver.backend.block;

import cz.blocksolver.backend.block.goniometric.*;
import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import cz.blocksolver.backend.port.PortType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GoniometricBlockTest {

    public GoniometricBlock gBlock;
    @Before
    public void setUp() {
        gBlock = new GoniometricBlock("Goniometricky blok 1", 45, 65, 100, 50, SinusOperation.getInstance());

    }

    @Test
    public void testSinusOperationDegree(){
        InputPort a = gBlock.getInputPort(1);
        a.setValue(180.0);
        a.setType(PortType.DEGREE);
        gBlock.executeBlock();
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.0), b.getValue());
    }

    @Test
    public void testSinusOperationRadian(){
        InputPort a = gBlock.getInputPort(1);
        a.setValue(1.5);
        a.setType(PortType.RADIAN);
        gBlock.executeBlock();
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.99749), b.getValue());
    }

    @Test
    public void testCosinusOperationDegree(){
        gBlock.changeOperation(CosinusOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(180.0);
        a.setType(PortType.DEGREE);
        gBlock.executeBlock();
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(-1.0), b.getValue());
    }

    @Test
    public void testCosinusOperationRadian(){
        gBlock.changeOperation(CosinusOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(0.5);
        a.setType(PortType.RADIAN);
        gBlock.executeBlock();
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.87758), b.getValue());
    }

    @Test
    public void testTangensOperationRadian(){
        gBlock.changeOperation(TangensOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(5.0);
        a.setType(PortType.RADIAN);
        gBlock.executeBlock();
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(-3.38052), b.getValue());
    }

    @Test
    public void testTangensOperationDegree(){
        gBlock.changeOperation(TangensOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(180.0);
        a.setType(PortType.DEGREE);
        gBlock.executeBlock();
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.0), b.getValue());
    }

    @Test
    public void testCotangensOperationRadian(){
        gBlock.changeOperation(CotangensOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(0.5);
        a.setType(PortType.RADIAN);
        gBlock.executeBlock();
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(1.83049), b.getValue());
    }

    @Test
    public void testCotangensOperationDegree(){
        gBlock.changeOperation(CotangensOperation.getInstance());
        InputPort a = gBlock.getInputPort(1);
        a.setValue(50.0);
        a.setType(PortType.DEGREE);
        gBlock.executeBlock();
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
        gBlock.executeBlock();
        OutputPort b = gBlock.getOutputPort();
        Assert.assertEquals(new Double(0.5235), b.getValue());
    }

}