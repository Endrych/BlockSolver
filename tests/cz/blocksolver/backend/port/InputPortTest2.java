package cz.blocksolver.backend.port;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputPortTest2 {

    Port input = new InputPort(PortType.NUMBER, 0.0, 1);

    @Test
    public void TestToString() {
        String test = input.toString();
        Assert.assertEquals("Vstupní port 1", input.toString());

    }
}