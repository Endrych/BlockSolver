package cz.blocksolver.backend.schema;

import cz.blocksolver.backend.block.ArithmeticBlock;
import cz.blocksolver.backend.block.Block;
import cz.blocksolver.backend.block.BlockType;
import cz.blocksolver.backend.block.GoniometricBlock;
import cz.blocksolver.backend.block.arithmetic.AddOperation;
import cz.blocksolver.backend.block.goniometric.SinusOperation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SchemaTest {
    private Schema schema;

    @Before
    public void init(){
        schema = new Schema("TestSchema");
    }

    @Test
    public void addBlocks(){
        ArithmeticBlock block = new ArithmeticBlock("Test", 10,20, 20, 20, AddOperation.getInstance());
        schema.addBlock(block);
        assertEquals(schema.getBlocks().size(),1);
    }

    @Test
    public void removeBlocks(){
        ArithmeticBlock block = new ArithmeticBlock("Test", 10,20, 20, 20, AddOperation.getInstance());
        schema.addBlock(block);
        assertEquals(schema.getBlocks().size(),1);
        schema.removeBlock(block);
        assertEquals(schema.getBlocks().size(),0);
    }

    @Test
    public void testToString(){
        assertEquals("TestSchema",schema.toString());
    }

    @Test
    public void testHashCode(){
        Schema schema1 = new Schema("TestSchema");
        ArithmeticBlock block = new ArithmeticBlock("blockname",5,4,20,10, AddOperation.getInstance() );
        schema.addBlock(block);
        schema1.addBlock(block);
        assertEquals(schema1.hashCode(),schema.hashCode());
        schema1.removeBlock(block);
        assertNotEquals(schema1.hashCode(),schema.hashCode());
    }

    @Test
    public void testEquals(){
        Schema schema1 = new Schema("TestSchema");
        ArithmeticBlock block = new ArithmeticBlock("blockname",5,4,20,10, AddOperation.getInstance() );
        schema.addBlock(block);
        schema1.addBlock(block);
        assertEquals(schema1.equals(schema),true);
        schema1.removeBlock(block);
        assertEquals(schema1.equals(schema),false);
    }


}