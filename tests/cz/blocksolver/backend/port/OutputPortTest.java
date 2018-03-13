package cz.blocksolver.backend.port;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OutputPortTest {

    private  OutputPort outputPort;

    @Before
    public void Init(){
        outputPort = new OutputPort();
    }

    @Test
    public void InputHashTest(){
        OutputPort outputPort1 = new OutputPort();
        outputPort1.setInputPort(new InputPort(1));
        outputPort.setInputPort(new InputPort(1));
        assertEquals(outputPort.hashCode(),outputPort1.hashCode());
        outputPort.setName("Blbost");
        assertNotEquals(outputPort.hashCode(),outputPort1.hashCode());
    }


    @Test
    public void InputTwoEqualInputsPort(){
        OutputPort inputPort1 = new OutputPort();
        inputPort1.setInputPort(new InputPort(1));
        outputPort.setInputPort(new InputPort(1));
        assertEquals(inputPort1.equals(outputPort),true);
        outputPort.setName("Blbost");
        assertEquals(inputPort1.equals(outputPort),false);
    }

    @Test
    public void InputPortToString(){
        assertEquals("Výstupní port",outputPort.toString());
    }
}