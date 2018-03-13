package cz.blocksolver.backend.port;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputPortTest2 {

    Port input = new InputPort();


    @Test
    public void TestToString() {
        String test = input.toString();
        Assert.assertEquals("Vstupní port", input.toString());

    }
}