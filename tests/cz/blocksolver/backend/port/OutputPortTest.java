package cz.blocksolver.backend.port;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class OutputPortTest {

    private  OutputPort outputPort;

    @Before
    public void Init(){
        outputPort = new OutputPort(PortType.NUMBER, 0.0);
    }
    @Ignore
    @Test
    public void InputHashTest(){
        OutputPort outputPort1 =  outputPort = new OutputPort(PortType.NUMBER, 0.0);
        outputPort1.setInputPort(new InputPort(PortType.NUMBER, 0.0, 1));
        outputPort.setInputPort(new InputPort(PortType.NUMBER, 0.0, 1));
        assertEquals(outputPort.hashCode(),outputPort1.hashCode());
        outputPort.setName("Blbost");
        assertNotEquals(outputPort.hashCode(),outputPort1.hashCode());
    }

    @Ignore
    @Test
    public void InputTwoEqualInputsPort(){
        OutputPort inputPort1 =  outputPort = new OutputPort(PortType.NUMBER, 0.0);
        inputPort1.setInputPort(new InputPort(PortType.NUMBER, 0.0, 1));
        outputPort.setInputPort(new InputPort(PortType.NUMBER, 0.0, 1));
        assertEquals(inputPort1.equals(outputPort),true);
        outputPort.setName("Blbost");
        assertEquals(inputPort1.equals(outputPort),false);
    }
    @Ignore
    @Test
    public void InputPortToString(){
        assertEquals("Výstupní port",outputPort.toString());
    }
}