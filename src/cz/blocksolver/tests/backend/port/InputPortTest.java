package cz.blocksolver.tests.backend.port;

import cz.blocksolver.backend.port.InputPort;
import cz.blocksolver.backend.port.OutputPort;
import cz.blocksolver.backend.port.PortType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputPortTest {

    private InputPort inputPort;

    @Before
    public void init(){
        inputPort = new InputPort(PortType.NUMBER, 0.0, 1);
    }

    @Test
    public void inputHashTest(){
        InputPort inputPort1 = new InputPort(PortType.NUMBER, 0.0, 1);
        inputPort1.setOutputPort(new OutputPort(PortType.NUMBER, 0.0));
        inputPort.setOutputPort(new OutputPort(PortType.NUMBER, 0.0));
        assertEquals(inputPort1.hashCode(),inputPort.hashCode());
        inputPort.setName("Blbost");
        assertNotEquals(inputPort1.hashCode(),inputPort.hashCode());

    }

    @Test
    public void inputTwoEqualInputsPort(){
        InputPort inputPort1 = new InputPort(PortType.NUMBER, 0.0, 1);
        inputPort1.setOutputPort(new OutputPort(PortType.NUMBER, 0.0));
        inputPort.setOutputPort(new OutputPort(PortType.NUMBER, 0.0));
        assertEquals(inputPort1.equals(inputPort),true);
        inputPort.setName("Blbost");
        assertEquals(inputPort1.equals(inputPort),false);
    }
    @Test
    public void inputPortToString(){
        assertEquals("Vstupní port 1",inputPort.toString());
    }
}