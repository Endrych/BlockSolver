package cz.blocksolver.backend.port;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputPortTest {

    private InputPort inputPort;

    @Before
    public void Init(){
        inputPort = new InputPort(1);
    }

    @Test
    public void InputHashTest(){
        InputPort inputPort1 = new InputPort(1);
        inputPort1.setOutputPort(new OutputPort());
        inputPort.setOutputPort(new OutputPort());
        assertEquals(inputPort1.hashCode(),inputPort.hashCode());
        inputPort.setName("Blbost");
        assertNotEquals(inputPort1.hashCode(),inputPort.hashCode());

    }


    @Test
    public void InputTwoEqualInputsPort(){
        InputPort inputPort1 = new InputPort(1);
        inputPort1.setOutputPort(new OutputPort());
        inputPort.setOutputPort(new OutputPort());
        assertEquals(inputPort1.equals(inputPort),true);
        inputPort.setName("Blbost");
        assertEquals(inputPort1.equals(inputPort),false);
    }

    @Test
    public void InputPortToString(){
        assertEquals("Vstupní port 1",inputPort.toString());
    }
}